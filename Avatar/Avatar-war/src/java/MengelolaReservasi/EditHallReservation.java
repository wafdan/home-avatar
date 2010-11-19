/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaReservasi;

import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.exceptions.NonexistentEntityException;
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
public class EditHallReservation extends HttpServlet {
   
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
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        HallReservationJpaController hrjpa = new HallReservationJpaController();
        HallReservation hr = hrjpa.findHallReservation(Integer.parseInt(request.getParameter("reservationItemId")));
        try {
            Date beginTime = time.parse(request.getParameter("beginTimeHour") + ":" + request.getParameter("beginTimeMin"));
            Date endTime = time.parse(request.getParameter("endTimeHour") + ":" + request.getParameter("endTimeMin"));
            int att = Integer.parseInt(request.getParameter("attendees"));
            hr.setBeginTime(beginTime);
            hr.setEndTime(endTime);
            double price = att * hr.getProductId().getNormalRate();
            hr.setPrice(price);
            hrjpa.edit(hr);
            response.sendRedirect("reservation_manage.jsp");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditRoomReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EditRoomReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditRoomReservation.class.getName()).log(Level.SEVERE, null, ex);
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
