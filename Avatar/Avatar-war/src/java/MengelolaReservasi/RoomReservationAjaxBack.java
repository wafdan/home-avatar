/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaReservasi;

import AvatarEntity.Room;
import AvatarEntity.RoomJpaController;
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
public class RoomReservationAjaxBack extends HttpServlet {
   
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
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        RoomJpaController rmjpa = new RoomJpaController();
        PrintWriter out = response.getWriter();
        List<Room> lr = null;
        try {
            out.write("<?xml version=\"1.0\"?><getroom>");
            if (request.getParameter("item") == null) {
                lr = rmjpa.findUnused(request.getParameter("room"), dateOnly.parse(request.getParameter("entryDate")), dateOnly.parse(request.getParameter("exitDate")));
            } else {
                lr = rmjpa.findUnusedEx(request.getParameter("room"), dateOnly.parse(request.getParameter("entryDate")), dateOnly.parse(request.getParameter("exitDate")), Integer.parseInt(request.getParameter("item")));
            }
            for (Room room : lr) {
                out.write("<room>");
                out.write("<no>" + room.getRoomNo() + "</no>");
                out.write("<name>");
                out.write(room.getRoomNo() + (room.getRoomName() != null ? " - " + room.getRoomName() : ""));
                out.write("</name>");
                out.write("</room>");
            }
            out.write("</getroom>");
        } catch (ParseException ex) {
            Logger.getLogger(RoomReservationAjaxBack.class.getName()).log(Level.SEVERE, null, ex);
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
