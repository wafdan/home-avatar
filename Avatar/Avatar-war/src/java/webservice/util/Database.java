/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Database {

     private Connection connection;
     private String userName = "avatar";
     private String passWord = "avatar";
     private String hostName = "localhost";
     private String dbName = "avatar";

     public Database() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + hostName + ":3306/" + dbName + "?user=" + userName + "&password=" + passWord);
        } catch (Exception e) {
        }
     }

     public Connection getConnection() {
        return connection;
     }

     public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
        }
     }
}
