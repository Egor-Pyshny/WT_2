package com.example.demo1.services;

import com.example.demo1.models.Product;
import com.example.demo1.DAO.ProductDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;

import java.io.InputStream;
import java.util.List;
public class ProductService implements IService{

    public List<Product> GetProductListByCat(String category) throws ServiceException {
        List<Product> list = null;
        try {
            if (category != null){
                ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
                list = productDao.GetProductListByCat(category);

            }
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }

        return list;
    }

    public List<Product> GetAllProduct() throws ServiceException {
        List<Product> list = null;
        try {
            ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
            list = productDao.GetAllProduct();
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
        return list;
    }

    public boolean AddProduct(String name, String price, String category, InputStream file) throws ServiceException {
        try {
            ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
            if (name == null || price == null || category == null || file == null){
                throw new ServiceException("Incorrect value in product add");
            }
            productDao.AddProduct(name, price, category, file);
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }

        return true;
    }

    public Product GetProductById(int id) throws ServiceException {
        Product product = null;
        try {
            if (id <= 0){
                throw  new ServiceException("Incorrect id");
            }
            ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
            product = productDao.GetProductById(id);

        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
        return product;
    }
}
