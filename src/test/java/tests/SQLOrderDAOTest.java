package tests;

import com.example.demo1.models.CartItem;
import com.example.demo1.models.Product;
import com.example.demo1.DAO.OrderDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.ConnectionPoolFactory;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;
import com.example.demo1.connection.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SQLOrderDAOTest {

    @Test
    void createOrder() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        OrderDAO orderDao = (OrderDAO) MainDAOFactory.produce("order");
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(new Product(11,"as","1","category1",new byte[10]),3));
        orderDao.CreateOrder("address",cart);
    }
}