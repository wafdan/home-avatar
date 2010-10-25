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
        <h1>Hello World!</h1>
        <%
            KonfirmasiPembayaranController kpc = new KonfirmasiPembayaranController();
            String username = (String) session.getAttribute("name");
            List<Reservation> reserv = kpc.getReservation();

            for (Reservation r : reserv) {
                out.println(""+r.getReservationId()+" "+r.getUsername().getUsername()+" "+r.getNote());
                for (ReservationItem curRes : r.getReservationItemCollection()) {
                    out.println("->"+curRes.getReservationItemId()+" "+curRes.getReservationTime()+" "+curRes.getPrice());
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
