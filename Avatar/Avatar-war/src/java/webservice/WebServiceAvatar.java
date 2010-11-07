/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import AvatarEntity.exceptions.IllegalOrphanException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import Support.EmailSender;
import Support.EncMd5;
import AvatarEntity.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import webservice.model.Facility;

/**
 *
 * @author USER
 */
@WebService()
public class WebServiceAvatar {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAccomodation")
    public List<Facility> getAccomodation(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        if(c != null)
        {
            try {
                if ((EncMd5.MD5(password)).equals(c.getPassword())) {
                    // password customer benar

                    AccomodationJpaController ajpa = new AccomodationJpaController();
                    List<Accomodation> rooms = new ArrayList<Accomodation>();
                    rooms = ajpa.findAccomodationEntities();

                    List<Facility> lf = new ArrayList<Facility>();

                    for(int i = 0; i < rooms.size(); i++)
                    {
                        Facility f = new Facility();
                        f.setProduct_id(rooms.get(i).getProductId());
                        f.setProduct_type(rooms.get(i).getProductType());

                        Time time1 = new Time(rooms.get(i).getNormalEntry().getTime());
                        Time time2 = new Time(rooms.get(i).getNormalExit().getTime());
                        Time time3 = new Time(rooms.get(i).getToleranceEarly().getTime());
                        Time time4 = new Time(rooms.get(i).getToleranceLate().getTime());

                        f.setNormal_entry(time1.toString());
                        f.setNormal_exit(time2.toString());
                        f.setTolerate_early(time3.toString());
                        f.setTolerate_late(time4.toString());
                        f.setMax_pax(rooms.get(i).getMaxPax());
                        f.setProduct_description(rooms.get(i).getDescription());
                        f.setWeekday_rate(rooms.get(i).getWeekdayRate());
                        f.setWeekend_rate(rooms.get(i).getWeekendRate());

                        lf.add(f);
                    }
                    return lf;
                } else {
                    return null;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
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
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        if(c != null)
        {
            try {
                if ((EncMd5.MD5(password)).equals(c.getPassword())) {
                    // password customer benar

                    ReservationJpaController rjpa = new ReservationJpaController();
                    Reservation r = rjpa.findReservation(reservation_id);

                    if(r != null)
                    {
                        PaymentJpaController pjpa = new PaymentJpaController();

                        Payment p = new Payment();

                        String[] paymentdate = payment_date.split("-");
                        java.util.Date today = new java.util.Date();
                        java.util.Date now = new java.util.Date(Integer.parseInt(paymentdate[0]) - 1900, Integer.parseInt(paymentdate[1]) - 1, Integer.parseInt(paymentdate[2]));

                        p.setAccountNumber(account_number);
                        p.setAmount(amount);
                        p.setPaymentDate(now);
                        p.setConfirmTime(new java.util.Date());
                        p.setPaymentBank(payment_bank);
                        p.setPaymentMethod(payment_method);
                        p.setReservationId(r);
                        try {
                            pjpa.create(p);
                            return "Success";
                        } catch (IllegalOrphanException ex) {
                            Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                            return "Failed";
                        }                        
                    }
                    else
                    {
                        return "Failed";
                    }
                    //return "a";
                } else
                {
                    return "Failed";
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return "Failed";
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return "Failed";
            }
        }
        else
        {
            return "Failed";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reserveRoom")
    public String reserveRoom(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password, @WebParam(name = "product_id")
    String product_id, @WebParam(name = "entry_date")
    String entry_date, @WebParam(name = "exit_date")
    String exit_date, @WebParam(name = "total_item")
    int total_item) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        if(c != null)
        {
            try {
                if ((EncMd5.MD5(password)).equals(c.getPassword())) {
                    // password customer benar
                    String[] entrydate = entry_date.split("-");
                    String[] exitdate = exit_date.split("-");

                    java.util.Date entry = new java.util.Date(Integer.parseInt(entrydate[0]) - 1900, Integer.parseInt(entrydate[1]) - 1, Integer.parseInt(entrydate[2]));
                    java.util.Date exit = new java.util.Date(Integer.parseInt(exitdate[0]) - 1900, Integer.parseInt(exitdate[1]) - 1, Integer.parseInt(exitdate[2]));

                    RoomJpaController rojpa = new RoomJpaController();
                    List<Room> rooms = rojpa.findUnused(product_id, entry, exit);

                    if(rooms.size() >= total_item)
                    {
                        AccomodationJpaController ajpa = new AccomodationJpaController();
                        Accomodation acc = ajpa.findAccomodation(product_id);

                        double total_price = 0;
                        
                        java.util.Date temp = new java.util.Date(Integer.parseInt(entrydate[0]) - 1900, Integer.parseInt(entrydate[1]) - 1, Integer.parseInt(entrydate[2]));

                        while(temp.compareTo(exit) <= 0)
                        {
                            if((temp.getDay() == 6) || temp.getDay() == 0) {
                                total_price += acc.getWeekendRate();
                            }
                            else {
                                total_price += acc.getWeekdayRate();
                            }
                            temp.setDate(temp.getDate() + 1);
                        }

                        ReservationJpaController resjpa = new ReservationJpaController();
                        Reservation res = new Reservation();
                        res.setIsOnspot(false);
                        res.setNote("TRIP");
                        res.setUsername(c);

                        resjpa.create(res);

                        ReservationItemJpaController resitemjpa = new ReservationItemJpaController();
                        RoomReservationJpaController roomresjpa = new RoomReservationJpaController();

                        for(int i = 0; i < total_item; i++)
                        {
//                            ReservationItem resitem = new ReservationItem();
//                            resitem.setReservationId(res);
//                            resitem.setReservationTime(new Date());
//                            resitem.setPrice(total_price);

                            RoomReservation roomres = new RoomReservation();
                            roomres.setReservationId(res);
//                            roomres.setReservationItemId(resitem.getReservationItemId());
                            roomres.setEntryDate(entry);
                            roomres.setExitDate(exit);
                            roomres.setPrice(total_price);
                            roomres.setRoomNo(rooms.get(i));
                            roomres.setReservationTime(new Date());

                            roomresjpa.create(roomres);
                        }

                        String email = c.getEmail();
                        String body = "Welcome, "+ c.getUsername() +"\nYour Room Reservation has been successfully made.\n\n" +
                        "Please take note of your reservation ID to access your reservation.\n" +
                        "Reservation ID : " + res.getReservationId() + "\n\n" +
                        "Thank You\n";
                        try {
                            EmailSender.sendEmail(email, "avatarhomeapp@gmail.com", "", "Hotel Room Reservation", body);
                        } catch (Exception ex) {
                            Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        return res.getReservationId().toString();
                    }
                    else
                    {
                        return "Unavailable";
                    }
//                    String result = new String("");
//                    for(int i = 0; i < rooms.size(); i++)
//                    {
//                        result.concat(rooms.get(i).getRoomNo());
//                        result.concat(" ");
//                        //System.out.println(rooms.get(i).getRoomNo());
//                    }

                    //return rooms.get(0).getRoomNo();

                } else {
                    return "Failed";
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return "Failed";
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return "Failed";
            }
        }
        else
        {
            return "Failed";
        }
    }

}
