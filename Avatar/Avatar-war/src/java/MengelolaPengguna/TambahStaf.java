/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MengelolaPengguna;


import KelolaPengguna.MengelolaPenggunaController;
import AvatarEntity.Staff;
import AvatarEntity.StaffJpaController;
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

/**
 *
 * @author zulfikar
 */
public class TambahStaf extends HttpServlet {

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
            String fullname = request.getParameter("name");
            String EmployeeID = request.getParameter("subject");
            String email = request.getParameter("email");
            short position = Short.parseShort(request.getParameter("position"));
            MengelolaPenggunaController k = new MengelolaPenggunaController();
            String username = k.generateStaffUsername(fullname, EmployeeID);
            String password = k.generatePassword();
            String EncryptedPassword = k.getHashedPassword(password);

            
            Staff s = new Staff(username, fullname, EncryptedPassword, email, EmployeeID, position);
            StaffJpaController sjc=new StaffJpaController();
            sjc.create(s);
            //Mengecek user udah ada atau belum
            response.sendRedirect("TambahStaf.jsp");
            

        } catch(PreexistingEntityException ex){
            String fullname = request.getParameter("fullname");
            String EmployeeID = request.getParameter("employmentID");
            String email = request.getParameter("email");
            short position = Short.parseShort(request.getParameter("position"));
            MengelolaPenggunaController k = new MengelolaPenggunaController();
            String username = k.generateStaffUsername(fullname, EmployeeID, 4);
            String password = k.generatePassword();
            String EncryptedPassword=null;
            try {
                EncryptedPassword = k.getHashedPassword(password);
            } catch (NoSuchAlgorithmException ex1) {
                Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (UnsupportedEncodingException ex1) {
                Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex1);
            }


            Staff s = new Staff(username, fullname, EncryptedPassword, email, EmployeeID, position);
            StaffJpaController sjc=new StaffJpaController();
            try {
                sjc.create(s);
                response.sendRedirect("TambahStaf.jsp?status=success");
            } catch (PreexistingEntityException ex1) {
                Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex1);
                response.sendRedirect("TambahStaf.jsp?status=alreadyexist");
            } catch (Exception ex1) {
                Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex1);
                response.sendRedirect("TambahStaf.jsp?status=unexpectedfailure");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex);
            //response.sendRedirect("TambahStaf.jsp?status=unexpectedfailure");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex);
          //  response.sendRedirect("TambahStaf.jsp?status=unexpectedfailure");
        } catch (Exception ex) {
            Logger.getLogger(TambahStaf.class.getName()).log(Level.SEVERE, null, ex);
            //response.sendRedirect("TambahStaf.jsp?status=unexpectedfailure");
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
