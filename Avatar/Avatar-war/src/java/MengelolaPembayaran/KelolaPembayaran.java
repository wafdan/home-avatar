/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPembayaran;

import AvatarEntity.Reservation;
import AvatarEntity.ReservationJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian
 */
public class KelolaPembayaran extends HttpServlet {
   
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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet KelolaPembayaran</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet KelolaPembayaran at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            // Inisialisasi Kontroler JPA dan Kelas Entity
            ReservationJpaController resjc = new ReservationJpaController();
            List<Reservation> lres = resjc.findReservationEntities();
            for (Reservation item : lres) {
                out.println("ID: " + item.getReservationId() + "<br />");
                out.println("User: " + item.getUsername().getName() + "<br />");
                out.println("Amount: " + item.getTotalPrice() + "<br />");
                out.println("Reservation time: " + item.getReservationTime() + "<br />");
                out.println("On spot? " + (item.getIsOnspot() ? "yes" : "no") + "<br />");
                out.println("Confirmed? " + (item.getPayment() != null ? "yes" : "no") + "<br />");
                out.println("Verified? " + (item.getPayment() != null ? (item.getPayment().getUsername() != null ? "yes" : "no") : "no") + "<br />");
                out.println("Note: " + item.getNote() + "<br />");
                out.println("-----<br />");
            }
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
