package com.example.demo1.factories.serviceFactory;

import com.example.demo1.services.IService;
import com.example.demo1.services.UserService;
import com.example.demo1.factories.serviceFactory.IFactory;

class UserServiceFactory implements IFactory {

    private IService IService = null;

    @Override
    public IService produce() {
        if(IService == null){
            IService = new UserService();
        }
        return IService;
    }
}