package com.example.demo1.logic.commands;

import com.example.demo1.models.*;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.serviceFactory.MainServiceFactory;
import com.example.demo1.logic.ICommand;
import com.example.demo1.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProductToBasket implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        ProductService productService = null;
        try {
            productService = (ProductService) MainServiceFactory.produce("product");
            int productId = Integer.parseInt(request.getParameter("productId"));

            List<CartItem> basket = (List<CartItem>) request.getSession().getAttribute("basket");
            Product product = productService.GetProductById(productId);
            if (basket == null) {
                basket = new ArrayList<>();
            }
            Boolean exist=false;
            for (int i=0;i<basket.size();i++){
                CartItem item = basket.get(i);
                if(item.product.getId()==product.getId()){
                    item.amount++;
                    exist=true;
                    break;
                }
            }
            if(!exist)
                basket.add(new CartItem(product, 1));
            request.getSession().setAttribute("basket", basket);
        } catch (Exception ex) {
            throw new CommandException("Error occurred while adding a product to the cart.");
        }

        return "index.jsp";
    }
}

