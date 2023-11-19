package com.example.demo1.factories.serviceFactory;

import com.example.demo1.DAO.IDAO;
import com.example.demo1.services.IService;

public interface IFactory {
    public IService produce();
}
