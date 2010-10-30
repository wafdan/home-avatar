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
import java.text.NumberFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;



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
        Profile p = c.getProfilHotel();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
        Locale locale = Locale.getDefault();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition"," attachment; filename=\"receipt.pdf\"");

            Font hotelFont = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 6);
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 6);

            Document document = new Document(PageSize.A6, 10, 10, 10, 10);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            
            Paragraph line = new Paragraph(p.getHotelName(),hotelFont);
            document.add(line);
            line = new Paragraph(p.getHotelAddress1(), headerFont);
            document.add(line);
            line = new Paragraph(p.getHotelAddress2(), headerFont);
            document.add(line);
            line = new Paragraph(p.getHotelCity()+", "+p.getHotelCountry(), headerFont);
            document.add(line);
            line = new Paragraph("Phone : "+p.getHotelPhone(), headerFont);
            document.add(line);
            line = new Paragraph("Email : "+p.getHotelEmail(), headerFont);
            document.add(line);
            line = new Paragraph("Payment Receipt", titleFont);
            line.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(line);
            line.setAlignment(Paragraph.ALIGN_LEFT);
            line = new Paragraph("Name : "+r.getUsername().getName(), contentFont);
            document.add(line);
            line = new Paragraph("Address : "+r.getUsername().getAddress1(), contentFont);
            document.add(line);
            
            line = new Paragraph("Reservation Id : "+reservId, contentFont);
            document.add(line);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(3);
            for (ReservationItem curRes : r.getReservationItemCollection()) {
                if (curRes instanceof RoomReservation) {
                    table.addCell(borderlessCell("Room "+((RoomReservation) curRes).getRoomNo().getRoomNo(),Element.ALIGN_LEFT,true));
                    table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell(currencyFormat.format(curRes.getPrice()),Element.ALIGN_RIGHT,false));
                    table.addCell(borderlessCell("- Entry Date ",Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell((formatter.format(((RoomReservation) curRes).getEntryDate())),Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell("",Element.ALIGN_RIGHT,false));
                    table.addCell(borderlessCell("- Exit Date ",Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell((formatter.format(((RoomReservation) curRes).getExitDate())),Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell("",Element.ALIGN_RIGHT,false));
                } else if (curRes instanceof HallReservation) {
                    table.addCell(borderlessCell("Venue "+((HallReservation) curRes).getVenueNo().getVenueNo(),Element.ALIGN_LEFT,true));
                    table.addCell(borderlessCell(((HallReservation) curRes).getVenueNo().getVenueName(),Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell(currencyFormat.format(curRes.getPrice()),Element.ALIGN_RIGHT,false));
                    table.addCell(borderlessCell("- Usage Time ",Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell((formatter.format(((HallReservation) curRes).getUseDate())),Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell("",Element.ALIGN_RIGHT,false));
                    table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell((timeformatter.format(((HallReservation) curRes).getBeginTime())+"-"+timeformatter.format(((HallReservation) curRes).getEndTime())),Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell("",Element.ALIGN_RIGHT,false));
                } else if (curRes instanceof OtherServicesReservation) {
                    table.addCell(borderlessCell(""+(((OtherServicesReservation) curRes).getProductId().getProductType()),Element.ALIGN_LEFT,true));
                    table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
                    table.addCell(borderlessCell(currencyFormat.format(curRes.getPrice()),Element.ALIGN_RIGHT,false));
                }
            }
            table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
            table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
            table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
            table.addCell(borderlessCell("",Element.ALIGN_LEFT,false));
            table.addCell(borderlessCell("Total Price : ",Element.ALIGN_LEFT, true));
            table.addCell(borderlessCell(currencyFormat.format(r.getTotalPrice()),Element.ALIGN_RIGHT, false));
            document.add(table);
            
            
            document.add(new Paragraph(" "));
            line = new Paragraph(formatter.format(new Date()), contentFont);
            line.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(line);
            line = new Paragraph("Receptionist : "+r.getPayment().getUsername().getName(), contentFont);
            line.setAlignment(Paragraph.ALIGN_LEFT);
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

    private PdfPCell borderlessCell(String s, int alignment, boolean bold){
        PdfPCell cell = new PdfPCell();
        Font f;
        if (bold == true) {
            f= new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD);
        } else {
            f= new Font(Font.FontFamily.HELVETICA, 6);
        }
        cell.setBorder(0);
        cell.setHorizontalAlignment(alignment);
        cell.addElement(new Paragraph(s,f));
        return cell;
}

}
