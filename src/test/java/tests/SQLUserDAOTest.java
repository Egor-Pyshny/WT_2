package tests;

import com.example.demo1.models.User;
import com.example.demo1.DAO.UserDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.ConnectionPoolFactory;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;
import com.example.demo1.connection.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SQLUserDAOTest {

    @Test
    void signIn() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.CreateConnections();
        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        UserDAO userDAO = (UserDAO) MainDAOFactory.produce("user");
        User user = userDAO.signIn("as", "as");
        assertNotNull(user);
    }
}