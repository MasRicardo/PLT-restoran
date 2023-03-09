/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author masrc
 */
public class mysql {
    public static Connection connect;
    private static String DB_HOST;
    private static String DB_USER;
    private static String DB_PASS;
    
    public static Connection getConnection() throws SQLException {
        if(connect == null) {
            Driver driver = new Driver();
            
            connect = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
        }
        
        return connect;
    }

    /**
     *
     */
    public mysql() {
        mysql.DB_PASS = "";
        mysql.DB_USER = "root";
        mysql.DB_HOST = "jdbc:mysql://localhost:3306/db_padang_kashimura";
    }
}
