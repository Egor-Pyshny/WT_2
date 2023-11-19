package com.example.demo1.logic.commands;

import com.example.demo1.factories.serviceFactory.MainServiceFactory;
import com.example.demo1.models.SessionAttributes;
import com.example.demo1.models.User;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.logic.ICommand;
import com.example.demo1.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class Registration implements ICommand {
    
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = new User();
        UserService userService = null;
        try {
            userService = (UserService)MainServiceFactory.produce("user");

            user.setLogin(request.getParameter("Login"));
            user.setPassword(request.getParameter("Password"));
            if (user.getPassword().equals(request.getParameter("confirm-password"))) {
                int userId = userService.registration(user);
                request.setAttribute("SomeMessage", "Successful registration");
                request.getSession().setAttribute(SessionAttributes.Authorized, true);
                request.getSession().setAttribute(SessionAttributes.UserId, userId);
            } else {
                request.setAttribute("IncorrectData", "Passwords are not the same");
            }

        } catch (ServiceException ex) {
            throw new CommandException("Error occurred during user registration.", ex);
        }
        return "index.jsp";
    }
}

