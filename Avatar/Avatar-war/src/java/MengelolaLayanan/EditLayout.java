/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaLayanan;

import AvatarEntity.Layout;
import AvatarEntity.LayoutJpaController;
import AvatarEntity.exceptions.NonexistentEntityException;
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
public class EditLayout extends HttpServlet {
   
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
            // Inisialisasi JPA Controller dan List of Entity
            LayoutJpaController ljpa = new LayoutJpaController();
            if (request.getParameter("layoutNo") != null) {
                Layout layout = ljpa.findLayout(Integer.parseInt(request.getParameter("layoutNo")));
                if (request.getParameter("update") != null) { // Jika ada aksi pengubahan
                    layout.setLayoutName(request.getParameter("layoutName"));
                    ljpa.edit(layout);
                    response.sendRedirect("layout_add");
                } else  {
                    // Kirim ke JSP halaman edit
                    request.setAttribute("toEdit", layout);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/layout_edit.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditLayout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditLayout.class.getName()).log(Level.SEVERE, null, ex);
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
