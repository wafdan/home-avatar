/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengonfirmasiPembayaran;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.Date;
import javax.swing.JFileChooser;
import AvatarEntity.*;
import KonfirmasiPembayaran.*;


/**
 *
 * @author kamoe
 */
@WebServlet(name="DownloadPDF", urlPatterns={"/DownloadPDF"})
public class DownloadPDF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String reservId;
        reservId =  request.getParameter("reservationId");
        KonfirmasiPembayaranController c = new KonfirmasiPembayaranController();
        Reservation r = c.getReservationById(Integer.parseInt(reservId));
        try {
            File fl = new File("Receipt"+new Date()+".pdf");
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(fl);
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                document.add(new Paragraph("Receipt"));
                document.add(new Paragraph(""));
                document.add(new Paragraph("Reservation Id : "+reservId));
                document.add(new Paragraph("Name : "+r.getUsername().getName()));
                document.add(new Paragraph("Total Price : "+r.getTotalPrice()));
                document.add(new Paragraph(""));
                document.add(new Paragraph("Receptionist : "+r.getPayment().getUsername().getName()));
                document.close();
            }
            response.sendRedirect("reservation_status.jsp");
        } catch (Exception e) {
            e.printStackTrace();
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
