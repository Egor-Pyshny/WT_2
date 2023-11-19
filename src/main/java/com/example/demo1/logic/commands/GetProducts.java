package com.example.demo1.logic.commands;

import com.example.demo1.models.Product;
import com.example.demo1.DAO.ProductDAO;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.serviceFactory.MainServiceFactory;
import com.example.demo1.logic.ICommand;
import com.example.demo1.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class GetProducts implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        List<Product> list = null;
        ProductDAO productDao = null;
        String category = null;
        try {
            ProductService productService = (ProductService) MainServiceFactory.produce("product");
            category = request.getParameter("category");
            if (category == null || category.isEmpty()){
                list = productService.GetAllProduct();
            } else {
                list = productService.GetProductListByCat(category);
            }

            request.setAttribute("products", list);

        } catch (ServiceException ex) {
            throw new CommandException("Error occurred while fetching products from the database.", ex);
        }
        return "index.jsp";
    }
}

