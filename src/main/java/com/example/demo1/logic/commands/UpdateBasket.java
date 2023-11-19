package com.example.demo1.logic.commands;

import com.example.demo1.models.CartItem;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UpdateBasket implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        try {
            int num = Integer.parseInt(request.getParameter("quantity"));
            int id = Integer.parseInt(request.getParameter("productId"));
            List<CartItem> basket = (ArrayList<CartItem>) request.getSession().getAttribute("basket");
            for (CartItem item : basket) {
                if (item.getProduct().getId() == id) {
                    if(item.amount+num<=0){
                        basket.remove(item);
                    } else {
                        item.amount+=num;
                    }
                }
            }
            request.getSession().removeAttribute("basket");
            request.getSession().setAttribute("basket", basket);
        } catch (Exception ex) {
            throw new CommandException("Error occurred while updating the basket.", ex);
        }
        return "WEB-INF/JSP/Basket.jsp";
    }
}

