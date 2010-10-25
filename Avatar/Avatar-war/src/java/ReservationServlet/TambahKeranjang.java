/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationServlet;

import AvatarEntity.Room;
import Pemesanan.CartLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import Pemesanan.CartSessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zulfikar
 */
public class TambahKeranjang extends HttpServlet {

    CartLocal cartSessionBean1 = lookupCartSessionBeanLocal();

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
        HttpSession session=request.getSession();

        try {
            String action = request.getParameter("action");
            if (action.equals("add")) {
                
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                String roomType = (String) request.getParameter("roomtype");
                Date checkInDate = df.parse((String) request.getParameter("roomcheckindate"));
                

                Date checkOutDate = df.parse((String) request.getParameter("roomcheckoutdate"));
                short totalRoom = Short.parseShort((String) request.getParameter("totalroom"));

                String packageType = (String) request.getParameter("packagetype");
                Date hallDate = df.parse((String) request.getParameter("halldate"));
                short totalHall = Short.parseShort((String) request.getParameter("totalhall"));

                cartSessionBean1.addHallCartElement(packageType, hallDate, totalHall);
                cartSessionBean1.addRoomCartElement(roomType, checkInDate, checkOutDate, totalRoom);

                //out.write("Jumlah hall yang dipesan=" + cartSessionBean1.getHallCart().size());

                // request.setAttribute("listhall", cartSessionBean1.getListHall());
                session.setAttribute("roomcart", cartSessionBean1.getRoomCart());
                session.setAttribute("hallcart", cartSessionBean1.getHallCart());
                out.write("Jumlah cart untuk room : "+cartSessionBean1.getRoomCart().size());

                //response.sendRedirect("reservation.jsp?step=2");
                out.write("<a href='reservation.jsp?step=2'>Lanjut </a>");
            }
            else if (action.equals("delete")){
                
            }
        } catch (ParseException ex) {
            Logger.getLogger(TambahKeranjang.class.getName()).log(Level.SEVERE, null, ex);
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

    private CartLocal lookupCartSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartLocal) c.lookup("java:global/Avatar/Avatar-ejb/CartSessionBean!Pemesanan.CartLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

/**
 * @author Deepak Kumar
 * @Web http://www.roseindia.net
 * @Email deepak@roseindia.net
 */
