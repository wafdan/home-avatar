/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaLayanan;

import AvatarEntity.Layout;
import AvatarEntity.LayoutJpaController;
import AvatarEntity.Venue;
import AvatarEntity.VenueJpaController;
import AvatarEntity.VenueLayout;
import AvatarEntity.VenueLayoutJpaController;
import AvatarEntity.exceptions.PreexistingEntityException;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditVenue extends HttpServlet {
   
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
            VenueJpaController vjpa = new VenueJpaController();
            Venue venue = vjpa.findVenue(request.getParameter("venueNo"));
            if (request.getParameter("update") != null) {
                // Masukkan Venue
                if (!request.getParameter("description").equals("")) {
                    venue.setDescription(request.getParameter("description"));
                }
                vjpa.create(venue);
                // Masukkan layout
                LayoutJpaController ljpa = new LayoutJpaController();
                VenueLayoutJpaController vljpa = new VenueLayoutJpaController();
                VenueLayout vl = null;
                for (Layout lay : ljpa.findLayoutEntities()) {
                    vl = new VenueLayout(venue.getVenueNo(), lay.getLayoutNo(),
                            Integer.parseInt(request.getParameter("layout_" + lay.getLayoutNo())));
                    vl.setVenue(venue); vl.setLayout(lay);
                    if (vljpa.findVenueLayout(venue.getVenueNo(), lay.getLayoutNo()) != null) {
                        vljpa.edit(vl);
                    } else {
                        vljpa.create(vl);
                    }
                }
                response.sendRedirect("/backend/venue_add");
            } else {
                // Kirim ke JSP halaman edit
                request.setAttribute("toEdit", venue);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/venue_edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(EditVenue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditVenue.class.getName()).log(Level.SEVERE, null, ex);
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
