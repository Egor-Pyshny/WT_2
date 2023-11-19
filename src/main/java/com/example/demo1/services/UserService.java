package com.example.demo1.services;

import com.example.demo1.models.User;
import com.example.demo1.DAO.UserDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.exceptions.ServiceException;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;

public class UserService implements IService{

    public User signIn(String login, String password) throws ServiceException {
        User user = null;
        UserDAO userDao = null;
        try {
            userDao = (UserDAO) MainDAOFactory.produce("user");
            if (login == null || password == null) {
                throw new ServiceException("Incorrect password or login");
            }
            user = userDao.signIn(login, password);

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }
    
    public int registration(User user) throws ServiceException {
        UserDAO userDao = null;
        int id = -1;
        try {
            userDao = (UserDAO) MainDAOFactory.produce("user");
            if (user.getPassword() == null || user.getLogin() == null) {
                throw new ServiceException("Incorrect password or login");
            }
            id = userDao.registration(user);

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return id;
    }
}
