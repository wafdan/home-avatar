/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.util.Database;
import Support.EncMd5;

/**
 *
 * @author USER
 */
public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        try {
            this.password = EncMd5.MD5(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean authenticateUser()
    {
        try {
            Database db = new Database();
            String sql = "select * from customer where username like ? and password like ?";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getEmail() {
        try {
            Database db = new Database();
            String sql = "select * from customer where username like ? and password like ?";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getString("email");
            }
            else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
