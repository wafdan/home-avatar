/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.Staff;
import AvatarEntity.StaffJpaController;
import Support.EncMd5;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kamoe
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        StaffJpaController sjpa = new StaffJpaController();
        CustomerJpaController cjpa = new CustomerJpaController();

        try {
            String Username = request.getParameter("username");
            String Password = EncMd5.MD5(request.getParameter("password"));
            HttpSession session = request.getSession(true);
            Staff u = sjpa.findStaff(Username.toString());

            Customer c = cjpa.findCustomer(Username.toString());
            if (u != null) {//if yg login adalah staff
                if (Password.equals(u.getPassword())) { // if password staff benar
                    if (u.getPosition() == 0) { //if admin
                        out.println("ADMIN");
                    } else if (u.getPosition() == 1) { //if receptionist
                        out.println("RESEPSIONIS");
                    } else if (u.getPosition() == 2) { //if manager
                        out.println("MANAGER!");
                    }
                    session.setAttribute("username", u.getUsername());
                    session.setAttribute("name", u.getName());
                    session.setAttribute("position", u.getPosition());
                    out.println("Selamat Datang " + u.getName() + "");
                } else {
                    out.println("STAFF GAGAL!");
                }
            }

            if (c != null) { // if yang login adalah customer //if ditemukan customer yang login tersebut
                if (Password.equals(c.getPassword())) { // if password customer benar

                    out.println("CUSTOMER!");
                    session.setAttribute("username", c.getUsername());
                    session.setAttribute("name", c.getName());
                    session.setAttribute("position", 3); // "3" identifier dummy untuk customer
                    response.sendRedirect(request.getContextPath() +"/index.jsp");
                } else {
                    session.setAttribute("position", 30); // "30" identifier dummy untuk customer gagal login
                    response.sendRedirect(request.getContextPath() +"/index.jsp");
                    out.println("CUSTOMER GAGAL!");
                }
            }

            //else { //if gagal login
            //  response.sendRedirect(request.getContextPath() + "/index.jsp");
            //}

        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            //PrintWriter out = response.getWriter();
            out.println(e);
            e.printStackTrace();
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
