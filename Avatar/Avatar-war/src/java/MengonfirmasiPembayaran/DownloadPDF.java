/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengonfirmasiPembayaran;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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
        //processRequest(request, response);
        String reservId;
        reservId =  request.getParameter("reservationId");
        KonfirmasiPembayaranController c = new KonfirmasiPembayaranController();
        Reservation r = c.getReservationById(Integer.parseInt(reservId));
        //Profile p = c.getProfilHotel();

        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition"," attachment; filename=\"receipt.pdf\"");

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

            Document document = new Document(PageSize.A6, 10, 10, 10, 10);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Paragraph line = new Paragraph("Receipt", titleFont);
            line.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(line);
            document.add(new Paragraph(""));
            line.setAlignment(Paragraph.ALIGN_LEFT);
            line = new Paragraph("Reservation Id : "+reservId, contentFont);
            document.add(line);
            line = new Paragraph("Name : "+r.getUsername().getName(), contentFont);
            document.add(line);
            line = new Paragraph("Total Price : "+r.getTotalPrice(), contentFont);
            document.add(line);
            document.add(new Paragraph(""));
            line = new Paragraph("Receptionist : "+r.getPayment().getUsername().getName(), contentFont);
            document.add(line);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
