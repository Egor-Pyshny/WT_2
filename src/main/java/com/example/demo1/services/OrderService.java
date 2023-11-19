package com.example.demo1.services;

import com.example.demo1.models.CartItem;
import com.example.demo1.DAO.OrderDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;

import java.util.List;

public class OrderService implements IService{
    public void createOrder(String address, List<CartItem> cart) throws ServiceException {
        try{
            if (address != null && cart != null) {
                OrderDAO orderDao =(OrderDAO)MainDAOFactory.produce("order");
                orderDao.CreateOrder(address, cart);
            }
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }
}
