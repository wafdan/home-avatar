/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ReservationServlet;

import AvatarEntity.RoomJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zulfikar
 */
public class ReservationAjax extends HttpServlet {
   
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
            String checkindate=request.getParameter("checkindate");
            String checkoutdate=request.getParameter("checkoutdate");
            String product_id=request.getParameter("productid");

            SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
            Date checkIn=sdf.parse(checkindate);
            Date checkOut=sdf.parse(checkoutdate);

            Calendar checkInCal=Calendar.getInstance();
            Calendar checkOutCal=Calendar.getInstance();

            checkInCal.setTime(checkIn);
            checkOutCal.setTime(checkOut);

            if(!checkOutCal.after(checkInCal)){
                out.write(String.valueOf(-1));
                return;
            }

            int roomAvailable=(new RoomJpaController()).findUnused(product_id, checkIn, checkOut).size();
            System.out.println("Room Available="+roomAvailable);
            out.write(String.valueOf(roomAvailable));

        } catch (ParseException ex) {
            out.write(String.valueOf(-2));
            Logger.getLogger(ReservationAjax.class.getName()).log(Level.SEVERE, null, ex);
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
