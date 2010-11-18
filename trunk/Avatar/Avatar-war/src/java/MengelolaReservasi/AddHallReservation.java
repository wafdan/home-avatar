/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaReservasi;

import AvatarEntity.Customer;
import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.Venue;
import AvatarEntity.VenueJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

/**
 *
 * @author Christian
 */
public class AddHallReservation extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        HallReservationJpaController hrjpa = new HallReservationJpaController();
        HallJpaController hjpa = new HallJpaController();
        ReservationJpaController resjpa = new ReservationJpaController();
        Reservation res = null;
        int att = Integer.parseInt(request.getParameter("attendees"));
        try {
            Hall hall = hjpa.findHall(request.getParameter("productId"));
            HallReservation hallres = new HallReservation(new Date(), att * hall.getNormalRate(), time.parse(request.getParameter("beginTimeHour") + ":" + request.getParameter("beginTimeMin")), time.parse(request.getParameter("endTimeHour") + ":" + request.getParameter("endTimeMin")), date.parse(request.getParameter("useDate")), att);
            hallres.setVenueNo(new Venue(request.getParameter("venue")));
            if (request.getParameter("reservationId") != null) {
                hallres.setReservationId(new Reservation(Integer.parseInt(request.getParameter("reservationId"))));
            } else if (request.getParameter("parent") != null) {
                res = new Reservation(true, "layout: " + request.getParameter("layout"));
                Reservation parent = resjpa.findReservation(Integer.parseInt(request.getParameter("parent")));
                res.setParent(parent);
                res.setUsername(parent.getUsername());
                resjpa.create(res);
                hallres.setReservationId(res);
            } else {
                res = new Reservation(true, "layout: " + request.getParameter("layout"));
                res.setUsername(new Customer(request.getParameter("username")));
                resjpa.create(res);
                hallres.setReservationId(res);
            }
            hallres.setProductId(hall);
            hrjpa.create(hallres);
            // Tampilkan ke JSP
            response.sendRedirect("reservation_manage.jsp");
        } catch (ParseException ex) {
                Logger.getLogger(AddHallReservation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
