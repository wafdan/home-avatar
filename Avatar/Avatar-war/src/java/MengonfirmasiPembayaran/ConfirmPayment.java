/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengonfirmasiPembayaran;

import KonfirmasiPembayaran.KonfirmasiPembayaranController;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kamoe
 */
@WebServlet(name="ConfirmPayment", urlPatterns={"/ConfirmPayment"})
public class ConfirmPayment extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String reservId;
        String accNo;
        String bank;
        String amount;
        String paymentDate;

        reservId =  request.getParameter("reservationId");
        accNo =  request.getParameter("acc_no");
        bank = request.getParameter("bank");
        amount = request.getParameter("amount");
        paymentDate = request.getParameter("payment_date");

        try {
            if (!reservId.isEmpty() || !accNo.isEmpty() || !bank.isEmpty() || !amount.isEmpty() || !paymentDate.isEmpty())
            {
                Date pd = new Date();
                String[] arrpd = paymentDate.split("/");
                //System.out.println("1."+arrpd[0]+"-2."+arrpd[1]+"-3."+arrpd[2]);
                pd.setDate(Integer.parseInt(arrpd[0]));
                pd.setMonth(Integer.parseInt(arrpd[1]));
                pd.setYear(Integer.parseInt(arrpd[2]));
                KonfirmasiPembayaranController kpc = new KonfirmasiPembayaranController();
                kpc.confirmPayment(reservId, accNo, bank, amount, pd);
            }
            response.sendRedirect("reservation_status.jsp");
        } catch (Exception e) {
            e.printStackTrace();
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
