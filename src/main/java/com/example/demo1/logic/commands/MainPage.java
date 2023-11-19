package com.example.demo1.logic.commands;

import com.example.demo1.logic.ICommand;
import com.example.demo1.models.Product;
import com.example.demo1.DAO.ProductDAO;
import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class MainPage implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Product> list = null;
        String result = "";
        ProductDAO productDao = null;
        String category = null;
        try {
            category = request.getParameter("category");
            productDao = (ProductDAO) MainDAOFactory.produce("product");
            if (category == null || category.isEmpty()){
                list = productDao.GetAllProduct();
            } else {
                list = productDao.GetProductListByCat(category);
            }
            result = "index.jsp";
            request.setAttribute("products", list);
            request.getSession().setAttribute("products", list);
        } catch (DAOException ex){
            throw new CommandException("Page Error", ex);
        }
        return result;
    }
}
