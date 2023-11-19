package tests;

import com.example.demo1.DAO.ProductDAO;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.factories.ConnectionPoolFactory;
import com.example.demo1.factories.DAOFactory.MainDAOFactory;
import com.example.demo1.connection.ConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SQLProductDAOTest {

    @Test
    void getProductListByCat() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
        assertNotNull(productDao.GetProductListByCat("Phone"));
    }

    @Test
    void getAllProduct() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

            connectionPool.CreateConnections();

        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
        assertNotNull(productDao.GetAllProduct());
    }

    @Test
    void getProductById() throws DAOException {
        try{
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.CreateConnections();
        }catch (Exception ex){
            Assertions.fail("Problem with connection");
        }
        ProductDAO productDao = (ProductDAO) MainDAOFactory.produce("product");
        assertNotNull(productDao.GetProductById(11));
    }
}