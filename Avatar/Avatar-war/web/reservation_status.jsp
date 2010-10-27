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
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
    <head profile="http://gmpg.org/xfn/11">
        <title>Hotel Graha Bandung - Reservation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="imagetoolbar" content="no" />
        <link rel="stylesheet" href="styles/layout.css" type="text/css" />
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jqueryui.js"></script>
        <script type="text/javascript" src="script/showConfirmForm.js"></script>
        <script type="text/javascript" src="script/viewReservationDetail.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/jquerystyle.css" />"
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="wrapper col3">
            <div id="breadcrumb">
                <h1>Reservation Status</h1>
            </div>
        </div>

        <div class="wrapper col4">
        <div id="container">
        <div id="content" style="width:500px;">
        <%
            KonfirmasiPembayaranController kpc = new KonfirmasiPembayaranController();
            String name = (String) session.getAttribute("name");
            
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
        </div>
        </div>
        </div>
    </body>
</html>
