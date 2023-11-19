package com.example.demo1.factories.serviceFactory;

import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.serviceFactory.IFactory;
import com.example.demo1.factories.serviceFactory.OrderServiceFactory;
import com.example.demo1.factories.serviceFactory.UserServiceFactory;
import com.example.demo1.factories.serviceFactory.ProductServiceFactory;
import com.example.demo1.services.IService;

public class MainServiceFactory {
    private static IService newService;
    private static IFactory factory;

    private static IFactory getFactory(String name){
        switch (name){
            case "user": return new UserServiceFactory();
            case "order": return new OrderServiceFactory();
            case "product": return new ProductServiceFactory();
        }
        return null;
    }

    public static IService produce(String name) throws ServiceException {
        factory = getFactory(name);
        if(factory==null){
            throw new ServiceException();
        }
        newService = factory.produce();
        return newService;
    }
}
