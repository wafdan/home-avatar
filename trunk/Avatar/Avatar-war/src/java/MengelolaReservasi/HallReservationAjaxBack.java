/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaReservasi;

import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.Venue;
import AvatarEntity.VenueJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class HallReservationAjaxBack extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        SimpleDateFormat min = new SimpleDateFormat("mm");
        HallJpaController hjpa = new HallJpaController();
        Hall hall = hjpa.findHall(request.getParameter("hall"));
        VenueJpaController vjpa = new VenueJpaController();
        try {
            out.print("<?xml version=\"1.0\"?><gethall>");
            for (Venue ven : vjpa.findUnused(date.parse(request.getParameter("date")),
                    Integer.parseInt(request.getParameter("layout")),
                    Integer.parseInt(request.getParameter("att")))) {
                out.write("<venue>");
                out.write("<no>");
                out.write(ven.getVenueNo());
                out.write("</no>");
                out.write("<name>");
                out.write(ven.getVenueName());
                out.write("</name>");
                out.write("</venue>");
            }
            out.write("<hall>");
            out.write(hour.format(hall.getStartTime()));
            out.write("</hall>");
            out.write("<hall>");
            out.write(min.format(hall.getStartTime()));
            out.write("</hall>");
            out.write("<hall>");
            out.write(hour.format(hall.getEndTime()));
            out.write("</hall>");
            out.write("<hall>");
            out.write(min.format(hall.getEndTime()));
            out.write("</hall>");
            out.write("</gethall>");
        } catch (ParseException ex) {
            Logger.getLogger(HallReservationAjaxBack.class.getName()).log(Level.SEVERE, null, ex);
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
