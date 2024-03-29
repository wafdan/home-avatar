/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaReservasi;

import AvatarEntity.Room;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import AvatarEntity.exceptions.NonexistentEntityException;
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
public class EditRoomReservation extends HttpServlet {
   
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
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RoomReservationJpaController rrjpa = new RoomReservationJpaController();
        RoomReservation rr = rrjpa.findRoomReservation(Integer.parseInt(request.getParameter("reservationItemId")));
        try {
            Date entry = dateOnly.parse(request.getParameter("entryDate"));
            Date exit = dateOnly.parse(request.getParameter("exitDate"));
            rr.setEntryDate(entry);
            rr.setExitDate(exit);
            if (request.getParameter("actualEntry").equals("")) {
                rr.setActualEntry(null);
            } else {
                rr.setActualEntry(datetime.parse(request.getParameter("actualEntry")));
            }
            if (request.getParameter("actualExit").equals("")) {
                rr.setActualExit(null);
            } else {
                rr.setActualExit(datetime.parse(request.getParameter("actualExit")));
            }
            double price = MengelolaReservasiController.getAccomodationPrice(rr.getRoomNo().getProductId(), entry, exit);
            rr.setPrice(price);
            rr.setRoomNo(new Room(request.getParameter("roomNo")));
            rrjpa.edit(rr);
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
