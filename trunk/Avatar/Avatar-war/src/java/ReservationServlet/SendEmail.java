/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ReservationServlet;

import AvatarEntity.HallReservation;
import AvatarEntity.OtherServicesReservation;
import AvatarEntity.Profile;
import AvatarEntity.ProfileJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.RoomReservation;
import AvatarEntity.Staff;
import AvatarEntity.StaffJpaController;
import KelolaReservasi.MengelolaReservasiController;
import Support.EmailSender;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.management.timer.Timer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author kamoe
 */
@WebServlet(name="SendEmail", urlPatterns={"/backend/SendEmail"})
public class SendEmail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String ref;
        Integer reservId;
        String act;

        ref = request.getParameter("ref");
        reservId =  Integer.parseInt(request.getParameter("id"));
        act = request.getParameter("action");

        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
            MengelolaReservasiController ctrl = new MengelolaReservasiController();
            ProfileJpaController pctrl = new ProfileJpaController();
            Profile hotel = pctrl.findProfile(Boolean.TRUE);

            Reservation res = ctrl.getReservation(reservId);

            String to = res.getUsername().getEmail();
            String from = hotel.getHotelEmail();
            String cc = "";
            String subject = "";
            String body = "";
            String newline = System.getProperty("line.separator");

            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
            Locale locale = Locale.getDefault();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

            if (act != null) {
                if (act.equals("delete")) {
                    subject = hotel.getHotelName() + " [Reservation #"+reservId+"] Reservation Cancellation Due To Expired Payment Time";
                    // email ke customer
                    body = "Your reservation #"+reservId+" has been cancelled due to expired payment time. "+newline;
                    body += newline+"Reservation Id   : "+reservId;
                    body += newline+"Reservation Time : "+formatter.format(res.getReservationTime());
                    body += newline+"Due Date         : "+formatter.format(res.getReservationPaymentLimit());
                    body += newline+"Expired Date     : "+formatter.format(ctrl.getExpiredDate(reservId));
                    body += newline+"Total Price      : "+currencyFormat.format(res.getTotalPrice())+newline;
                    body += newline+newline+"Reservation Item(s) cancelled: ";
                    int i=1;
                    Collection<ReservationItem> col = ctrl.getReservationItemById(reservId);
                    Iterator<ReservationItem> it = col.iterator();
                    while (it.hasNext()) {
                        ReservationItem curRes = it.next();
                        body += newline+i+". ";
                        if (curRes instanceof RoomReservation) {
                            body += "Room "+((RoomReservation) curRes).getRoomNo().getRoomNo()+", "+((RoomReservation) curRes).getRoomNo().getProductId().getProductType();
                            body += newline+"# Entry Date : "+formatter.format(((RoomReservation) curRes).getEntryDate());
                            body += newline+"# Exit Date  : "+formatter.format(((RoomReservation) curRes).getExitDate());
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        } else if (curRes instanceof HallReservation) {
                            body += "Package "+((HallReservation) curRes).getProductId().getProductType()+", Hall "+((HallReservation) curRes).getVenueNo().getVenueNo()+", "+((HallReservation) curRes).getVenueNo().getVenueName();
                            body += newline+"# Usage Date : "+formatter.format(((HallReservation) curRes).getUseDate());
                            body += newline+"# Usage Time : "+timeformatter.format(((HallReservation) curRes).getBeginTime())+" - "+timeformatter.format(((HallReservation) curRes).getEndTime());
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        } else if (curRes instanceof OtherServicesReservation) {
                            body += ((OtherServicesReservation) curRes).getProductId().getProductType()+" x "+((OtherServicesReservation) curRes).getAmount();
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        }
                        i++;
                    }
                    body += newline+newline+"Visit our website to make new reservation";
                    body += newline+newline;
                    body += newline+hotel.getHotelName();
                    body += newline+hotel.getHotelAddress1()+", "+ hotel.getHotelCity();
                    body += newline+hotel.getHotelCountry();
                    body += newline+"Phone : "+hotel.getHotelPhone();
                    body += newline+"Email : "+hotel.getHotelEmail();
                    EmailSender.sendEmail(to, from, cc, subject, body);
                    
                    // email ke pegawai
                    StaffJpaController sc = new StaffJpaController();
                    Staff s = sc.findStaffByUsername(request.getSession().getAttribute("username").toString());
                    to = s.getEmail();
                    
                    body = "You've cancelled reservation #"+reservId+" due to expired payment time. "+newline;
                    body += newline+"Reservation Id   : "+reservId;
                    body += newline+"Customer Name    : "+res.getUsername().getName()+" ("+res.getUsername().getUsername()+")";
                    body += newline+"Customer Address : "+res.getUsername().getAddress1()+", "+res.getUsername().getAddress2();
                    body += newline+"                   "+res.getUsername().getCountry()+", "+res.getUsername().getCountry();
                    body += newline+"Customer Phone   : "+res.getUsername().getName();
                    body += newline+"Customer Email   : "+res.getUsername().getEmail();
                    body += newline+newline+"Reservation Time : "+formatter.format(res.getReservationTime());
                    body += newline+"Due Date         : "+formatter.format(res.getReservationPaymentLimit());
                    body += newline+"Expired Date     : "+formatter.format(ctrl.getExpiredDate(reservId));
                    body += newline+"Total Price      : "+currencyFormat.format(res.getTotalPrice())+newline;
                    body += newline+newline+"Reservation Item(s) cancelled: ";
                    col = ctrl.getReservationItemById(reservId);
                    it = col.iterator();
                    i=1;
                    while (it.hasNext()) {
                        ReservationItem curRes = it.next();
                        body += newline+i+". ";
                        if (curRes instanceof RoomReservation) {
                            body += "Room "+((RoomReservation) curRes).getRoomNo().getRoomNo()+", "+((RoomReservation) curRes).getRoomNo().getProductId().getProductType();
                            body += newline+"# Entry Date : "+formatter.format(((RoomReservation) curRes).getEntryDate());
                            body += newline+"# Exit Date  : "+formatter.format(((RoomReservation) curRes).getExitDate());
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        } else if (curRes instanceof HallReservation) {
                            body += "Package "+((HallReservation) curRes).getProductId().getProductType()+", Hall "+((HallReservation) curRes).getVenueNo().getVenueNo()+", "+((HallReservation) curRes).getVenueNo().getVenueName();
                            body += newline+"# Usage Date : "+formatter.format(((HallReservation) curRes).getUseDate());
                            body += newline+"# Usage Time : "+timeformatter.format(((HallReservation) curRes).getBeginTime())+" - "+timeformatter.format(((HallReservation) curRes).getEndTime());
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        } else if (curRes instanceof OtherServicesReservation) {
                            body += ((OtherServicesReservation) curRes).getProductId().getProductType()+" x "+((OtherServicesReservation) curRes).getAmount();
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        }
                        i++;
                    }
                    body += newline+newline;
                    body += newline+hotel.getHotelName();
                    body += newline+hotel.getHotelAddress1()+", "+ hotel.getHotelCity();
                    body += newline+hotel.getHotelCountry();
                    body += newline+"Phone : "+hotel.getHotelPhone();
                    body += newline+"Email : "+hotel.getHotelEmail();

                    EmailSender.sendEmail(to, from, cc, subject, body);
                    response.sendRedirect("HapusResv?id="+reservId+"&ref="+ref);
                } else if (act.equals("reminder")) {
                    // email ke customer
                    subject = hotel.getHotelName() + " [Reservation #"+reservId+"] Reservation Payment Reminder";
                    body = "Your reservation #"+reservId+" is in due payment time. "+newline;
                    body += newline+"Reservation Id   : "+reservId;
                    body += newline+"Reservation Time : "+formatter.format(res.getReservationTime());
                    body += newline+"Due Date         : "+formatter.format(res.getReservationPaymentLimit());
                    body += newline+"Expired Date     : "+formatter.format(ctrl.getExpiredDate(reservId));
                    body += newline+"Total Price      : "+currencyFormat.format(res.getTotalPrice())+newline;
                    body += newline+"Reservation Item(s): ";
                    int i=1;
                    Collection<ReservationItem> col = ctrl.getReservationItemById(reservId);
                    Iterator<ReservationItem> it = col.iterator();
                    while (it.hasNext()) {
                        ReservationItem curRes = it.next();
                        body += newline+i+". ";
                        if (curRes instanceof RoomReservation) {
                            body += "Room "+((RoomReservation) curRes).getRoomNo().getRoomNo()+", "+((RoomReservation) curRes).getRoomNo().getProductId().getProductType();
                            body += newline+"# Entry Date : "+formatter.format(((RoomReservation) curRes).getEntryDate());
                            body += newline+"# Exit Date  : "+formatter.format(((RoomReservation) curRes).getExitDate());
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        } else if (curRes instanceof HallReservation) {
                            body += "Package "+((HallReservation) curRes).getProductId().getProductType()+", Hall "+((HallReservation) curRes).getVenueNo().getVenueNo()+", "+((HallReservation) curRes).getVenueNo().getVenueName();
                            body += newline+"# Usage Date : "+formatter.format(((HallReservation) curRes).getUseDate());
                            body += newline+"# Usage Time : "+timeformatter.format(((HallReservation) curRes).getBeginTime())+" - "+timeformatter.format(((HallReservation) curRes).getEndTime());
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        } else if (curRes instanceof OtherServicesReservation) {
                            body += ((OtherServicesReservation) curRes).getProductId().getProductType()+" x "+((OtherServicesReservation) curRes).getAmount();
                            body += newline+"# Price      : "+currencyFormat.format(curRes.getPrice());
                        }
                        i++;
                    }
                    body += newline+newline+"Pay your reservation to our bank account before expired payment time: ";
                    body += newline+"- "+hotel.getBankName1();
                    body += newline+"  A/C Number : "+hotel.getAccountNumber1();
                    body += newline+"  A/C Name   : "+hotel.getAccountName1();
                    if (hotel.getAccountName2() != null) {
                        body += newline+"- "+hotel.getBankName2();
                        body += newline+"  A/C Number : "+hotel.getAccountNumber2();
                        body += newline+"  A/C Name   : "+hotel.getAccountName2();
                    }
                    body += newline+newline+"And visit our website to confirm your payment.";
                    body += newline+newline;
                    body += newline+hotel.getHotelName();
                    body += newline+hotel.getHotelAddress1()+", "+ hotel.getHotelCity();
                    body += newline+hotel.getHotelCountry();
                    body += newline+"Phone : "+hotel.getHotelPhone();
                    body += newline+"Email : "+hotel.getHotelEmail();
                    EmailSender.sendEmail(to, from, cc, subject, body);
                    response.sendRedirect("reservation_manage_notification.jsp?status=send_email_success&id="+reservId);
                }
            } else {
                response.sendRedirect("reservation_manage_notification.jsp?status=send_email_failed&id="+reservId);
            }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendEmail at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("reservation_manage_notification.jsp?status=send_email_failed");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
