/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.model.Facility;
import webservice.util.Database;
import Support.EmailSender;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import webservice.model.User;

/**
 *
 * @author USER
 */
@WebService()
public class WSAvatar {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getFacility")
    public List<Facility> getFacility(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        User user = new User(username, password);

        if(user.authenticateUser())
        {
            List<Facility> lf = new ArrayList<Facility>();
            Database db;
            try {
                db = new Database();

                try {
                    String sql = "SELECT * FROM `accomodation`";
                    PreparedStatement ps = db.getConnection().prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        Facility f = new Facility();
                        f.setProduct_id(rs.getString("product_id"));
                        f.setProduct_type(rs.getString("product_type"));
                        f.setProduct_description(rs.getString("description"));
                        f.setMax_pax(rs.getInt("max_pax"));
                        f.setNormal_entry((rs.getTime("normal_entry")).toString());
                        f.setNormal_exit((rs.getTime("normal_exit")).toString());
                        f.setWeekday_rate(rs.getDouble("weekday_rate"));
                        f.setWeekend_rate(rs.getDouble("weekend_rate"));
                        f.setTolerate_early((rs.getTime("tolerance_early")).toString());
                        f.setTolerate_late((rs.getTime("tolerance_late")).toString());

                        lf.add(f);
                    }
                    return lf;
                } catch (Exception e) {
                    return null;

                } finally {
                    db.closeConnection();
                    db = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(WSAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "confirmPayment")
    public String confirmPayment(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password, @WebParam(name = "reservation_id")
    int reservation_id, @WebParam(name = "payment_date")
    String payment_date, @WebParam(name = "payment_method")
    String payment_method, @WebParam(name = "payment_bank")
    String payment_bank, @WebParam(name = "account_number")
    String account_number, @WebParam(name = "amount")
    double amount) {
        //TODO write your implementation code here:

        String[] paymentdate = payment_date.split("-");

        java.util.Date today = new java.util.Date();
        java.sql.Timestamp now = new java.sql.Timestamp(today.getTime());
        java.sql.Date now2 = new java.sql.Date(Integer.parseInt(paymentdate[0]) - 1900, Integer.parseInt(paymentdate[1]) - 1, Integer.parseInt(paymentdate[2]));
        //System.out.println(now.toString());

        User user = new User(username, password);

        if(user.authenticateUser()) {
            //Payment p = new Payment(reservation_id, now, "", payment_date, payment_method, payment_bank, account_number, amount);

            Database db;
            try {
                db = new Database();

                try {
                    String sql = "SELECT * FROM `reservation` WHERE `reservation_id` = ? AND `username` like ?";
                    PreparedStatement ps = db.getConnection().prepareStatement(sql);
                    ps.setInt(1, reservation_id);
                    ps.setString(2, username);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {

                        String sql2 = "INSERT INTO `payment` (`reservation_id`,`confirm_time`,`username`,`payment_date`,`payment_method`,`payment_bank`,`account_number`,`amount`) VALUES (?,?,?,?,?,?,?,?)";
                        PreparedStatement ps2 = db.getConnection().prepareStatement(sql2);
                        ps2.setInt(1, reservation_id);
                        ps2.setTimestamp(2, now);
                        ps2.setString(3, null);
                        ps2.setDate(4, now2);
                        ps2.setString(5, payment_method);
                        ps2.setString(6, payment_bank);
                        ps2.setString(7, account_number);
                        ps2.setDouble(8, 10000);

                        ps2.executeUpdate();

                        return "Success";
                    }
                    else {
                        return "Error";
                    }
                } catch (Exception e) {
                    return e.getMessage();

                } finally {
                    db.closeConnection();
                    db = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(WSAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return ex.getMessage();
            }
        }
        else {
            return "Failed";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reserveFacility")
    public String reserveFacility(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password, @WebParam(name = "product_id")
    String product_id, @WebParam(name = "entry_date")
    String entry_date, @WebParam(name = "exit_date")
    String exit_date, @WebParam(name = "total_item")
    int total_item) {
        //TODO write your implementation code here:
        String[] entrydate = entry_date.split("-");
        String[] exitdate = exit_date.split("-");

        java.util.Date today = new java.util.Date();
        java.sql.Timestamp now = new java.sql.Timestamp(today.getTime());
        java.sql.Date entry = new java.sql.Date(Integer.parseInt(entrydate[0]) - 1900, Integer.parseInt(entrydate[1]) - 1, Integer.parseInt(entrydate[2]));
        java.sql.Date exit = new java.sql.Date(Integer.parseInt(exitdate[0]) - 1900, Integer.parseInt(exitdate[1]) - 1, Integer.parseInt(exitdate[2]));

        User user = new User(username, password);

        if(user.authenticateUser()) {

            Database db;
            try {
                db = new Database();

                try {
                    String sql2 = "SELECT * FROM `room` WHERE (`product_id` = ?)";
                    PreparedStatement ps2 = db.getConnection().prepareStatement(sql2);
                    ps2.setString(1, product_id);

                    ResultSet rs2 = ps2.executeQuery();

                    int count_available = 0;
                    ArrayList<String> list_room = new ArrayList();

                    while(rs2.next())
                    {
                        String temp_room = rs2.getString("room_no");
                        list_room.add(temp_room);
                        count_available += 1;
                    }

                    String sql = "SELECT * FROM `room_reservation` , `room` WHERE (`room_reservation`.`room_no` = `room`.`room_no`) AND (`room`.`product_id` = ?) AND (((`entry_date` < ? ) AND  (`exit_date` > ?)) OR ((`entry_date` < ? ) AND  (`exit_date` > ?)))";
                    PreparedStatement ps = db.getConnection().prepareStatement(sql);
                    ps.setString(1, product_id);
                    ps.setDate(2, entry);
                    ps.setDate(3, entry);
                    ps.setDate(4, exit);
                    ps.setDate(5, exit);

                    ResultSet rs = ps.executeQuery();

                    int count_used = 0;

                    while(rs.next())
                    {
                        String temp_room = rs.getString("room_no");
                        list_room.remove(temp_room);
                        count_used += 1;
                    }

                    if ((count_available - count_used) >= total_item) {

                        String sql_reservation = "INSERT INTO `reservation` (`is_onspot`,`username`,`note`) VALUES (?,?,?)";
                        PreparedStatement ps_reservation = db.getConnection().prepareStatement(sql_reservation);
                        ps_reservation.setInt(1, 0);
                        ps_reservation.setString(2, username);
                        ps_reservation.setString(3, "TRIP");
                        ps_reservation.executeUpdate();

                        ResultSet rs_reservation = ps_reservation.getGeneratedKeys();
                        rs_reservation.next();

                        int reservation_id = rs_reservation.getInt(1);
                        int reservation_item_id = -1;

                        String sql_price = "SELECT * FROM `accomodation` WHERE (`product_id` = ?)";
                        PreparedStatement ps_price = db.getConnection().prepareStatement(sql_price);
                        ps_price.setString(1, product_id);

                        ResultSet rs_price = ps_price.executeQuery();
                        rs_price.next();

                        double weekday_rate = rs_price.getDouble("weekday_rate");
                        double weekend_rate = rs_price.getDouble("weekend_rate");
                        double total = 0;

                        java.sql.Date temp = new java.sql.Date(Integer.parseInt(entrydate[0]) - 1900, Integer.parseInt(entrydate[1]) - 1, Integer.parseInt(entrydate[2]));

                        while (temp.compareTo(exit) <= 0) {
                            if((temp.getDay() == 6) || temp.getDay() == 0) {
                                total += weekend_rate;
                            }
                            else {
                                total += weekday_rate;
                            }
                            temp.setDate(temp.getDate() + 1);
                        }

                        for (int i = 0; i < total_item; i++) {

                            String sql_reservation_item = "INSERT INTO `reservation_item` (`reservation_id`,`reservation_time`,`price`,`DTYPE`) VALUES (?,?,?,?)";
                            PreparedStatement ps_reservation_item = db.getConnection().prepareStatement(sql_reservation_item);
                            ps_reservation_item.setInt(1, reservation_id);
                            ps_reservation_item.setTimestamp(2, now);
                            ps_reservation_item.setDouble(3, total);
                            ps_reservation_item.setString(4, "T");
                            ps_reservation_item.executeUpdate();

                            ResultSet rs_reservation_item = ps_reservation_item.getGeneratedKeys();
                            rs_reservation_item.next();

                            reservation_item_id = rs_reservation_item.getInt(1);

                            String temp_room = list_room.get(i);

                            String sql_room_reservation = "INSERT INTO `room_reservation` (`reservation_item_id`, `room_no`,`entry_date`,`exit_date`) VALUES (?,?,?,?)";
                            PreparedStatement ps_room_reservation = db.getConnection().prepareStatement(sql_room_reservation);
                            ps_room_reservation.setInt(1, reservation_item_id);
                            ps_room_reservation.setString(2, temp_room);
                            ps_room_reservation.setDate(3, entry);
                            ps_room_reservation.setDate(4, exit);
                            ps_room_reservation.executeUpdate();

                        }

                        String email = user.getEmail();
                        String body = "Welcome, "+ user.getUsername() +"\nYour Room Reservation has been successfully made.\n\n" +
                        "Please take note of your reservation ID to access your reservation.\n" +
                        "Reservation ID : " + reservation_id + "\n\n" +
                        "Thank You\n";

                        EmailSender.sendEmail(email, "avatarhomeapp@gmail.com", "", "Hotel Room Reservation", body);

                        return "Available " + reservation_id;
                    }
                    else {
                        return "Unavailable" + " " + (count_available - count_used);
                    }
                } catch (Exception e) {
                    return e.getMessage();

                } finally {
                    db.closeConnection();
                    db = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(WSAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return ex.getMessage();
            }
        }
        else {
            return "Failed";
        }
    }
}
