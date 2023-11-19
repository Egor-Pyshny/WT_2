package com.example.demo1.controllers;

import com.example.demo1.logic.ICommand;
import com.example.demo1.logic.commands.*;
import com.example.demo1.exceptions.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/Basket")
@MultipartConfig
public class BasketPage extends HttpServlet {
    public BasketPage() {
        super();
    }

    @Override
    public void init() throws ServletException {
        Initializer.initialize();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        String path = "WEB-INF/JSP/Basket.jsp";
        if(command == "TO_MAIN"){
            path = "index.jsp";
        }
        if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "en");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        } else {
            System.out.println("RequestDispatcher is NULL");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICommand content = null;
        String command = req.getParameter("command");
        switch (command){
            case "UPDATE_BASKET":{
                content = new UpdateBasket();
                break;
            }
            case "REMOVE_FROM_BASKET":{
                content = new RemoveProductFromBasket();
                break;
            }
            default:{
                break;
            }
        }
        try {
            if(content != null)
                content.execute(req);
        } catch (CommandException ex) {
            System.out.println("ERROR: Page exception in Cotroller " + ex.getMessage());
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            System.out.println("ERROR: Page exception in Cotroller2 " + ex.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/Basket.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        } else {
            System.out.println("RequestDispatcher is NULL");
        }
    }
}

