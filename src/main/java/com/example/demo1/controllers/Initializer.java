package com.example.demo1.controllers;

import com.example.demo1.connection.ConnectionPool;
import com.example.demo1.factories.ConnectionPoolFactory;
import jakarta.servlet.ServletException;

import java.sql.SQLException;

public class Initializer {
    private static volatile boolean ININTIALIZED = false;
    public static void initialize() throws ServletException {
        if(ININTIALIZED) return;
        try {
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.CreateConnections();
            ININTIALIZED = true;
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
