package com.example.demo1.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final int INITIAL_POOL_SIZE = 10;

    private List<Connection> connections;

    public ConnectionPool() {}

    public void CreateConnections() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connections = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connections.add(connection);
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        if (connections.isEmpty()) {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connections.remove(connections.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            for (Connection connection : connections) {
                connection.close();
            }
            connections.clear();
        } catch (SQLException ex) {
            super.finalize();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static void closeStatement(Statement statement) throws SQLException {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static void rollbackQuery(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            System.out.println("Rollback error");
        }
    }

}

