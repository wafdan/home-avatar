/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaLayanan;

import AvatarEntity.Accomodation;
import AvatarEntity.AccomodationJpaController;
import AvatarEntity.exceptions.PreexistingEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TOSHIBA
 */
@WebServlet(name="TambahAcco", urlPatterns={"/backend/TambahAcco"})
public class TambahAcco extends HttpServlet {
   
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
            String pid = request.getParameter("pid");
            String type = request.getParameter("type");
            String desc = request.getParameter("desc");
            String img = request.getParameter("img");
            String max = request.getParameter("max");
            String nent = request.getParameter("nent");
            String noxt = request.getParameter("noxt");
            String wday = request.getParameter("wday");
            String wend = request.getParameter("wend");
            String terl = request.getParameter("terl");
            String tlat = request.getParameter("tlat");

            Accomodation hoo = new Accomodation();
            AccomodationJpaController ajc = new AccomodationJpaController();

            hoo.setProductId(pid);
            hoo.setProductType(type);
            hoo.setDescription(desc);
            hoo.setImage(img);
            hoo.setMaxPax(Integer.parseInt(max));
            hoo.setNormalEntry(Time.valueOf(nent));
            hoo.setNormalExit(Time.valueOf(noxt));
            hoo.setWeekdayRate(Double.parseDouble(wday));
            hoo.setWeekendRate(Double.parseDouble(wend));
            hoo.setToleranceEarly(Time.valueOf(terl));
            hoo.setToleranceLate(Time.valueOf(tlat));

            ajc.create(hoo);

            out.println("hoooo");
            response.sendRedirect("fac_room_manage.jsp");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(TambahAcco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TambahAcco.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            //response.sendRedirect("fac_room_add.jsp");
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
