package com.example.demo1.factories;

import com.example.demo1.connection.ConnectionPool;


public class ConnectionPoolFactory {

    private final static ConnectionPoolFactory factory = new ConnectionPoolFactory();

    private final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionPoolFactory() {}

    public static ConnectionPoolFactory getInstance(){
        return factory;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }
}
