package com.example.demo1.factories.DAOFactory;

import com.example.demo1.DAO.IDAO;
import com.example.demo1.DAO.OrderDAO;
import com.example.demo1.factories.DAOFactory.IFactory;

class OrderDAOFactory implements IFactory {

    private IDAO IDAOService = null;

    @Override
    public IDAO produce() {
        if(IDAOService == null){
            IDAOService = new OrderDAO();
        }
        return IDAOService;
    }
}
