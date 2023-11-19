package com.example.demo1.logic.commands;

import com.example.demo1.factories.serviceFactory.MainServiceFactory;
import com.example.demo1.models.CartItem;
import com.example.demo1.DAO.OrderDAO;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;
import com.example.demo1.logic.ICommand;
import com.example.demo1.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessOrder implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        try {
            String address = request.getParameter("address");
            OrderDAO orderDao = (OrderDAO) MainDAOFactory.produce("order");
            OrderService orderService = (OrderService) MainServiceFactory.produce("order");
            List<CartItem> cart = (ArrayList<CartItem>) request.getSession().getAttribute("basket");
            orderService.createOrder(address,cart);
            request.getSession().removeAttribute("basket");
        } catch (ServiceException ex) {
            throw new CommandException("Error occurred while processing the order.", ex);
        }
        return "index.jsp";
    }
}

