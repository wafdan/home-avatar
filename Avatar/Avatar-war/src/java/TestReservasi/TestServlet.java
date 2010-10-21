/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestReservasi;

import AvatarEntity.CustomerJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationItemJpaController;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import AvatarEntity.exceptions.PreexistingEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Christian
 */
public class TestServlet extends HttpServlet {
   
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
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath () + "</h1>");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            CustomerJpaController custjpa = new CustomerJpaController();
            ReservationJpaController resjpa = new ReservationJpaController();
            ReservationItemJpaController rijpa = new ReservationItemJpaController();
            RoomJpaController rmjpa = new RoomJpaController();
            Room rm = rmjpa.findRoom("105");
            RoomReservation rr = new RoomReservation();
            Reservation res = new Reservation();
            rr.setReservationId(res);
            rr.setReservationTime(new Date());
            rr.setRoomNo(rm);
            rr.setPrice(rm.getProductId().getWeekdayRate());
            rr.setEntryDate(df.parse("2010-12-25"));
            rr.setExitDate(df.parse("2010-12-26"));
            res.setNote("tes");
            res.setUsername(custjpa.findCustomer("christian.h6191"));
            resjpa.create(res);
            Collection<ReservationItem> coll = new HashSet<ReservationItem>();
            coll.add(rr);
            for (ReservationItem item : coll) {
                item.setReservationId(res);
                rijpa.create(item);
            }
            out.println("</body>");
            out.println("</html>");
        /*} catch (PreexistingEntityException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);*/
        } catch (Exception ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
