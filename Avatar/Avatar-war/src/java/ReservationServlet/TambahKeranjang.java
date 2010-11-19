/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationServlet;

import AvatarEntity.Customer;
import AvatarEntity.CustomerJpaController;
import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.Payment;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import Debug.Debug;
import Pemesanan.CartController;
import Pemesanan.CartSessionBeanLocal;
import Pemesanan.HallSessionInfo;
import Pemesanan.RoomSessionInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import Pemesanan.CartSessionBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zulfikar
 */
public class TambahKeranjang extends HttpServlet {

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
                System.out.println("Masuk sini");
                //Mendapatkan informasi buat roomnya
                String roomType = (String) request.getParameter("roomtype");
                String preCheckInDate = request.getParameter("roomcheckindate");
                String preCheckOutDate = request.getParameter("roomcheckoutdate");
                String preTotalRoom = request.getParameter("totalroom");

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date checkInDate;
                Date checkOutDate;
                short totalRoom;

                checkInDate = df.parse(preCheckInDate);
                checkOutDate = df.parse(preCheckOutDate);

                Calendar calOut = (Calendar.getInstance());
                calOut.setTime(checkOutDate);

                Calendar calIn = (Calendar.getInstance());
                calIn.setTime(checkInDate);

                if (!calOut.after(calIn)) {
                    response.sendRedirect("reservation.jsp?step=1&error=1");
                    return;
                }

                totalRoom = Short.parseShort(preTotalRoom);
                cartSessionBean1.addRoomCartElement(roomType, checkInDate, checkOutDate, totalRoom);
                session.setAttribute("roomcart", cartSessionBean1.getRoomCart());
                System.out.println("Jumlah cart untuk room : " + cartSessionBean1.getRoomCart().size());

                //out.write("<a href='reservation.jsp?step=2'>Lanjut </a>");
                response.sendRedirect("reservation.jsp?step=1");
            } else if (action.equals("delete")) {
            } else if (action.equals("proceed")) {
                ReservationJpaController resjpa = new ReservationJpaController();
                Reservation res = new Reservation();
                res.setIsOnspot(false);
                String usernameCustomer = (String) session.getAttribute("username");
                Customer cust = (new CustomerJpaController()).findCustomer(usernameCustomer);
                res.setUsername(cust);
                res.setNote("");

                resjpa.create(res);
                ArrayList<HallSessionInfo> listHallCart = cartSessionBean1.getHallCart();
                ArrayList<RoomSessionInfo> listRoomCart = cartSessionBean1.getRoomCart();
                Iterator<HallSessionInfo> iHallCart = listHallCart.iterator();
                Iterator<RoomSessionInfo> iRoomCart = listRoomCart.iterator();

                //yang room gw masih belum yakin harus divalidasi dulu ini sama ceha.
                CartController cartController = new CartController();
                Hall thisHall;
                while (iHallCart.hasNext()) {
                    HallSessionInfo temp = iHallCart.next();
                    thisHall = (new HallJpaController()).findHall(temp.product_id);
                    HallReservation hallReservation = new HallReservation();
                    hallReservation.setProductId(thisHall);
                    hallReservation.setUseDate(temp.use_date);
                    hallReservation.setReservationTime(new Date());
                    hallReservation.setReservationId(res);
                    hallReservation.setPrice(temp.price);
                    hallReservation.setBeginTime(thisHall.getStartTime());
                    hallReservation.setEndTime(thisHall.getEndTime());
                    (new HallReservationJpaController()).create(hallReservation);
                }

                while (iRoomCart.hasNext()) {
                    RoomSessionInfo temp = iRoomCart.next();
                    List<Room> roomKosong = cartController.generateRoomNumber(temp.product_id, temp.entry_date, temp.exit_date, temp.total);
                    for (int i = 0; i < temp.total; i++) {
                        RoomReservation roomReservation = new RoomReservation();
                        roomReservation.setEntryDate(temp.entry_date);
                        roomReservation.setExitDate(temp.exit_date);
                        roomReservation.setReservationTime(new Date());
                        Room r = roomKosong.get(0);
                        roomReservation.setRoomNo(r);
                        roomKosong.remove(r);
                        roomReservation.setPrice(temp.price);
                        roomReservation.setReservationId(res);
                        (new RoomReservationJpaController()).create(roomReservation);
                    }

                }

                out.write("success");
                cartSessionBean1.clearCart();
                response.sendRedirect("reservation.jsp?step=3");
            } else if (action.equals("addhall")) {
                String package_str = request.getParameter("hallpackage");
                String reservationdate_str = request.getParameter("reservationdate");
                String layout_str = request.getParameter("layout");
                String capacity_str = request.getParameter("capacity");
                String hallneeded_str = request.getParameter("hallneeded");

                Date reservationdate = (new SimpleDateFormat("MM/dd/yyyy")).parse(reservationdate_str);
                int capacity = Integer.parseInt(capacity_str);
                short hallneeded = Short.parseShort(hallneeded_str);
                int layoutid = Integer.parseInt(layout_str);

                cartSessionBean1.addHallCartElement(package_str, reservationdate, hallneeded, capacity, layoutid);
                Debug.debug("Jumlah elemen cart untuk si Hall : " + cartSessionBean1.getHallCart().size());
                response.sendRedirect("hallreservation.jsp?step=1");
                session.setAttribute("hallcart", cartSessionBean1.getHallCart());
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
            return (CartSessionBeanLocal) c.lookup("java:global/Avatar/Avatar-ejb/CartSessionBean!Pemesanan.CartSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
