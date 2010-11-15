/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengubahInformasiPengguna;

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.exceptions.PreexistingEntityException;
import Support.FormValidator;
import Support.EncMd5;
import java.util.logging.Level;
/**
 *
 * @author Christian
 */
public class UpdateAkun extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String redirparam = "";
        String name = request.getParameter("fullname");
        String identityType = request.getParameter("identity_type");
        String identityNumber = request.getParameter("identity_number");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String uname = String.valueOf(session.getAttribute("username"));
        String pass0 = request.getParameter("opass");
        String pass1 = request.getParameter("npass1");
        String pass2 = request.getParameter("npass2");
        CustomerJpaController custJC = new CustomerJpaController();
        Customer cust = custJC.findCustomer(uname);
        if (!(pass0.equals("") && pass1.equals("") && pass2.equals(""))) {
            try {
                if (!EncMd5.MD5(pass0).equals(cust.getPassword())) {
                    redirparam = redirparam + "pass=2";
                } else if (!pass1.equals(pass2)) {
                    redirparam = redirparam + "pass=1"; // password doesn't match
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UpdateAkun.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UpdateAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!FormValidator.validateName(name)) {
            if (!redirparam.equals("")) redirparam = redirparam + "&";
            redirparam = redirparam + "name=1";
        }
        if (!FormValidator.validateEmail(email)) {
            if (!redirparam.equals("")) redirparam = redirparam + "&";
            redirparam = redirparam + "email=1";
        }
        if (!telephone.equals("") && !FormValidator.validatePhone(telephone)) {
            if (!redirparam.equals("")) redirparam = redirparam + "&";
            redirparam = redirparam + "phone=1";
        }
        if (!redirparam.equals("")) {
            response.sendRedirect("../editprofile.jsp?"+redirparam);
        } else {
            try {
                if (!pass1.equals("")) {
                    cust.setPassword(EncMd5.MD5(pass1));
                }
                cust.setCity(city);
                cust.setIdentityType(identityType);
                cust.setIdentityNumber(identityNumber);
                cust.setCountry(country);
                cust.setAddress1(address1);
                if (!address2.equals("")) {
                    cust.setAddress2(address2);
                } else {
                    cust.setAddress2(null);
                }
                if (!telephone.equals("")) {
                    cust.setTelephone(telephone);
                } else {
                    cust.setTelephone(null);
                }
                custJC.edit(cust);
                response.sendRedirect("../myprofile.jsp");
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UpdateAkun.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UpdateAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
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
