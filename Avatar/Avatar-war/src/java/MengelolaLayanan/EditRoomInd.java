/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaLayanan;

import AvatarEntity.Accomodation;
import AvatarEntity.AccomodationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian
 */
public class EditRoomInd extends HttpServlet {
   
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/room_add");
        try {
            // Inisialisasi JPA Controller dan List of Entity
            RoomJpaController rmjpa = new RoomJpaController();
            AccomodationJpaController acjpa = new AccomodationJpaController();
            if (request.getParameter("roomNo") != null) {
                Room room = rmjpa.findRoom(request.getParameter("roomNo"));
                if (request.getParameter("update") != null) {
                    // Jika ada aksi pengubahan
                    room.setFloor(Integer.parseInt(request.getParameter("floor")));
                    if (!request.getParameter("roomName").equals(""))
                        room.setRoomName(request.getParameter("roomName"));
                    Accomodation accom = acjpa.findAccomodation(request.getParameter("roomType"));
                    room.setProductId(accom);
                    rmjpa.edit(room);
                } else  {
                    // Kirim ke JSP halaman edit
                    request.setAttribute("toEdit", room);
                    dispatcher = request.getRequestDispatcher("/backend/room_edit.jsp");
                }
            }
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(EditRoomInd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditRoomInd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditRoomInd.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            out.close();
        }
        dispatcher.forward(request, response);
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
