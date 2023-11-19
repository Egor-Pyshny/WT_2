package com.example.demo1.factories.serviceFactory;

import com.example.demo1.services.IService;
import com.example.demo1.services.ProductService;
import com.example.demo1.factories.serviceFactory.IFactory;

class ProductServiceFactory implements IFactory {

    private IService IService = null;

    @Override
    public IService produce() {
        if(IService == null){
            IService = new ProductService();
        }
        return IService;
    }
}