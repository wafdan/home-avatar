/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPengguna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AvatarEntity.Staff;
import AvatarEntity.StaffJpaController;

/**
 *
 * @author zulfikar
 */
@WebServlet(name="EditStaff", urlPatterns={"/MengelolaPengguna/EditStaff"})
public class EditStaff extends HttpServlet {
   
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
            String username=request.getParameter("username");
            String name=request.getParameter("nama");
            String ID=request.getParameter("emID");
            short position=Short.parseShort(request.getParameter("position"));

            Staff s=new Staff();
            StaffJpaController sj=new StaffJpaController();
            s=sj.findStaff(username);
            out.write("Username="+username);
            s.setName(name);
            s.setEmploymentId(ID);
            s.setPosition(position);
            //sj.getEntityManager().getTransaction().commit();
            sj.edit(s);

            response.sendRedirect("../backend/staff_manage.jsp");
            
        } /*catch (NonexistentEntityException ex) {
            Logger.getLogger(EditStaff.class.getName()).log(Level.SEVERE, null, ex);
        } */catch (Exception ex) {
            Logger.getLogger(EditStaff.class.getName()).log(Level.SEVERE, null, ex);
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
