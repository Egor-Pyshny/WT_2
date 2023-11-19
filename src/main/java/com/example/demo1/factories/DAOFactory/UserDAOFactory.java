package com.example.demo1.factories.DAOFactory;

import com.example.demo1.DAO.IDAO;
import com.example.demo1.DAO.UserDAO;
import com.example.demo1.factories.DAOFactory.IFactory;

class UserDAOFactory implements IFactory {

    private IDAO IDAOService = null;

    @Override
    public IDAO produce() {
        if(IDAOService == null){
            IDAOService = new UserDAO();
        }
        return IDAOService;
    }
}
