package com.gui.projectmanagementserver.database;

import com.mysql.cj.jdbc.DatabaseMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Client {
    @SuppressWarnings("exports")
    public static Connection getConnection(){
        Connection c = null ;

        com.mysql.cj.jdbc.Driver driver;
        try {
            // đăng kí MySQL Driver với DriverManager
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            // các thông số
            String url = "jdbc:mysql://127.0.0.1:3306/manager_project";
            String username = "root";
            String password = "" ;

            // tạo kết nối
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    @SuppressWarnings("exports")
    public static void closeConnection(Connection c) {
        try {
            if (c != null ) {
                c.close();
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
    }

    @SuppressWarnings("exports")
    public static void printInfor(Connection c ) throws SQLException {
        if (c!= null) {
            DatabaseMetaData mtdt = (DatabaseMetaData) c.getMetaData();
            System.out.println(mtdt.getDatabaseProductName());
            System.out.println(mtdt.getDatabaseProductVersion());
        }
    }

}

