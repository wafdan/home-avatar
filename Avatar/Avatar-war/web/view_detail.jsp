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
        out.println("Reservation Item(s) :<br />");
        KonfirmasiPembayaranController c = new KonfirmasiPembayaranController();
        Reservation r = c.getReservationById(Integer.parseInt(rid));
        if (r.getReservationItemCollection().size() < 1) {
            out.println("No Reservation Item <br />");
        } else
        {
            int i = 1;
            for (ReservationItem curRes : r.getReservationItemCollection()) {
                if (curRes instanceof RoomReservation) {
                    out.println(i+". Room "+((RoomReservation) curRes).getRoomNo().getRoomNo()+", "+((RoomReservation) curRes).getRoomNo().getProductId().getProductType());
                    out.println("<table>");
                    out.println("<tr><td># Entry Date</td><td>"+formatter.format(((RoomReservation) curRes).getEntryDate())+"</td></tr>");
                    out.println("<tr><td># Exit Date</td><td>"+formatter.format(((RoomReservation) curRes).getExitDate())+"</td></tr>");
                } else if (curRes instanceof HallReservation) {
                    out.println(i+". Package "+((HallReservation) curRes).getProductId().getProductType()+", Hall "+((HallReservation) curRes).getVenueNo().getVenueNo()+", "+((HallReservation) curRes).getVenueNo().getVenueName());
                    out.println("<table>");
                    out.println("<tr><td># Usage Date</td><td>"+formatter.format(((HallReservation) curRes).getUseDate())+"</td></tr>");
                    out.println("<tr><td># Usage Time</td><td>"+timeformatter.format(((HallReservation) curRes).getBeginTime())+"-"+timeformatter.format(((HallReservation) curRes).getEndTime())+"</td></tr>");
                } else if (curRes instanceof OtherServicesReservation) {
                    out.println("<tr>"+i+". "+((OtherServicesReservation) curRes).getProductId().getProductType()+" x "+((OtherServicesReservation) curRes).getAmount()+"</tr>");
                    out.println("<table>");
                }
                out.println("<tr><td># Price</td><td>"+currencyFormat.format(curRes.getPrice())+"</td></tr>");
                out.println("</table>");
                i++;
            }
        }
        out.println("<input class='button' type ='button' onclick='javascript:viewDetail("+rid+",0)' value='Hide Detail'>");
    } else {
        out.println("<input class='button' type ='button' onclick='javascript:viewDetail("+rid+",1)' value='View Detail'>");
    }
%>



