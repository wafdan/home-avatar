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
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.util.Locale" %>

<%-- start object initialization --%>
<%
SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm");
Locale locale = Locale.getDefault();
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<%-- end object initialization --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="script/showConfirmForm.js"></script>
        <script type="text/javascript" src="script/viewReservationDetail.js"></script>
    </head>
    <body>
        <%
            KonfirmasiPembayaranController kpc = new KonfirmasiPembayaranController();
            String name = (String) session.getAttribute("name");
            out.println(name);
            out.println("<br />");

            List<Reservation> Reserv = kpc.getReservationByName(name);
            out.println("<H1>Reservations</H1>");
            if (Reserv.size() < 1) {
                out.println("No Reservation");
                out.println("<br />");
            } else {
                for (Reservation r : Reserv) {
                    out.println("Reservation Id : "+r.getReservationId()+"<br />");
                    out.println("Total Price : "+currencyFormat.format(r.getTotalPrice())+"<br />");
                    out.println("Note : "+r.getNote()+"<br />");
                    out.println("Payment Status : ");
                    if (kpc.getPaymentStatus(r) == 1) {
                        out.println("Unpaid");
                    } else if (kpc.getPaymentStatus(r) == 2) {
                        out.println("Verified");
                    } else {
                        out.println("Not Verified");
                    }
                    out.println("<br />");
                    out.println("<div id=detail"+r.getReservationId()+"><input class='button' type ='button' onclick='javascript:viewDetail("+r.getReservationId()+",1)' value='View Detail'></div>");
                    if (kpc.getPaymentStatus(r) == 1) {
                        out.println("<div id=confirm_form"+r.getReservationId()+"><input class='button' type ='button' onclick='javascript:showForm("+r.getReservationId()+",1)' value='Confirm'></div>");
                    } else {
                        if (kpc.getPaymentStatus(r) == 2) {
                            out.println("<form method='post' name='download' id='download' action='DownloadPDF'>");
                            out.println("<input type='hidden' name='reservationId' id='reservationId' value='"+r.getReservationId()+"' />");
                            out.println("<input class='button' type ='submit' value='Download Receipt'>");
                            out.println("</form>");
                        }
                    }
                    out.println("<br /><br />");
                }
            }
        %>
    </body>
</html>
