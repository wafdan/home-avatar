/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import Support.EncMd5;
import AvatarEntity.*;
import AvatarEntity.exceptions.IllegalOrphanException;
import Support.EmailSender;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import webservice.model.*;

/**
 *
 * @author USER
 */
@WebService()
public class WSAvatarMobile {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public int login(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        if(c != null)
        {
            try {
                if ((EncMd5.MD5(password)).equals(c.getPassword())) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WSAvatarMobile.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WSAvatarMobile.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        else
        {
            return 0;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalRoom")
    public int getTotalRoom(@WebParam(name = "username")
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

                    return rooms.size();

                } else {
                    return -1;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRoomType")
    public String getRoomType(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        AccomodationJpaController ajpa = new AccomodationJpaController();
        List<Accomodation> rooms = new ArrayList<Accomodation>();

        rooms = ajpa.findAccomodationEntities(1, index);

        return rooms.get(0).getProductType();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRoomID")
    public String getRoomID(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        AccomodationJpaController ajpa = new AccomodationJpaController();
        List<Accomodation> rooms = new ArrayList<Accomodation>();

        rooms = ajpa.findAccomodationEntities(1, index);

        return rooms.get(0).getProductId();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRoomDesc")
    public String getRoomDesc(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        AccomodationJpaController ajpa = new AccomodationJpaController();
        List<Accomodation> rooms = new ArrayList<Accomodation>();

        rooms = ajpa.findAccomodationEntities(1, index);

        return rooms.get(0).getDescription();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRoomWeekdayRate")
    public double getRoomWeekdayRate(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        AccomodationJpaController ajpa = new AccomodationJpaController();
        List<Accomodation> rooms = new ArrayList<Accomodation>();

        rooms = ajpa.findAccomodationEntities(1, index);

        return rooms.get(0).getWeekdayRate();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRoomWeekendRate")
    public double getRoomWeekendRate(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        AccomodationJpaController ajpa = new AccomodationJpaController();
        List<Accomodation> rooms = new ArrayList<Accomodation>();

        rooms = ajpa.findAccomodationEntities(1, index);

        return rooms.get(0).getWeekendRate();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalHall")
    public int getTotalHall(@WebParam(name = "username")
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

                    HallJpaController hjpa = new HallJpaController();
                    List<Hall> halls = new ArrayList<Hall>();
                    halls = hjpa.findHallEntities();

                    return halls.size();

                } else {
                    return -1;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHallType")
    public String getHallType(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        HallJpaController hjpa = new HallJpaController();
        List<Hall> halls = new ArrayList<Hall>();

        halls = hjpa.findHallEntities(1, index);

        return halls.get(0).getProductType();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHallID")
    public String getHallID(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        HallJpaController hjpa = new HallJpaController();
        List<Hall> halls = new ArrayList<Hall>();

        halls = hjpa.findHallEntities(1, index);

        return halls.get(0).getProductId();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHallDesc")
    public String getHallDesc(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        HallJpaController hjpa = new HallJpaController();
        List<Hall> halls = new ArrayList<Hall>();

        halls = hjpa.findHallEntities(1, index);

        return halls.get(0).getDescription();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHallNormalRate")
    public double getHallNormalRate(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        HallJpaController hjpa = new HallJpaController();
        List<Hall> halls = new ArrayList<Hall>();

        halls = hjpa.findHallEntities(1, index);

        return halls.get(0).getNormalRate();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHallOverchargeRate")
    public double getHallOverchargeRate(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        HallJpaController hjpa = new HallJpaController();
        List<Hall> halls = new ArrayList<Hall>();

        halls = hjpa.findHallEntities(1, index);

        return halls.get(0).getOverchargeRate();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalOther")
    public int getTotalOther(@WebParam(name = "username")
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

                    OtherServicesJpaController ojpa = new OtherServicesJpaController();
                    List<OtherServices> others = new ArrayList<OtherServices>();
                    others = ojpa.findOtherServicesEntities();

                    return others.size();

                } else {
                    return -1;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOtherType")
    public String getOtherType(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        OtherServicesJpaController ojpa = new OtherServicesJpaController();
        List<OtherServices> others = new ArrayList<OtherServices>();

        others = ojpa.findOtherServicesEntities(1, index);

        return others.get(0).getProductType();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOtherID")
    public String getOtherID(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        OtherServicesJpaController ojpa = new OtherServicesJpaController();
        List<OtherServices> others = new ArrayList<OtherServices>();

        others = ojpa.findOtherServicesEntities(1, index);

        return others.get(0).getProductId();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOtherDesc")
    public String getOtherDesc(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        OtherServicesJpaController ojpa = new OtherServicesJpaController();
        List<OtherServices> others = new ArrayList<OtherServices>();

        others = ojpa.findOtherServicesEntities(1, index);

        return others.get(0).getDescription();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOtherPrice")
    public double getOtherPrice(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        OtherServicesJpaController ojpa = new OtherServicesJpaController();
        List<OtherServices> others = new ArrayList<OtherServices>();

        others = ojpa.findOtherServicesEntities(1, index);

        return others.get(0).getUnitPrice();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalLayout")
    public int getTotalLayout(@WebParam(name = "username")
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

                    LayoutJpaController ljpa = new LayoutJpaController();
                    List<Layout> layouts = new ArrayList<Layout>();
                    layouts = ljpa.findLayoutEntities();

                    return layouts.size();

                } else {
                    return -1;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLayoutNo")
    public int getLayoutNo(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        LayoutJpaController ljpa = new LayoutJpaController();
        List<Layout> layouts = new ArrayList<Layout>();

        layouts = ljpa.findLayoutEntities(1, index);

        return layouts.get(0).getLayoutNo();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLayoutName")
    public String getLayoutName(@WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:

        LayoutJpaController ljpa = new LayoutJpaController();
        List<Layout> layouts = new ArrayList<Layout>();

        layouts = ljpa.findLayoutEntities(1, index);

        return layouts.get(0).getLayoutName();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNameHotel")
    public String getNameHotel() {
        //TODO write your implementation code here:
        ProfileJpaController pjpa = new ProfileJpaController();
        List<Profile> p = pjpa.findProfileEntities();

        return p.get(0).getHotelName();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHotelName")
    public String getHotelName(@WebParam(name = "username")
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

                    ProfileJpaController pjpa = new ProfileJpaController();
                    List<Profile> p = pjpa.findProfileEntities();

                    return p.get(0).getHotelName();
                }
                else
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
    @WebMethod(operationName = "getHotelDesc")
    public String getHotelDesc() {
        //TODO write your implementation code here:
        ProfileJpaController pjpa = new ProfileJpaController();
        List<Profile> p = pjpa.findProfileEntities();

        return p.get(0).getHotelDescription();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHotelAddress")
    public String getHotelAddress() {
        //TODO write your implementation code here:
        ProfileJpaController pjpa = new ProfileJpaController();
        List<Profile> p = pjpa.findProfileEntities();

        String address = p.get(0).getHotelAddress1() + " , " + p.get(0).getHotelAddress2() + " , " + p.get(0).getHotelCity() + " , " + p.get(0).getHotelCountry();

        return address;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHotelEmail")
    public String getHotelEmail() {
        //TODO write your implementation code here:
        ProfileJpaController pjpa = new ProfileJpaController();
        List<Profile> p = pjpa.findProfileEntities();

        return p.get(0).getHotelEmail();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHotelPhone")
    public String getHotelPhone() {
        //TODO write your implementation code here:
        ProfileJpaController pjpa = new ProfileJpaController();
        List<Profile> p = pjpa.findProfileEntities();

        return p.get(0).getHotelPhone();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getHotelFax")
    public String getHotelFax() {
        //TODO write your implementation code here:
        ProfileJpaController pjpa = new ProfileJpaController();
        List<Profile> p = pjpa.findProfileEntities();

        return p.get(0).getHotelFax();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalReservation")
    public int getTotalReservation(@WebParam(name = "username")
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

                    ReservationJpaController rjpa = new ReservationJpaController();
                    List<Reservation> r = rjpa.findOnlineReservationByName(c.getName());

                    return r.size();
                }
                else
                {
                    return -1;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WebServiceAvatar.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getReservationID")
    public int getReservationID(@WebParam(name = "username")
    String username, @WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        ReservationJpaController rjpa = new ReservationJpaController();
        List<Reservation> r = rjpa.findOnlineReservationByName(c.getName());

        return r.get(index).getReservationId();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalPayment")
    public double getTotalPayment(@WebParam(name = "username")
    String username, @WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        ReservationJpaController rjpa = new ReservationJpaController();
        List<Reservation> r = rjpa.findOnlineReservationByName(c.getName());

        ReservationItemJpaController rijpa = new ReservationItemJpaController();
        Collection<ReservationItem> ri = new ArrayList<ReservationItem>();
        ri = r.get(index).getReservationItemCollection();

        double total_price = 0;
        for(ReservationItem item : ri)
        {
            total_price += item.getPrice();
        }

        return total_price;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPaymentStatus")
    public String getPaymentStatus(@WebParam(name = "username")
    String username, @WebParam(name = "index")
    int index) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        ReservationJpaController rjpa = new ReservationJpaController();
        List<Reservation> r = rjpa.findOnlineReservationByName(c.getName());

        Payment p = r.get(index).getPayment();

        if(p != null)
        {
            if(p.getUsername() != null)
            {
                return "Verified";
            }
            else
            {
                return "Confirmed";
            }
        }
        else
        {
            return "Not Paid";
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
                        Collection<ReservationItem> ri = new ArrayList<ReservationItem>();
                        ri = r.getReservationItemCollection();
                        double total_price = 0;

                        for(ReservationItem item : ri)
                        {
                            total_price += item.getPrice();
                        }

                        if(amount < total_price)
                        {
                            return "Failed";
                        }
                        else
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

                   if((entry.before(new Date()) || exit.before(new Date())) || exit.before(entry))
                    {
                        return "Failed";
                    }

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
                        res.setNote("");
                        res.setUsername(c);

                        resjpa.create(res);

                        ReservationItemJpaController resitemjpa = new ReservationItemJpaController();
                        RoomReservationJpaController roomresjpa = new RoomReservationJpaController();

                        for(int i = 0; i < total_item; i++)
                        {
                            RoomReservation roomres = new RoomReservation();
                            roomres.setReservationId(res);
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reserveHall")
    public String reserveHall(@WebParam(name = "username")
    String username, @WebParam(name = "password")
    String password, @WebParam(name = "product_id")
    String product_id, @WebParam(name = "use_date")
    String use_date, @WebParam(name = "layout_no")
    int layout_no, @WebParam(name = "attendees")
    int attendees, @WebParam(name = "total_item")
    int total_item) {
        //TODO write your implementation code here:
        CustomerJpaController cjpa = new CustomerJpaController();

        Customer c = cjpa.findCustomer(username.toString());

        if(c != null)
        {
            try {
                if ((EncMd5.MD5(password)).equals(c.getPassword())) {
                    // password customer benar
                    String[] usedate = use_date.split("-");

                    java.util.Date use = new java.util.Date(Integer.parseInt(usedate[0]) - 1900, Integer.parseInt(usedate[1]) - 1, Integer.parseInt(usedate[2]));

                    if(use.before(new Date()))
                    {
                        return "Failed";
                    }

                    VenueJpaController vjpa = new VenueJpaController();
                    List<Venue> venues = vjpa.findUnused(use, layout_no, attendees);

                    if(venues.size() >= total_item)
                    {
                        HallJpaController hjpa = new HallJpaController();
                        Hall h = hjpa.findHall(product_id);

                        double total_price = 0;

                        total_price = attendees * h.getNormalRate();

                        ReservationJpaController resjpa = new ReservationJpaController();
                        Reservation res = new Reservation();
                        res.setIsOnspot(false);
                        res.setNote("");
                        res.setUsername(c);

                        resjpa.create(res);

                        ReservationItemJpaController resitemjpa = new ReservationItemJpaController();
                        HallReservationJpaController hallresjpa = new HallReservationJpaController();

                        for(int i = 0; i < total_item; i++)
                        {
                            HallReservation hallres = new HallReservation();
                            hallres.setReservationId(res);
                            hallres.setBeginTime(h.getStartTime());
                            hallres.setEndTime(h.getEndTime());
                            hallres.setPrice(total_price);
                            hallres.setProductId(h);
                            hallres.setUseDate(use);
                            hallres.setReservationTime(new Date());
                            hallres.setVenueNo(venues.get(i));
                            hallres.setAttendees(attendees);

                            hallresjpa.create(hallres);
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