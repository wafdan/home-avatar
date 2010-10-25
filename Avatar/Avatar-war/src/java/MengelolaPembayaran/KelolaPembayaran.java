/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPembayaran;

import AvatarEntity.Customer;
import AvatarEntity.HallReservation;
import AvatarEntity.OtherServicesReservation;
import AvatarEntity.Payment;
import AvatarEntity.PaymentJpaController;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.ReservationJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.Staff;
import AvatarEntity.StaffJpaController;
import AvatarEntity.exceptions.IllegalOrphanException;
import AvatarEntity.exceptions.NonexistentEntityException;
import KelolaPembayaran.ReceiptGenerator;
import Support.EmailSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class KelolaPembayaran extends HttpServlet {
   
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
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("verify") != null) {
                Locale locale = Locale.getDefault();
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                ReservationJpaController rjpa = new ReservationJpaController();
                Integer reservationId = Integer.parseInt(request.getParameter("reservationId"));
                Reservation res = rjpa.findReservation(reservationId);
                Customer cust = res.getUsername();
                Collection<ReservationItem> lresitem = res.getReservationItemCollection();
                String messageContent = "To: Mr/Ms/Mrs. " + cust.getName() + "\n\n";
                String messageItemList = "";
                for (ReservationItem item : lresitem) {
                    messageItemList += ("    " + item.getReservationItemId() + ". ");
                    if (item instanceof RoomReservation) {
                        messageItemList += ("Room " + ((RoomReservation) item).getRoomNo().getRoomNo());
                    } else if (item instanceof HallReservation) {
                        messageItemList += ("Package " + ((HallReservation) item).getProductId().getProductId());
                    } else if (item instanceof OtherServicesReservation) {
                        messageItemList += ("" + ((OtherServicesReservation) item).getProductId().getProductId());
                    } else {
                        messageItemList += "unknown service???";
                    }
                    messageItemList += (" => " + currencyFormat.format(item.getPrice()));
                    messageItemList += "\n";
                }
                String destAddress = res.getUsername().getEmail();
                if (request.getParameter("verify").equals("Remind")) {
                    messageContent += "Dear customer,\n\n";
                    messageContent += ("Please be reminded that you have made reservation on " +
                            formatter.format(res.getReservationTime()) + " as follows:\n");
                    messageContent += messageItemList;
                    messageContent += "    ------\n";
                    messageContent += ("    Total = " + currencyFormat.format(res.getTotalPrice()) + "\n\n");
                    messageContent += "Please make your payment done and confirm yours immediately.\n\nThank you for your attention.\n\n\n";
                    messageContent += "Sincerely yours,\nHotel Management";
                    try {
                        EmailSender.sendEmail(destAddress, "chrhad081@hotmail.com", "", "Reservation Payment Reminder", messageContent);
                    } catch (Exception ex) {
                        Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (request.getParameter("verify").equals("Verify")) {
                    if (res.getPayment() != null) { // jika pembayaran sudah dikonfirmasi
                        // Memperbaharui basis data
                        PaymentJpaController pjpa = new PaymentJpaController();
                        StaffJpaController sjpa = new StaffJpaController();
                        Payment pay = res.getPayment();
                        Staff verifier = sjpa.findStaff((String) session.getAttribute("username"));
                        pay.setUsername(verifier);
                        try { // penulisan ke basis data
                            pjpa.edit(pay);
                        } catch (IllegalOrphanException ex) {
                            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // Mengirim kepada pengguna
                        ReceiptGenerator recgen = new ReceiptGenerator();
                        String attfile = recgen.generateDocument(res);
                        messageContent += "Dear customer,\n\n";
                        messageContent += ("You have completed payment for your reservation made on " +
                                formatter.format(res.getReservationTime()) + " as follows:\n");
                        messageContent += messageItemList;
                        messageContent += "    ------\n";
                        messageContent += ("    Total = " + currencyFormat.format(res.getTotalPrice()) + "\n\n");
                        messageContent += "Your payment receipt is attached with this mail.\n\nThank you for choosing our service.\n\n\n";
                        messageContent += "Sincerely yours,\nHotel Management";
                        try {
                            EmailSender.sendEmail(destAddress, "chrhad081@hotmail.com", "", "Payment Receipt", messageContent, attfile);
                        } catch (Exception ex) {
                            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            // Inisialisasi Kontroler JPA dan Kelas Entity
            ReservationJpaController resjc = new ReservationJpaController();
            List<Reservation> lres = resjc.findReservationEntities();
            request.setAttribute("returnList", lres);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/payment_manage.jsp");
            dispatcher.forward(request, response);
            /*for (Reservation item : lres) {
                out.println("ID: " + item.getReservationId() + "<br />");
                out.println("User: " + item.getUsername().getName() + "<br />");
                out.println("Amount: " + item.getTotalPrice() + "<br />");
                out.println("Reservation time: " + item.getReservationTime() + "<br />");
                out.println("On spot? " + (item.getIsOnspot() ? "yes" : "no") + "<br />");
                out.println("Confirmed? " + (item.getPayment() != null ? "yes" : "no") + "<br />");
                out.println("Verified? " + (item.getPayment() != null ? (item.getPayment().getUsername() != null ? "yes" : "no") : "no") + "<br />");
                out.println("Note: " + item.getNote() + "<br />");
                out.println("Reservation item(s):<br />");
                for (ReservationItem subitem : item.getReservationItemCollection()) {
                    out.println("\t" + subitem.getReservationItemId() + ": Rp " + subitem.getPrice() + "<br />");
                }
                out.println("-----<br />");
            }*/
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
