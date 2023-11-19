package com.example.demo1.logic.commands;

import com.example.demo1.models.CartItem;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class RemoveProductFromBasket implements ICommand {
    
    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            List<CartItem> basket = (List<CartItem>) request.getSession().getAttribute("basket");
            for (int i=0;i<basket.size();i++) {
                CartItem item = basket.get(i);
                if(item.product.getId()==productId){
                    basket.remove(item);
                    break;
                }
            }
            request.getSession().setAttribute("basket", basket);
        } catch (Exception ex) {
            throw new CommandException("Error occurred while removing the cart item.", ex);
        }

        return "WEB-INF/JSP/Basket.jsp";
    }
}

