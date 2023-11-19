package com.example.demo1.DAO;

import com.example.demo1.factories.ConnectionPoolFactory;
import com.example.demo1.models.Product;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.connection.ConnectionPool;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IDAO {
    private static final String GetProductsByCat = "SELECT pro_id, pro_name, pro_price, pro_discount, cat_name, pro_image FROM product " +
            "JOIN product_category ON cat_id = pro_cat WHERE cat_name = ?";

    private static final String AddDiscount = "UPDATE product " +
            "SET pro_discount = ? " +
            "WHERE pro_id = ?";

    private static final String GetAllProducts = "SELECT pro_id, pro_name, pro_price, pro_discount, cat_name, pro_image FROM product " +
            "JOIN product_category ON cat_id = pro_cat";

    private static final String GetProductById = "SELECT pro_id, pro_name, pro_price, pro_discount, cat_name, pro_image " +
            "FROM product " +
            "JOIN product_category ON cat_id = pro_cat " +
            "WHERE pro_id = ?";

    private static final String AddProduct = "INSERT INTO product " +
            "(pro_id, pro_name, pro_price, pro_discount, pro_cat, pro_image)" +
            "VALUES " +
            "(null, ?, ?, DEFAULT, ?, ?)";

    private static final String GetCategoryByName = "SELECT cat_id FROM product_category WHERE cat_name = ?";

    public List<Product> GetProductListByCat(String category) throws DAOException {
        List<Product> list = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = connectionPool.getConnection();
            ps = con.prepareStatement(GetProductsByCat);
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                Blob blob = rs.getBlob("pro_image");
                if (blob == null)
                    continue;
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(5),
                        blob.getBytes(1,(int)blob.length())));
            }
        } catch (SQLException e) {
            ConnectionPool.rollbackQuery(con);
            throw new DAOException("Sql error",e);
        } finally {
            try{
                connectionPool.releaseConnection(con);
                ConnectionPool.closeResultSet(rs);
                ConnectionPool.closePreparedStatement(ps);
            } catch (SQLException e) {
                throw new DAOException("SQl connection close error", e);
            }
        }
        return list;
    }

    public List<Product> GetAllProduct() throws DAOException {
        List<Product> list = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(GetAllProducts);
            while (rs.next()) {
                Blob blob = rs.getBlob("pro_image");
                if (blob == null)
                    continue;
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(5),
                        blob.getBytes(1,(int)blob.length())));
            }
        } catch (SQLException e) {
            ConnectionPool.rollbackQuery(con);
            throw new DAOException("Sql error",e);
        } finally {
            try{
                connectionPool.releaseConnection(con);
                ConnectionPool.closeResultSet(rs);
                ConnectionPool.closeStatement(st);
            } catch (SQLException e) {
                throw new DAOException("SQl connection close error", e);
            }
        }
        return list;
    }

    public boolean SetDiscount(int productId, int discountSize) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            ps = con.prepareStatement(AddDiscount);
            ps.setInt(1, discountSize);
            ps.setInt(2, productId);
            int rowNumber = ps.executeUpdate();
            if (rowNumber == 0) {
                throw new DAOException("Discount add exception");
            }
        } catch (SQLException e) {
            ConnectionPool.rollbackQuery(con);
            throw new DAOException("Sql error",e);
        } finally {
            try{

                connectionPool.releaseConnection(con);
                ConnectionPool.closePreparedStatement(ps);
            } catch (SQLException e) {
                throw new DAOException("SQl connection close error", e);
            }
        }
        return true;
    }

    public boolean AddProduct(String name,String price, String category, InputStream file) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        int categoryNumber;
        try {
            con = connectionPool.getConnection();
            ps = con.prepareStatement(GetCategoryByName);
            ps.setString(1,category);
            rs = ps.executeQuery();
            if (rs.next()){
                categoryNumber = rs.getInt(1);
            }else {
                throw new DAOException("Incorrect category name");
            }
            ps = con.prepareStatement(AddProduct);
            ps.setString(1,name);
            ps.setString(2,price);
            ps.setInt(3,categoryNumber);
            ps.setBlob(4,file);
            int rowNumber = ps.executeUpdate();
            if (rowNumber == 0) {
                throw new DAOException("Product add exception");
            }
        } catch (SQLException e) {
            ConnectionPool.rollbackQuery(con);
            throw new DAOException("Sql error",e);
        } finally {
            try{
                connectionPool.releaseConnection(con);
                ConnectionPool.closeResultSet(rs);
                ConnectionPool.closePreparedStatement(ps);
            } catch (SQLException e) {
                throw new DAOException("SQl connection close error", e);
            }
        }
        return true;
    }

    public Product GetProductById(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Product product = null;
        try {
            con = connectionPool.getConnection();
            ps = con.prepareStatement(GetProductById);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getBytes(6));
            }
        } catch (SQLException e) {
            ConnectionPool.rollbackQuery(con);
            throw new DAOException("Sql error",e);
        } finally {
            try{
                connectionPool.releaseConnection(con);
                ConnectionPool.closeResultSet(rs);
                ConnectionPool.closePreparedStatement(ps);
            } catch (SQLException e) {
                throw new DAOException("SQl connection close error", e);
            }
        }
        return product;
    }
}
