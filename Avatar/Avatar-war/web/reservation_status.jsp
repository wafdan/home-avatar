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
        <%
                    ProfileJpaController pjc=new ProfileJpaController();
                    Profile p=pjc.findProfile(Boolean.TRUE);
        %>
        <title><%=p.getHotelName() %> - Reservation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="imagetoolbar" content="no" />
        <link rel="stylesheet" href="styles/layout.css" type="text/css" />
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jqueryui.js"></script>
        <script type="text/javascript" src="script/showConfirmForm.js"></script>
        <script type="text/javascript" src="script/viewReservationDetail.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/jquerystyle.css" />
        <script type="text/javascript">
            $(function(){
                // Datepicker
                $('.datepicker').datepicker({
                    inline: true, dateFormat : "yy-mm-dd"
                });
                //hover states on the static widgets
                $('#dialog_link, ul#icons li').hover(
                function() { $(this).addClass('ui-state-hover'); },
                function() { $(this).removeClass('ui-state-hover'); }
            );
            });
        </script>
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
            if (Reserv.size() < 1) {
                out.println("No Reservation");
                out.println("<br />");
            } else {
                for (Reservation r : Reserv) {
                    out.println("Reservation Id : "+r.getReservationId());
                    out.println("<div class='tabres'>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<td class='col1'>Total Price</td><td class='col2'>"+currencyFormat.format(r.getTotalPrice())+"</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class='col1'>Note</td><td class='col2'>"+r.getNote()+"</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class='col1'>Payment Status</td><td class='col2'>");
                    if (kpc.getPaymentStatus(r) == 1) {
                        out.println("Unpaid");
                    } else if (kpc.getPaymentStatus(r) == 2) {
                        out.println("Verified");
                    } else {
                        out.println("Not Verified");
                    }
                    out.println("</td></tr></table>");
                    out.println("</div>");
                    //out.println("<input type='text' id='payment_date"+r.getReservationId()+"' name='payment_date' class='datepicker' />");
                    out.println("<div class='detail' id=detail"+r.getReservationId()+"><input class='button' type ='button' onclick='javascript:viewDetail("+r.getReservationId()+",1)' value='View Detail'></div>");
                    out.println("<div class='tabconfirm_form'>");
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
                    out.println("</div>");
                    out.println("<br /><br />");
                }
            }
        %>
        </div>
        <div id="column">
        <div class="subnav">
            <h2>Reservation</h2>
            <ul>
                <li><a href="reservation.jsp?step=1">Room Reservation</a></li>
                <li><a href="hallreservation.jsp?step=1">Hall Reservation</a></li>
            </ul>
        </div>

        </div>
        </div>
        <div class="clear"></div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
