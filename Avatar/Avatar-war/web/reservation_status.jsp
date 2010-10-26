<%--
    Document   : reservation_status.jsp
    Created on : 21 Okt 10, 18:57:56
    Author     : kamoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*" %>
<%@page import="KonfirmasiPembayaran.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Collection" %>
<%@page import="java.io.FileOutputStream;" %>
<%@page import="com.itextpdf.text.*" %>
<%@page import="com.itextpdf.text.pdf.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            KonfirmasiPembayaranController kpc = new KonfirmasiPembayaranController();
            String name = (String) session.getAttribute("name");
            out.println(name);
            out.println("<br />");

            List<Reservation> UReserv = kpc.getUnpaidReservationByName(name);
            out.println("<H1>Unpaid Reservation</H1>");
            if (UReserv.size() < 1) {
                out.println("No Unpaid Reservation");
                out.println("<br />");
            } else {
                for (Reservation r : UReserv) {
                    out.println("Reservation Id = "+r.getReservationId()+" by "+r.getUsername().getUsername()+", Note : "+r.getNote());
                    out.println("<br />");
                    double total = 0;
                    for (ReservationItem curRes : r.getReservationItemCollection()) {
                        out.println("-> Reservation Item Id = "+curRes.getReservationItemId()+" - "+curRes.getReservationTime()+" : Rp "+curRes.getPrice());
                        out.println("<br />");
                        if (curRes instanceof RoomReservation) {
                            out.println("Entry Date = "+((RoomReservation) curRes).getEntryDate());
                            out.println("Exit Date = "+((RoomReservation) curRes).getExitDate());
                        } else if (curRes instanceof HallReservation) {
                            out.println("Date = "+((HallReservation) curRes).getUseDate());
                            out.println("Time = "+((HallReservation) curRes).getBeginTime()+"-"+((HallReservation) curRes).getEndTime());
                        } else if (curRes instanceof OtherServicesReservation) {
                            out.println("- "+((OtherServicesReservation) curRes).getProductId().getProductType());
                        }
                        out.println("<br />");
                        total += curRes.getPrice();
                    }
                    out.println("Total = Rp "+total);
                    out.println("<br /><br />");
                }
            }

            List<Reservation> PReserv = kpc.getPaidReservationByName(name);
            out.println("<H1>Paid Reservation</H1>");
            if (PReserv.size() < 1) {
                out.println("No Paid Reservation");
                out.println("<br />");
            } else {
                for (Reservation r : PReserv) {
                    out.println("Reservation Id = "+r.getReservationId()+" by "+r.getUsername().getUsername()+", Note : "+r.getNote());
                    out.println("<br />");
                    for (ReservationItem curRes : r.getReservationItemCollection()) {
                        out.println("-> Reservation Item Id = "+curRes.getReservationItemId()+" - "+curRes.getReservationTime()+" : Rp "+curRes.getPrice());
                        out.println("<br />");
                        if (curRes instanceof RoomReservation) {
                            out.println("Entry Date = "+((RoomReservation) curRes).getEntryDate());
                            out.println("Exit Date = "+((RoomReservation) curRes).getExitDate());
                        } else if (curRes instanceof HallReservation) {
                            out.println("Date = "+((HallReservation) curRes).getUseDate());
                            out.println("Time = "+((HallReservation) curRes).getBeginTime()+"-"+((HallReservation) curRes).getEndTime());
                        } else if (curRes instanceof OtherServicesReservation) {
                            out.println("- "+((OtherServicesReservation) curRes).getProductId().getProductType());
                        }
                        out.println("<br />");
                    }
                    out.println("Status = ");
                    if (kpc.isPaymentVerified(r)) {
                        out.println("Verified");
                    } else {
                        out.println("Not verified");
                    }
                    out.println("<br />");
                }
            }

            /*Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("d:/test.pdf"));
            document.open();
            document.add(new Paragraph("Hello, this is an example of how to use iText."));
            document.close();*/
        %>
    </body>
</html>
