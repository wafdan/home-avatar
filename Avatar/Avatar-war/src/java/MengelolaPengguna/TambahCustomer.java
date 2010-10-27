/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MengelolaPengguna;

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.exceptions.PreexistingEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import KelolaPengguna.MengelolaPenggunaController;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author zulfikar
 */
@WebServlet(name = "TambahCustomer", urlPatterns = {"/backend/TambahCustomer"})
public class TambahCustomer extends HttpServlet {

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
            /* TODO output your page here */
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String idtype = request.getParameter("idtype");
            String idnumber = request.getParameter("idnumber");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String telephone = request.getParameter("telephone");

            MengelolaPenggunaController m = new MengelolaPenggunaController();
            String username = m.generateCustomerUsername(name);
            String password = m.generatePassword();
            Customer c = new Customer();
            c.setUsername(username);
            c.setPassword(password);
            c.setName(name);
            c.setEmail(email);
            c.setIdentityNumber(idnumber);
            c.setIdentityType(idtype);
            c.setAddress1(address1);
            c.setAddress2(address2);
            c.setCity(city);
            c.setCountry(country);
            c.setTelephone(telephone);

            CustomerJpaController cjc = new CustomerJpaController();
            cjc.create(c);
            response.sendRedirect("../backend/customer_add.jsp?status=success");
            //success
            //unexpectedfailure
            //alreadyexist

        } catch (PreexistingEntityException ex) {
            response.sendRedirect("../backend/customer_add.jsp?status=alreadyexist");
        } catch (Exception ex) {
            response.sendRedirect("../backend/customer_add.jsp?status=unexpectedfailure");
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
