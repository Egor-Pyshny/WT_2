package com.example.demo1.logic.commands;

import com.example.demo1.models.Product;
import com.example.demo1.DAO.ProductDAO;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;
import com.example.demo1.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class LogOut implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException {
        try {
            ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
            List<Product> products = productDao.GetAllProduct();
            request.getSession().invalidate();
            request.setAttribute("products", products);

        } catch (DAOException ex) {
            throw new CommandException("Error occurred during logout process.", ex);
        }
        return "index.jsp";
    }
}


