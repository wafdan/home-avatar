/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationServlet;

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.Hall;
import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import Pemesanan.CartController;
import Pemesanan.CartSessionBeanLocal;
import Pemesanan.HallSessionInfo;
import Pemesanan.RoomSessionInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
public class TambahKeranjangBackup extends HttpServlet {

    CartSessionBeanLocal cartSessionBean1 = lookupCartSessionBeanLocal();

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
        HttpSession session = request.getSession();

        try {
            String action = request.getParameter("action");
            if (action.equals("add")) {

                //Mendapatkan informasi buat roomnya
                String roomCheckBox = request.getParameter("roomcheckbox");
                String hallCheckBox = request.getParameter("hallcheckbox");

                String roomType = (String) request.getParameter("roomtype");
                String preCheckInDate = request.getParameter("roomcheckindate");
                String preCheckOutDate = request.getParameter("roomcheckoutdate");
                String preTotalRoom = request.getParameter("totalroom");

                String packageType = (String) request.getParameter("packagetype");
                String preHallDate = request.getParameter("halldate");
                String preTotalHall = request.getParameter("totalhall");

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date checkInDate;
                Date checkOutDate;
                short totalRoom;

                if (roomCheckBox != null) {
                    checkInDate = df.parse(preCheckInDate);
                    checkOutDate = df.parse(preCheckOutDate);

                    Calendar calOut=(Calendar.getInstance());
                    calOut.setTime(checkOutDate);

                    Calendar calIn=(Calendar.getInstance());
                    calIn.setTime(checkInDate);

                    if(!calOut.after(calIn)){
                        response.sendRedirect("reservation.jsp?step=1&error=1");
                        return;
                    }

                    totalRoom = Short.parseShort(preTotalRoom);
                    cartSessionBean1.addRoomCartElement(roomType, checkInDate, checkOutDate, totalRoom);
                    session.setAttribute("roomcart", cartSessionBean1.getRoomCart());
                    out.write("Jumlah cart untuk room : " + cartSessionBean1.getRoomCart().size());

                }

                //Mendapatkan informasi buat hall nya

                Date hallDate;
                short totalHall;

                if (hallCheckBox != null) {
                    hallDate = df.parse(preHallDate);
                    totalHall = Short.parseShort(preTotalHall);
                    //cartSessionBean1.addHallCartElement(packageType, hallDate, totalHall);
                    session.setAttribute("hallcart", cartSessionBean1.getHallCart());
                    out.write("Jumlah cart untuk hall : " + cartSessionBean1.getHallCart().size());
                }

                //out.write("<a href='reservation.jsp?step=2'>Lanjut </a>");
                response.sendRedirect("reservation.jsp?step=2");
            } else if (action.equals("delete")) {
            } else if (action.equals("proceed")) {
                ReservationJpaController resjpa = new ReservationJpaController();
                Reservation res = new Reservation();
                res.setIsOnspot(false);
                /*Ini harus diubah*/
                String usernameCustomer = (String) session.getAttribute("username");
                Customer cust = (new CustomerJpaController()).findCustomer(usernameCustomer);
                res.setUsername(cust);
                res.setNote("");
                resjpa.create(res);
                ArrayList<HallSessionInfo> listHallCart = cartSessionBean1.getHallCart();
                ArrayList<RoomSessionInfo> listRoomCart = cartSessionBean1.getRoomCart();
                Iterator<HallSessionInfo> iHallCart = listHallCart.iterator();
                Iterator<RoomSessionInfo> iRoomCart = listRoomCart.iterator();

                while (iHallCart.hasNext()) {
                    HallSessionInfo temp = iHallCart.next();
                    HallReservation hallReservation = new HallReservation();
                    hallReservation.setProductId(new Hall(temp.product_id));
                    hallReservation.setUseDate(temp.use_date);
                    hallReservation.setReservationTime(new Date());
                    hallReservation.setReservationId(res);
                    (new HallReservationJpaController()).create(hallReservation);
                }

                CartController cartController = new CartController();

                while (iRoomCart.hasNext()) {
                    RoomSessionInfo temp = iRoomCart.next();
                    RoomReservation roomReservation = new RoomReservation();
                    roomReservation.setEntryDate(temp.entry_date);
                    roomReservation.setExitDate(temp.exit_date);
                    roomReservation.setReservationTime(new Date());
                    roomReservation.setRoomNo(new Room(cartController.generateRoomNumber(temp.product_id, temp.entry_date, temp.exit_date)));
                    roomReservation.setReservationId(res);
                    (new RoomReservationJpaController()).create(roomReservation);
                }

                out.write("success");
                response.sendRedirect("reservation.jsp?step=4");
            }
        } catch (ParseException ex) {
            Logger.getLogger(TambahKeranjang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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

    private CartSessionBeanLocal lookupCartSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartSessionBeanLocal) c.lookup("java:global/Avatar/Avatar-ejb/CartSessionBean!Pemesanan.CartLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
