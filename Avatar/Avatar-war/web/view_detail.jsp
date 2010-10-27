<%-- 
    Document   : view_detail
    Created on : 27 Okt 10, 9:53:00
    Author     : kamoe
--%>

<%@page import="AvatarEntity.*" %>
<%@page import="KonfirmasiPembayaran.*" %>
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

<%
    String rid = request.getParameter("id");
    String show = request.getParameter("show");

    if (show.equals("true")) {
        KonfirmasiPembayaranController c = new KonfirmasiPembayaranController();
        Reservation r = c.getReservationById(Integer.parseInt(rid));
        for (ReservationItem curRes : r.getReservationItemCollection()) {
            if (curRes instanceof RoomReservation) {
                out.println("Room Reservation<br />");
                out.println("Entry Date = "+formatter.format(((RoomReservation) curRes).getEntryDate())+"<br />");
                out.println("Exit Date = "+formatter.format(((RoomReservation) curRes).getExitDate()));
            } else if (curRes instanceof HallReservation) {
                out.println("Hall Reservation<br />");
                out.println("Usage Time = "+formatter.format(((HallReservation) curRes).getUseDate())+","+timeformatter.format(((HallReservation) curRes).getBeginTime())+"-"+timeformatter.format(((HallReservation) curRes).getEndTime()));
            } else if (curRes instanceof OtherServicesReservation) {
                out.println("Other Services Reservation<br />");
                out.println("- "+((OtherServicesReservation) curRes).getProductId().getProductType());
            }
            out.println("<br />");
            out.println("Price = "+currencyFormat.format(curRes.getPrice())+"<br />");
        }
        out.println("<input class='button' type ='button' onclick='javascript:viewDetail("+rid+",0)' value='Hide Detail'>");
    } else {
        out.println("<input class='button' type ='button' onclick='javascript:viewDetail("+rid+",1)' value='View Detail'>");
    }
%>



