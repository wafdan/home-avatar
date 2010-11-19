/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KelolaPembayaran;

import AvatarEntity.HallReservation;
import AvatarEntity.OtherServicesReservation;
import AvatarEntity.Reservation;
import AvatarEntity.ReservationItem;
import AvatarEntity.RoomReservation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Christian
 */
@Stateless
public class ReceiptGenerator implements ReceiptGeneratorLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /*
     * Menciptakan dokumen PDF bukti pembayaran; mengembalikan nama arsipnya
     * dalam URL absolut
     */
    public String generateDocument(Reservation reservation) {
        File dir = new File(".");
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        Date today = new Date();
        Locale locale = Locale.getDefault();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(today) + "_" + reservation.getReservationId() + ".pdf";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc,
                    new FileOutputStream(dir.getCanonicalPath() + "\\" + filename));
            doc.open();
            doc.add(new Paragraph("Payment Receipt", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD, new BaseColor(0, 0, 128))));
            doc.add(new Paragraph("Reservation ID: #" + reservation.getReservationId()));
            doc.add(new Paragraph("Customer name: " + reservation.getUsername().getName()));
            doc.add(new Paragraph("Confirmation date: " + sdf.format(reservation.getReservationTime())));
            doc.add(new List());
            doc.add(new ListItem("--------------------"));
            String lItem = "";
            for (ReservationItem item : reservation.getReservationItemCollection()) {
                if (item instanceof RoomReservation) {
                    RoomReservation sItem = (RoomReservation) item;
                    lItem = "Room " + sItem.getRoomNo().getRoomNo() + " (" + sItem.getRoomNo().getProductId().getProductType() + "), "
                            + sdf.format(sItem.getEntryDate()) + " - " + sdf.format(sItem.getExitDate());
                } else if (item instanceof HallReservation) {
                    HallReservation sItem = (HallReservation) item;
                    lItem = "Meeting package" + sItem.getProductId().getProductType()
                            + ", " + sdf.format(sItem.getUseDate()) + " (" + sItem.getAttendees() + "pax)";
                } else if (item instanceof OtherServicesReservation) {
                    OtherServicesReservation sItem = (OtherServicesReservation) item;
                    lItem = sItem.getProductId().getProductType();
                } else {
                    lItem = "unknown service???";
                }
                lItem += " => " + currencyFormat.format(item.getPrice());
                doc.add(new ListItem(lItem));
            }
            doc.add(new ListItem("--------------------"));
            doc.add(new ListItem("Total = " + currencyFormat.format(reservation.getTotalPrice()), FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD, new BaseColor(0,0,0))));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph("Signed,"));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph("Hotel management"));
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReceiptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReceiptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReceiptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filename;
    }
}
