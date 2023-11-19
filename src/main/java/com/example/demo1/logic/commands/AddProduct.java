package com.example.demo1.logic.commands;

import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.serviceFactory.MainServiceFactory;
import com.example.demo1.logic.ICommand;
import com.example.demo1.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class AddProduct implements ICommand {
    
    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        InputStream inputStream = null;
        ProductService productService = null;
        try {
            productService =(ProductService) MainServiceFactory.produce("product");
            String productName = request.getParameter("productName");
            String productPrice = request.getParameter("productPrice");
            String productCategory = request.getParameter("productCategory");
            Part filePart = request.getPart("productImage");
            if (filePart != null) {
                inputStream = filePart.getInputStream();
            }
            productService.AddProduct(productName,productPrice,productCategory,inputStream);

        } catch (ServiceException ex) {
            throw new CommandException("Error occurred while adding a product to the database.");
        } catch (ServletException ex) {
            throw new CommandException("Incorrect file format or error during file upload.");
        }

        return "WEB-INF/JSP/Administrator.jsp";
    }
}
