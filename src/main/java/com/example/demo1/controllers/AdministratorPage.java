package com.example.demo1.controllers;

import com.example.demo1.logic.commands.AddProduct;
import com.example.demo1.logic.commands.Authorization;
import com.example.demo1.exceptions.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/Administrator")
@MultipartConfig
public class AdministratorPage extends HttpServlet {
    public AdministratorPage() {
        super();
    }

    @Override
    public void init() throws ServletException {
        Initializer.initialize();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        String path = "WEB-INF/JSP/Administrator.jsp";
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
        AddProduct addProduct = new AddProduct();
        try {
            addProduct.execute(req);
        } catch (CommandException ex) {
            req.setAttribute("addFailed", true);
            System.out.println("ERROR: Page exception in Cotroller " + ex.getMessage());
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            System.out.println("ERROR: Page exception in Cotroller2 " + ex.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/Administrator.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        } else {
            System.out.println("RequestDispatcher is NULL");
        }
    }
}

