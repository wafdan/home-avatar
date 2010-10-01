/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MelakukanRegistrasi;

import AvatarEntity.exceptions.PreexistingEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import MelakukanRegistrasi.MelakukanRegistrasiController;
import Support.EncMd5;

/**
 *
 * @author Christian
 */
public class TambahAkun extends HttpServlet {
   
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
            MelakukanRegistrasiController control = new MelakukanRegistrasiController();
            String name = request.getParameter("fullname");
            String identityType = request.getParameter("identity_type");
            String identityNumber = request.getParameter("identity_number");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String uname;
            String pass = control.generatePassword();
            CustomerJpaController custJC = new CustomerJpaController();
            Customer cust = null;
            do {
                uname = control.generateUsername(name, email);
                try {
                    cust = custJC.findCustomer(uname);
                } finally {
                    cust = null;
                }
            } while (cust != null);
            cust = new Customer(uname, name, EncMd5.MD5(pass), email, identityType, identityNumber, address1, city, country);
            if (!address2.equals("")) cust.setTelephone(address2);
            if (!telephone.equals("")) cust.setTelephone(telephone);
            custJC.create(cust);
            out.println("<html>");
            out.println("<head>");
            out.println("\t<title>Servlet TambahAkun</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("\t<h1>Pendaftaran Akun Avatar</h1>");
            out.println("\t<p>Username: "+uname+"</p>");
            out.println("\t<p>Password: "+pass+"</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (PreexistingEntityException ex) {
            out.println("Internal Error: Data already existed");
            Logger.getLogger(TambahAkun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            out.println("Internal Error: Unknown");
            Logger.getLogger(TambahAkun.class.getName()).log(Level.SEVERE, null, ex);
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
