package com.example.demo1.DAO;

import com.example.demo1.factories.ConnectionPoolFactory;
import com.example.demo1.models.User;
import com.example.demo1.exceptions.DAOException;
import com.example.demo1.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IDAO {

    private static String registerNewUser = "INSERT INTO user (idUser, UserLogin, UserPasswordHash,role,ban) Values (null, ?,?,?,?)";
    private static String checkIfUserExist = "Select * from user where userLogin = ? and UserPasswordHash = ?";
    private static String getUserId = "Select idUser, role from user where UserLogin = ?";

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public User signIn(String login, String password) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        User user = new User();
        try {
            con = connectionPool.getConnection();
            ps = con.prepareStatement(checkIfUserExist);
            ps.setString(1, login);
            ps.setString(2, User.getHashSha512Password(password));
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Incorrect login or password");
            }

            ps = con.prepareStatement(getUserId);
            ps.setString(1,login);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Incorrect login or password");
            }else
            {
                user.setId(rs.getInt(1));
                user.setRole(rs.getString(2));
            }
            ps.setInt(1,user.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                if (rs.getBoolean(1)){
                    throw new DAOException("You were banned");
                }
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

        return user;
    }

    public int registration(User user) throws DAOException {
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        int result = -1;
        try {
            con = connectionPool.getConnection();
            ps = con.prepareStatement(registerNewUser);
            ps.setString(1, user.getLogin());
            ps.setString(2, User.getHashSha512Password(user.getPassword()));
            ps.setString(3, "User");
            ps.setString(4, "0");
            ps.executeUpdate();
            ps = con.prepareStatement(getUserId);
            ps.setString(1,user.getLogin());
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Incorrect login or password");
            }else
            {
                result = rs.getInt(1);
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
        return result;
    }
}
