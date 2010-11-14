<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Layout" %>
<%@page import="AvatarEntity.LayoutJpaController" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List" %>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="Layanan.*" %>
<%@page import="AvatarEntity.Accomodation"%>
<%@page import="AvatarEntity.Hall"%>
<%@page import="AvatarEntity.HallJpaController"%>
<%@page import="AvatarEntity.AccomodationJpaController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="Pemesanan.CartSessionBeanLocal"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="Pemesanan.*" %>
<%@page import="java.text.SimpleDateFormat"%>



<% /* ******************************************************************
            Menampilan reservation dari si room nya */


            ArrayList<RoomSessionInfo> cartRoom = (ArrayList<RoomSessionInfo>) session.getAttribute("roomcart");
            ArrayList<HallSessionInfo> cartHall = (ArrayList<HallSessionInfo>) session.getAttribute("hallcart");
            CartController chartController = new CartController();
            double totalBill = 0;
            if (cartRoom != null && cartHall != null) {
%>
<h1>YOUR RESERVATION CART</h1>
<%            }
            if ((cartRoom != null)) {
                if (cartRoom.size() != 0) {
                    Iterator<RoomSessionInfo> iRoom = cartRoom.iterator();
                    int i = 0;

%>
<p>Your reservation will not saved until you click <b>"PROCEED" </b></p>
<h2>Room Reservation</h2>
<table>
    <tr>
        <th>No.</th>
        <th>Facility</th>
        <th>Total</th>
        <th style="width:150px;">Check-in date</th>
        <th>Duration (days)</th>
        <th>Unit price weekend</th>
        <th>Unit price weekday</th>
        <th>Total price</th>
    </tr>
    <tr>
        <% /*ini buat produce isi td-nya*/
                            while (iRoom.hasNext()) {
                                i++;
                                RoomSessionInfo temp = iRoom.next();
                                double singleRowPrice = 0;
                                singleRowPrice = temp.price*temp.total;
                                totalBill += singleRowPrice;

        %>
    <tr>
        <td><%=i%></td>
        <td><%=chartController.getRoomProductType(temp.product_id)%></td>
        <td><%=temp.total%></td>
        <td><%=(new SimpleDateFormat("EEE, d MMM yyyy")).format(temp.entry_date) %></td>
        <td><%=chartController.getDuration(temp.entry_date, temp.exit_date)%></td>
        <td><%=chartController.getRoomPriceWeekend(temp.product_id)%></td>
        <td><%=chartController.getRoomPriceWeekday(temp.product_id)%></td>
        <td><%=singleRowPrice %></td>
    </tr>
    <%
                        }
    %>
</tr>
</table>
<%

                }
            }

            /* ******************************************************************
            Menampilkan reservasi dari Hall nya*/
            if (!(cartHall == null)) {
                if (cartHall.size() != 0) {
                    Iterator<HallSessionInfo> iHall = cartHall.iterator();
%>

<h2>Hall</h2>
<table>
    <tr>
        <th>No.</th>
        <th>Date</th>
        <th>Package</th>
        <th>Layout</th>
        <th>Attendees</th>
        <th>Total Room</th>
        <th>Rate</th>
    </tr>

    <%
                        int i = 0;
                        while (iHall.hasNext()) {
                            i++;
                            HallSessionInfo temp = iHall.next();
                            double singleRowPrice = 0;
                            singleRowPrice = chartController.getHallPrice(temp.product_id);
                            totalBill += singleRowPrice;
    %>
    <tr>
        <td><%=i%></td>
        <td><%=(new SimpleDateFormat("dd/MM/yyyy")).format(temp.use_date)%></td>
        <td><%=temp.product_name%></td>
        <td><%=temp.total%></td>
        <td><%=temp.layout_name%></td>
        <td><%=temp.attendees%></td>
        <td><%=temp.total%></td>
        <td><%=singleRowPrice%></td>
    </tr>

    <%                                }
    %>

</table>
<%
                }
            }
            try {
                    session.setAttribute("totalprice", totalBill);
                    if(totalBill!=0){
%>
<p>Your bill : Rp. <%= totalBill%></p>
<a href="TambahKeranjang?action=proceed">Proceed</a>
<br> <br> 
<%}
            } catch (NullPointerException ex) {
            }

            %>


