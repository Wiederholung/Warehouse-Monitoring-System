package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnector {
    private static final String JDBC_DRIVER  = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://wada.metattri.com:3360/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "C 2998-mysql";
    private Connection conn = null;
    private Statement stmt = null;

    public DBConnector() {
        try {
            Class.forName(JDBC_DRIVER );
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public Statement getStatement() {
        return stmt;
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
    }
}
