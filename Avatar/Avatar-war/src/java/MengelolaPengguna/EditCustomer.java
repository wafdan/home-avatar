/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPengguna;

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zulfikar
 */
public class EditCustomer extends HttpServlet {
   
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
            //?username=+Restya19+&nama=+Restya+Winda+A.+&emID=+198806032010092001+&inumber=1
            String username=request.getParameter("username");
            String name=request.getParameter("name");
            String itype=request.getParameter("itype");
            String inumber=request.getParameter("inumber");

            Customer s=new Customer();
            CustomerJpaController sj=new CustomerJpaController();
            s=sj.findCustomer(username);
            out.write("Username="+username);
            s.setName(name);
            s.setIdentityNumber(inumber);
            s.setIdentityType(itype);
            //sj.getEntityManager().getTransaction().commit();
            sj.edit(s);
            response.sendRedirect("ManageCustomer.jsp");
        } catch(NonexistentEntityException ex){
            out.write(ex.getMessage());
        }
        catch (Exception ex) {
            out.write(ex.getMessage());
        }
        finally {
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
