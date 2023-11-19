package com.example.demo1.factories.serviceFactory;

import com.example.demo1.services.IService;
import com.example.demo1.services.OrderService;
import com.example.demo1.factories.serviceFactory.IFactory;

class OrderServiceFactory implements IFactory {

    private IService IService = null;

    @Override
    public IService produce() {
        if(IService == null){
            IService = new OrderService();
        }
        return IService;
    }
}