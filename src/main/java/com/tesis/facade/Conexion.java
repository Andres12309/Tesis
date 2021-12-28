/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.facade;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Personal
 */
public class Conexion {

    //PRODUCCION
//    private String servidorOracleP = "jdbc:oracle:thin:@172.16.20.105:1521:TERCON";
//    private String usernameP = "tercon";
//    private String passwordP = "tercon";
    //TALLER
    private String servidorMYSQL = "jdbc:mysql://mysql-andres.alwaysdata.net:3306/andres_helppeople?zeroDateTimeBehavior=convertToNull";
    private String username = "andres";
    private String password = "linuxhack1@";

    private Connection connection;
    private Boolean taller;

    public Conexion(Boolean taller) {
        this.taller = taller;
        this.createConnection();
    }

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = null;
            this.connection = DriverManager.getConnection(servidorMYSQL, username, password);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }//End get Connection
}
