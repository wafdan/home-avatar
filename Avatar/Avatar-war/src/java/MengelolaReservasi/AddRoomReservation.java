/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaReservasi;

import AvatarEntity.Customer;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import KelolaReservasi.MengelolaReservasiController;
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

/**
 *
 * @author Christian
 */
public class AddRoomReservation extends HttpServlet {
   
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
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter out = response.getWriter();
        RoomReservationJpaController rrjpa = new RoomReservationJpaController();
        RoomJpaController rmjpa = new RoomJpaController();
        ReservationJpaController resjpa = new ReservationJpaController();
        Reservation res = null;
        try {
            Room room = rmjpa.findRoom(request.getParameter("roomNo"));
            Date entry = dateOnly.parse(request.getParameter("entryDate"));
            Date exit = dateOnly.parse(request.getParameter("exitDate"));
            double price = MengelolaReservasiController.getAccomodationPrice(room.getProductId(), entry, exit);
            RoomReservation rr = new RoomReservation(new Date(), price, entry, exit);
            if (request.getParameter("reservationId") != null) {
                rr.setReservationId(new Reservation(Integer.parseInt(request.getParameter("reservationId"))));
            } else if (request.getParameter("parent") != null) {
                res = new Reservation(true, "");
                Reservation parent = resjpa.findReservation(Integer.parseInt(request.getParameter("parent")));
                res.setParent(parent);
                res.setUsername(parent.getUsername());
                resjpa.create(res);
                rr.setReservationId(res);
            } else {
                res = new Reservation(true, "");
                res.setUsername(new Customer(request.getParameter("username")));
                resjpa.create(res);
                rr.setReservationId(res);
            }
            rr.setRoomNo(room);
            rrjpa.create(rr);
            //Tampilkan ke JSP
            response.sendRedirect("reservation_manage.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(AddRoomReservation.class.getName()).log(Level.SEVERE, null, ex);
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
