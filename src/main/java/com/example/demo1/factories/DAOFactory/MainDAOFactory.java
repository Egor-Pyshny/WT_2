package com.example.demo1.factories.DAOFactory;

import com.example.demo1.DAO.IDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.DAOFactory.IFactory;

public class MainDAOFactory{

    private static IDAO newIDAO;
    private static IFactory factory;

    private static IFactory getFactory(String name){
        switch (name){
            case "user": return new UserDAOFactory();
            case "order": return new OrderDAOFactory();
            case "product": return new ProductDAOFactory();
        }
        return null;
    }

    public static IDAO produce(String name) throws DAOException {
        factory = getFactory(name);
        if(factory==null){
            throw new DAOException();
        }
        newIDAO = factory.produce();
        return newIDAO;
    }
}
