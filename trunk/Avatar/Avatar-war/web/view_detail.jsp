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
            out.println("<table>");
            int i = 1;
            for (ReservationItem curRes : r.getReservationItemCollection()) {
                if (curRes instanceof RoomReservation) {
                    out.println("<tr>"+i+". Room Reservation - Room "+((RoomReservation) curRes).getRoomNo().getRoomNo()+", "+((RoomReservation) curRes).getRoomNo().getProductId().getProductType()+"</tr>");
                    out.println("<tr><td># Entry Date</td><td>"+formatter.format(((RoomReservation) curRes).getEntryDate())+"</td></tr>");
                    out.println("<tr><td># Exit Date</td><td>"+formatter.format(((RoomReservation) curRes).getExitDate())+"</td></tr>");
                } else if (curRes instanceof HallReservation) {
                    out.println("<tr>"+i+". Hall Reservation - Package "+((HallReservation) curRes).getProductId().getProductType()+", Hall "+((HallReservation) curRes).getVenueNo().getVenueNo()+", "+((HallReservation) curRes).getVenueNo().getVenueName()+"</tr>");
                    out.println("<tr><td># Usage Time</td><td>"+formatter.format(((HallReservation) curRes).getUseDate())+","+timeformatter.format(((HallReservation) curRes).getBeginTime())+"-"+timeformatter.format(((HallReservation) curRes).getEndTime())+"</td></tr>");
                } else if (curRes instanceof OtherServicesReservation) {
                    out.println("<tr>"+i+". Other Services Reservation</tr>");
                    out.println("<tr># "+((OtherServicesReservation) curRes).getProductId().getProductType()+" x "+((OtherServicesReservation) curRes).getAmount()+"</tr>");
                }
                out.println("<tr><td># Price</td><td>"+currencyFormat.format(curRes.getPrice())+"</td></tr>");
                i++;
            }
            out.println("</table>");
        }
        out.println("<input class='button' type ='button' onclick='javascript:viewDetail("+rid+",0)' value='Hide Detail'>");
    } else {
        out.println("<input class='button' type ='button' onclick='javascript:viewDetail("+rid+",1)' value='View Detail'>");
    }
%>



