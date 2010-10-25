<%--
    Document   : reservation
    Created on : 20 Okt 10, 13:03:55
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Accomodation"%>
<%@page import="AvatarEntity.Hall"%>
<%@page import="AvatarEntity.HallJpaController"%>
<%@page import="AvatarEntity.AccomodationJpaController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="Pemesanan.CartLocal"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="Pemesanan.*" %>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

$(function(){

// Datepicker
$('.datepicker').datepicker({
inline: true
});

//hover states on the static widgets
$('#dialog_link, ul#icons li').hover(
function() { $(this).addClass('ui-state-hover'); },
function() { $(this).removeClass('ui-state-hover'); }
);

});


<%



            try {
                //out.write((String)request.getParameter("step"));
                if (request.getParameter("step").equals("1")) {
%>
<h1> Reservation Form</h1>
<p> Please fill up this form to book our facility. </p>
<h2>ROOM</h2>

<form action="TambahKeranjang?action=add" method="POST">
    <label>Room type : </label>
    <select name="roomtype">
        <%
                            /*INI BUAT MASUKKIN TIPE ROOMTYPENYA DARI DATABASE*/
                            List<Accomodation> listAccomodation = (new AccomodationJpaController()).findAccomodationEntities();
                            Iterator<Accomodation> i = listAccomodation.iterator();


                            while (i.hasNext()) {
                                Accomodation temp;
                                temp = i.next();
        %>
        <option value="<%=temp.getProductId()%>"><%=temp.getProductType()%></option>
        <%
                            }
        %>
    </select>

    <label>Total room</label> <input type="text" name="totalroom" />
    <!-- BAGIAN DHANA -->
    <label>Check-in date</label> <input name="roomcheckindate" type="text" class="datepicker" class="date-pick" />
    <label>Check-out date</label> <input name="roomcheckoutdate" type="text" class="datepicker" />
    <!-- END OF BAGIAN DHANA -->

    <h2>HALL</h2>
    <label>Package</label>
    <select name="packagetype">
        <%
                            /*INI BUAT MASUKKIN TIPE HALLTYPE DARI DATABASE*/
                            List<Hall> listhall = (new HallJpaController()).findHallEntities();
                            Iterator<Hall> iHall = listhall.iterator();
                            while (iHall.hasNext()) {
                                Hall hall = iHall.next();
        %>
        <option value="<%=hall.getProductId()%>"> <%=hall.getProductType()%> </option>
        <%
                            }
        %>
    </select>
    <label>Total hall needed</label> <input type="text" name="totalhall" />
    <!-- BAGIAN DHANA LAGI -->
    <label>Date</label> <input name="halldate" type="text" class="datepicker" />
    <!-- END OF BAGIAN DHANA -->
    <input type="submit" value="SUBMIT">
</form>

<%
                } else if (request.getParameter("step").equals("2")) {
                    /* INI HALAMAN KEDUA */
                    ArrayList<RoomSessionInfo> cartRoom = (ArrayList<RoomSessionInfo>) session.getAttribute("roomcart");
                    ArrayList<HallSessionInfo> cartHall = (ArrayList<HallSessionInfo>) session.getAttribute("hallcart");
                    Iterator<RoomSessionInfo> iRoom = cartRoom.iterator();
                    Iterator<HallSessionInfo> iHall = cartHall.iterator();
                    int i = 0;

%>

<h1>THIS IS RESUME OF YOUR RESERVATION</h1>
<h2>Room</h2>
<table>
    <tr>
        <th>No.</th>
        <th>Facility</th>
        <th>Duration (days)</th>
        <th>Unit price weekend</th>
        <th>Unit price weekday</th>
        <th>Status</th>
        <th>Total price</th>
    </tr>
    <tr>
        <% /*ini buat produce isi td-nya*/

                            CartController chartController = new CartController();
                            double totalBill = 0;
                            while (iRoom.hasNext()) {
                                i++;
                                RoomSessionInfo temp = iRoom.next();
                                double singleRowPrice;
                                if (temp.available) {
                                    singleRowPrice = chartController.countTotalBill(temp.entry_date, temp.exit_date, chartController.getRoomPriceWeekday(temp.product_id), chartController.getRoomPriceWeekend(temp.product_id));
                                    totalBill += singleRowPrice;
                                } else {
                                    singleRowPrice = 0;
                                }
        %>
    <tr>
        <td><%=i%></td>
        <td><%=chartController.getRoomProductType(temp.product_id)%></td>
        <td><%=chartController.getDuration(temp.entry_date, temp.exit_date)%></td>
        <td><%=chartController.getRoomPriceWeekend(temp.product_id)%></td>
        <td><%=chartController.getRoomPriceWeekday(temp.product_id)%></td>
        <td><%=String.valueOf(temp.available)%></td>
        <td><%=singleRowPrice%></td>
    </tr>
    <%
                        }
    %>
</tr>
</table>

<h2>Hall</h2>
<table>
    <tr>
        <th>No.</th>
        <th>Hall type</th>
        <th>Date</th>
        <th>Rate</th>
        <th>Status</th>
    </tr>

    <%
                        i = 0;
                        while (iHall.hasNext()) {
                            i++;
                            HallSessionInfo temp = iHall.next();
                            double singleRowPrice=0;
                            if(temp.available){
                                singleRowPrice=chartController.getHallPrice(temp.product_id);
                            }
                            totalBill+=singleRowPrice;
    %>
    <tr>
        <td><%=i%></td>
        <td><%=chartController.getHallType(temp.product_id)%></td>
        <td><%=(new SimpleDateFormat("dd/MM/yyyy")).format(temp.use_date)%></td>
        <td><%=singleRowPrice %></td>
        <td><%=temp.available%></td>
    </tr>

    <%                                }
    %>

</table>

<a href="reservation.jsp?step=1">Request more...</a>
<p>Your bill : Rp. <%=totalBill %></p>
<a href="reservation.jsp?step=3">Proceed</a>

<%
                session.setAttribute("totalprice", totalBill);
                } else if (request.getParameter("step").equals("3")) {
                    /* INI HALAMAN KETIGA */
                    double totalPrice=(Double)session.getAttribute("totalprice");
%>
<p>Please transfer Rp. <%=totalPrice %> to one of these account number : </p>
<ol>
    <li>
        Bank : BRI
        Acc. No. : 0992-19920776-1
        Holder name : Zulfikar Hakim
    </li>

    <li>
        Bank : BCA
        Acc No. : 03976-1-374-1
        Holder name : Zulfikar Hakim
    </li>
</ol>

<FORM METHOD="LINK" ACTION="#">
    <INPUT TYPE="submit" VALUE="Save Reservation">
</FORM>

<form method="link" action="#">
    <input type="submit" value="Go to main page"
</form>



<%                            } else if (request.getParameter("step").equals("4")){
                    /* INI HALAMAN KEEMPAT */
    %> 
    <p> Your reservation has been saved. Please pay and confirm in 5 workdays. If you have paid, go to confirmation page below</p>
    <a href="#">Confirm know</a>
    <%

%>

<%                    }
            } catch (NullPointerException ex) {
                ex.printStackTrace();

            }
%>
</body>
</html>
