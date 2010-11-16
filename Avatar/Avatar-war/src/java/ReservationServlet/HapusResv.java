/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ReservationServlet;

import KelolaReservasi.MengelolaReservasiController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TOSHIBA
 */
@WebServlet(name="HapusResv", urlPatterns={"/backend/HapusResv"})
public class HapusResv extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Integer reservId;
        String ref;
        
        reservId =  Integer.parseInt(request.getParameter("id"));
        ref = request.getParameter("ref");

        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
            MengelolaReservasiController ctrl = new MengelolaReservasiController();
            ctrl.deleteReservation(reservId);
            if (ref != null) {
                if (ref.equals("notif")) {
                    //response.sendRedirect("SendEmail?ref=delete&id="+reservId+"&type=expired");
                    response.sendRedirect("reservation_manage_notification.jsp?status=delete_success&id="+reservId);
                } else {
                    response.sendRedirect("reservation_manage.jsp?status=delete_success&id="+reservId);
                }
            } else {
                response.sendRedirect("reservation_manage.jsp?status=delete_success&id="+reservId);
            }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HapusResv</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HapusResv at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("reservation_manage_notification.jsp?status=delete_failed&id="+reservId);
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
