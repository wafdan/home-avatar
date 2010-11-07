/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MengelolaLayanan;

import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
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
@WebServlet(name = "TambahHall", urlPatterns = {"/backend/TambahHall"})
public class TambahHall extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PreexistingEntityException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String pid = request.getParameter("pid");
            String type = request.getParameter("type");
            String desc = request.getParameter("desc");
            String nrat = request.getParameter("nrat");
            String nrtu = request.getParameter("nrtu");
            String ovrt = request.getParameter("ovrt");
            String ovu = request.getParameter("ovu");
            String stim = request.getParameter("stim");
            String stim2 = request.getParameter("stim2");
            String etim = request.getParameter("etim");
            String etim2 = request.getParameter("etim2");

            if (nrat == null ? "" == null : nrat.equals("")) {
                nrat = "0";
            }
            if (ovrt == null ? "" == null : ovrt.equals("")) {
                ovrt = "0";
            }
            if ((stim == null ? "" == null : stim.equals(""))
                    || (stim2 == null ? "" == null : stim2.equals(""))) {
                stim = "00";
                stim2 = "00";
            }
            if ((etim == null ? "" == null : etim.equals(""))
                    || (etim2 == null ? "" == null : etim2.equals(""))) {
                etim = "00";
                etim2 = "00";
            }

            stim = stim + ":" + stim2 + ":00";
            etim = etim + ":" + etim2 + ":00";

            Hall hoo = new Hall();
            HallJpaController ajc = new HallJpaController();

            hoo.setProductId(pid);
            hoo.setProductType(type);
            hoo.setDescription(desc);
            hoo.setNormalRate(Double.parseDouble(nrat));
            hoo.setNormalRateUnit(nrtu);
            hoo.setOverchargeRate(Double.parseDouble(ovrt));
            hoo.setOverchargeUnit(ovu);
            hoo.setStartTime(Time.valueOf(stim));
            hoo.setEndTime(Time.valueOf(etim));

            ajc.create(hoo);
            out.println("hoooo");
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(TambahAcco.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("fac_hall_add.jsp");
        } catch (Exception ex) {
            Logger.getLogger(TambahAcco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NoClassDefFoundError ex){
            response.sendRedirect("fac_hall_add.jsp");
        }finally {
            response.sendRedirect("fac_hall_manage.jsp");
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
        try {
            processRequest(request, response);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(TambahHall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TambahHall.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(TambahHall.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TambahHall.class.getName()).log(Level.SEVERE, null, ex);
        }
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
