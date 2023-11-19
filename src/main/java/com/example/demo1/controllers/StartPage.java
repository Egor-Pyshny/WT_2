package com.example.demo1.controllers;

import com.example.demo1.logic.ICommand;
import com.example.demo1.logic.commands.AddProductToBasket;
import com.example.demo1.logic.commands.LogOut;
import com.example.demo1.logic.commands.MainPage;
import com.example.demo1.exceptions.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/")
@MultipartConfig
public class StartPage extends HttpServlet {
    public StartPage() {
        super();
    }

    @Override
    public void init() throws ServletException {
       Initializer.initialize();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = "index.jsp";
        if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "en");
        }
        try {
            MainPage content = new MainPage();
            content.execute(req);
        } catch (CommandException e) {
            System.out.println("ERROR: Page exception in Controller " + e.getMessage());
            page = "error.jsp";
            req.setAttribute("error", e.getMessage());
        } catch (Exception e) {
            page = "error.jsp";
            System.out.println("ERROR: Page exception in Controller " + e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        } else {
            System.out.println("RequestDispatcher is NULL");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = "index.jsp";
        String command = req.getParameter("command");
        if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "en");
        }
        try {
            ICommand content = null;
            switch (command){
                case "LOG_OUT":{
                    content = new LogOut();
                    //req.removeAttribute("cart");
                    break;
                }
                case "ADD_TO_BASKET":{
                    content = new AddProductToBasket();
                    req.setAttribute("command","");
                    break;
                }
                default:{
                    content = new MainPage();
                    break;
                }
            }
            content.execute(req);
        } catch (CommandException e) {
            System.out.println("ERROR: Page exception in Controller " + e.getMessage());
            page = "error.jsp";
            req.setAttribute("error", e.getMessage());
        } catch (Exception e) {
            page = "error.jsp";
            System.out.println("ERROR: Page exception in Controller " + e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        } else {
            System.out.println("RequestDispatcher is NULL");
        }
    }
}

