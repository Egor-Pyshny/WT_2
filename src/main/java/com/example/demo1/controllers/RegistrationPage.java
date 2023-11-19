package com.example.demo1.controllers;

import com.example.demo1.exceptions.CommandException;
import com.example.demo1.logic.commands.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/Registration")
@MultipartConfig
public class RegistrationPage extends HttpServlet {
    public RegistrationPage() {
        super();
    }

    @Override
    public void init() throws ServletException {
        Initializer.initialize();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "en");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/Registration.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        } else {
            System.out.println("RequestDispatcher is NULL");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Registration registration = new Registration();
        try {
            registration.execute(req);
            resp.sendRedirect(req.getContextPath() + "/Restik");
        } catch (CommandException ex) {
            req.setAttribute("registrationFailed", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/Registration.jsp");
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            } else {
                System.out.println("RequestDispatcher is NULL");
            }
            System.out.println("ERROR: Page exception in Cotroller " + ex.getMessage());
        } catch (Exception ex) {
            req.setAttribute("error", ex.getMessage());
            System.out.println("ERROR: Page exception in Cotroller2 " + ex.getMessage());
        }
    }
}

