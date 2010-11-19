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
import KelolaReservasi.MengelolaReservasiController;
import Support.EmailSender;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
        String popup = "";
        SimpleDateFormat detail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (request.getParameter("verify") != null) {
                Locale locale = Locale.getDefault();
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
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
                            detail.format(res.getReservationTime()) + " as follows:\n");
                    messageContent += messageItemList;
                    messageContent += "    ------\n";
                    messageContent += ("    Total = " + currencyFormat.format(res.getTotalPrice()) + "\n\n");
                    messageContent += "Please make your payment done and confirm yours immediately.\n\nThank you for your attention.\n\n\n";
                    messageContent += "Sincerely yours,\nHotel Management";
                    try {
                        EmailSender.sendEmail(destAddress, "chrhad081@gmail.com", "", "Reservation Payment Reminder", messageContent);
                        popup = "<script language=\"javascript\" type=\"text/javascript\">\n"
                                + "<!--\n\twindow.alert('E-mail has been sent to "
                                + res.getUsername().getEmail() + ".');\n//-->\n</script>";
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
                                detail.format(res.getReservationTime()) + " as follows:\n");
                        messageContent += messageItemList;
                        messageContent += "    ------\n";
                        messageContent += ("    Total = " + currencyFormat.format(res.getTotalPrice()) + "\n\n");
                        messageContent += "Your payment receipt is attached with this mail.\n\nThank you for choosing our service.\n\n\n";
                        messageContent += "Sincerely yours,\nHotel Management";
                        try {
                            EmailSender.sendEmail(destAddress, "avatarhomeapp@gmail.com", "", "Payment Receipt", messageContent, attfile);
                            popup = "<script language=\"javascript\" type=\"text/javascript\">\n"
                                + "<!--\n\twindow.alert('E-mail has been sent to "
                                + res.getUsername().getEmail() + ".');\n//-->\n</script>";
                            File fdel = new File(attfile);
                            boolean succ = fdel.delete();
                            if (!succ) {
                                throw new IllegalArgumentException("Delete: deletion failed");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else if (request.getParameter("confirm") != null) {
                Integer reservationId = Integer.parseInt(request.getParameter("reservationId"));
                String username = request.getParameter("username");
                Date paymentDate = dateOnly.parse(request.getParameter("paymentDate"));
                String paymentMethod = request.getParameter("paymentMethod");
                String paymentBank = request.getParameter("paymentBank");
                String accountNumber = request.getParameter("accountNumber");
                double amount = Double.parseDouble(request.getParameter("amount"));
                PaymentJpaController pjpa = new PaymentJpaController();
                ReservationJpaController resjpa = new ReservationJpaController();
                StaffJpaController stjpa = new StaffJpaController();
                Payment pay = new Payment(new Date(), paymentDate, paymentMethod, paymentBank, accountNumber, amount);
                pay.setReservationId(resjpa.findReservation(reservationId));
                pay.setUsername(stjpa.findStaff(username));
                pjpa.create(pay);
            }
            // Inisialisasi Kontroler JPA dan Kelas Entity
            ReservationJpaController resjc = new ReservationJpaController();
            List<Reservation> lres = null;
            int page = 1, numperpage = 20, total = 0;
            if (request.getParameter("pg") != null)
                page = Integer.parseInt(request.getParameter("pg"));
            if (request.getParameter("mode") != null) {
                if (request.getParameter("mode").equals("unconf")) {
                    lres = resjc.findUnpaidReservationEntities(numperpage, numperpage * (page - 1));
                    total = resjc.getUnpaidReservationCount();
                } else if (request.getParameter("mode").equals("unver")) {
                    lres = resjc.findUnverifiedReservationEntities(numperpage, numperpage * (page - 1));
                    total = resjc.getUnverifiedReservationCount();
                } else if (request.getParameter("mode").equals("ver")) {
                    lres = resjc.findVerifiedReservationEntities(numperpage, numperpage * (page - 1));
                    total = resjc.getVerifiedReservationCount();
                } else if (request.getParameter("mode").equals("problem")) {
                    MengelolaReservasiController rescon = new MengelolaReservasiController();
                    lres = rescon.getReservationToWarn();
                    numperpage = total = lres.size();
                }
            } else {
                lres = resjc.findReservationEntities(numperpage, numperpage * (page - 1));
                total = resjc.getReservationCount();
            }
            request.setAttribute("returnList", lres);
            request.setAttribute("totalpage", (int) Math.ceil(((double) total) / numperpage));
            request.setAttribute("popup", popup);
            // Periksa Isi List :: TODO: Hapus
            /*Payment pay = null;
            for (Reservation res : lres) {
                System.out.println("Reservation ID: #" + res.getReservationId());
                System.out.println("Reservation Date: " + res.getReservationTime() + " aka " + detail.format(res.getReservationTime()));
                System.out.println("Reservation made " + (res.getIsOnspot() ? "on-spot" : "online"));
                System.out.println("Customer: " + res.getUsername().getUsername());
                System.out.println("Total: " + res.getTotalPrice());
                System.out.println("Reservation due: " + (res.getReservationPaymentLimit() == null ? "-" : dateOnly.format(res.getReservationPaymentLimit())));
                System.out.println("--- Payment Info ----");
                pay = res.getPayment();
                System.out.println("    " + (pay == null ? "not yet" : "confirmed"));
                if (pay != null) {
                    System.out.println("    at " + detail.format(pay.getConfirmTime()));
                    System.out.println("    payment date: " + dateOnly.format(pay.getPaymentDate()));
                    System.out.println("    amount: " + pay.getAmount());
                    System.out.println("    method: " + pay.getPaymentMethod());
                    System.out.println("    bank: " + pay.getPaymentBank());
                    System.out.println("    ac: " + pay.getAccountNumber());
                    System.out.println("    verifier: " + (pay.getUsername() == null ? "not verified" : pay.getUsername().getUsername()));
                }
                System.out.println("========#####========");
            }*/
            // Tampilkan ke JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/payment_manage.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(KelolaPembayaran.class.getName()).log(Level.SEVERE, null, ex);
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
