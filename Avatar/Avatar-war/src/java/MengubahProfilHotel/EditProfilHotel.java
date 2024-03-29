/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MengubahProfilHotel;

import AvatarEntity.Profile;
import AvatarEntity.ProfileJpaController;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zulfikar
 */
public class EditProfilHotel extends HttpServlet {

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
            String hotelname = request.getParameter("hotelname");
            String hoteldesc = request.getParameter("hoteldesc");
            String city = request.getParameter("city");
            String phonenumber = request.getParameter("hotel_phone");
            String faxnumber=request.getParameter("hotel_fax");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String address2 = request.getParameter("address2");
            String country = request.getParameter("country");

            String accno1=request.getParameter("account_no_1");
            String accno2=request.getParameter("account_no_2");
            String bankname1=request.getParameter("bank_name_1");
            String bankname2=request.getParameter("bank_name_2");
            String accname1=request.getParameter("account_name_1");
            String accname2=request.getParameter("account_name_2");

            ProfileJpaController pjc = new ProfileJpaController();
            Profile p = new Profile(Boolean.TRUE, hotelname, address, city, country, phonenumber);
            p.setHotelDescription(hoteldesc);
            p.setHotelAddress2(address2);
            p.setHotelEmail(email);
            p.setHotelDescription(hoteldesc);
            p.setHotelFax(faxnumber);
            p.setAccountName1(accname1);
            p.setAccountName2(accname2);
            p.setAccountNumber1(accno1);
            p.setAccountNumber2(accno2);
            p.setBankName1(bankname1);
            p.setBankName2(bankname2);
            pjc.edit(p);


        } catch (NonexistentEntityException ex) {
            out.write(ex.getMessage());
        } catch (Exception ex) {
            out.write(ex.getMessage());
        } finally {
            response.sendRedirect("profile_manage.jsp");
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
