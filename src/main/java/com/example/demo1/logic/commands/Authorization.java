package com.example.demo1.logic.commands;

import com.example.demo1.models.SessionAttributes;
import com.example.demo1.models.User;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.serviceFactory.MainServiceFactory;
import com.example.demo1.logic.ICommand;
import com.example.demo1.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Authorization implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        UserService userService = null;
        try {
           userService = (UserService) MainServiceFactory.produce("user");

            User user = userService.signIn(request.getParameter("Login"), request.getParameter("Password"));
            request.setAttribute("SomeMessage", "Successful LogIn");
            request.getSession().setAttribute(SessionAttributes.Authorized, true);
            request.getSession().setAttribute(SessionAttributes.UserId, user.getId());
            request.getSession().setAttribute(SessionAttributes.isAdmin, user.getRole());

        } catch (ServiceException ex) {
            throw new CommandException("Incorrect Login or password.", ex);
        }
        return "index.jsp";
    }

}

