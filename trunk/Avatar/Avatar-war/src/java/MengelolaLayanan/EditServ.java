/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaLayanan;

import AvatarEntity.OtherServices;
import AvatarEntity.OtherServicesJpaController;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name="EditServ", urlPatterns={"/backend/EditServ"})
public class EditServ extends HttpServlet {
   
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
            String id = request.getParameter("pid");
            String type = request.getParameter("type");
            String desc = request.getParameter("desc");
            String img = request.getParameter("img");
            String prcu = request.getParameter("prcu");
            String uprc = request.getParameter("uprc");
            boolean pub = request.getParameter("pub").equals("true") ? true : false;

            out.println(id + "///" + type + "///" + desc + "///" + img + "///"+ pub);

            OtherServicesJpaController sj = new OtherServicesJpaController();
            OtherServices s = new OtherServices();
            s = sj.findOtherServices(id);
            //out.write("productId="+id);
            s.setProductType(type);
            s.setDescription(desc);
            s.setImage(img);
            s.setPricingUnit(prcu);
            s.setUnitPrice(Double.parseDouble(uprc));
            s.setPublished(pub);
            //sj.getEntityManager().getTransaction().commit();
            sj.edit(s);


        } catch (Exception ex) {
            Logger.getLogger(EditAcco.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect("../backend/fac_serv_manage.jsp");
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
