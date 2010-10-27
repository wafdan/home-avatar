
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="Layanan.*" %>
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
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
    <head profile="http://gmpg.org/xfn/11">
        <title>Hotel Graha Bandung - Reservation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="imagetoolbar" content="no" />
        <link rel="stylesheet" href="styles/layout.css" type="text/css" />
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jqueryui.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/jquerystyle.css" />"
        <script type="text/javascript">
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


            
            var checkDateKosong=function()
            {
                //return false kalo ga valid
                
                var roomcheckinlength=document.syalala.roomcheckindate.value.length;
                //alert(roomcheckinlength);
                var roomcheckoutlength=document.syalala.roomcheckoutdate.value.length;
                var halldate=document.syalala.halldate.value.length;
                var room=document.syalala.roomcheckbox.checked;
                var hall=document.syalala.hallcheckbox.checked;
                var retval=true;

                if(room){
                    retval=retval&&!(roomcheckinlength==0 || roomcheckoutlength==0);
                }

                if(hall)
                {
                    retval=retval&&!(halldate==0)
                }

                if(retval)
                    alert("Date field must be filled");
                return retval;            
            }

            var changeRoom=function()
            {
                // var kondisi=document.getElementById("roomcheckbox").checked;
                var kondisi=document.syalala.roomcheckbox.checked;
                //alert(document.syalala.roomcheckbox.checked);
                if(!kondisi){
                    document.syalala.roomcheckindate.disabled=true;
                    document.syalala.roomcheckoutdate.disabled=true;
                    document.syalala.roomtype.disabled=true;
                    document.syalala.totalroom.disabled=true;
                }else{
                    document.syalala.roomcheckindate.disabled=false;
                    document.syalala.roomcheckoutdate.disabled=false;
                    document.syalala.roomtype.disabled=false;
                    document.syalala.totalroom.disabled=false;
                }

                var kondisi2=document.syalala.hallcheckbox.checked;

                if(kondisi || kondisi2){
                    document.syalala.tombol.disabled=false;
                }
                else{
                    document.syalala.tombol.disabled=true;
                }
            }

            var changeHall=function()
            {
                var kondisi=document.syalala.hallcheckbox.checked;
                if(kondisi)
                {
                    document.syalala.halldate.disabled=false;
                    document.syalala.packagetype.disabled=false;
                    document.syalala.totalhall.disabled=false;

                }
                else
                {
                    document.syalala.halldate.disabled=true;
                    document.syalala.packagetype.disabled=true;
                    document.syalala.totalhall.disabled=true;
                }

                var kondisi2=document.syalala.roomcheckbox.checked;

                if(kondisi || kondisi2){
                    document.syalala.tombol.disabled=false;
                }
                else{
                    document.syalala.tombol.disabled=true;
                }

            }
        </script>
    </head>

    <body>
        <%
                    try {
                        /*HALAMAN PERTAMA*/
                        if (request.getParameter("step").equals("1")) {
        %>
        <jsp:include page="header.jsp"/>
        <div class="wrapper col3">
            <div id="breadcrumb">
                <h1>Reservations</h1>
            </div>
        </div>

        <div class="wrapper col4">
            <div id="container">
                <div id="content" style="width:500px;">
                    <h1>Please fill up this form to book our facility. </h1>

                    <form action="TambahKeranjang?action=add" method="POST" name="syalala" onsubmit="return checkDateKosong();">
                        <input type="checkbox" onclick="changeRoom();" name="roomcheckbox" value="1" /><label>Book Room</label>
                        <input type="checkbox" onclick="changeHall();" name="hallcheckbox" value="1" /><label>Book Hall</label>
                        <ul>
                            <li>
                                <h2>ROOM</h2>
                                <div id="errormessage">

                                    <%
                                                                String error = request.getParameter("error");
                                                                if (error != null) {
                                                                    out.write("WARNING : Check in date must be BEFORE check out date");
                                                                }
                                    %></div>
                                <label>Room type </label> <select name="roomtype" disabled="true">
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
                                </select> </li>

                            <!--li> <label>Total room</label> <input type="text" name="totalroom" /></li -->
                            <li> <select name="totalroom" disabled="true">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select> </li>
                            <li><label>Check-in date</label> <input disabled="true" name="roomcheckindate" type="text" class="datepicker" class="date-pick" /></li>
                            <li><label>Check-out date</label> <input  disabled="true" name="roomcheckoutdate" type="text" class="datepicker" /></li>
                        </ul>

                        <h2>HALL</h2>
                        <ul>
                            <li><label>Package</label>
                                <select name="packagetype" disabled="true">
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
                            <li> <select name="totalhall" disabled="true">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select> </li>
                            <li><label>Date</label> <input name="halldate" type="text" class="datepicker" disabled="true"/></li>
                        </ul>
                        <input type="submit" value="SUBMIT" name="tombol" disabled="true">

                    </form>

                    <%
                                            } else if (request.getParameter("step").equals("2")) {
                                                /* INI HALAMAN KEDUA */
                                                ArrayList<RoomSessionInfo> cartRoom = (ArrayList<RoomSessionInfo>) session.getAttribute("roomcart");
                                                ArrayList<HallSessionInfo> cartHall = (ArrayList<HallSessionInfo>) session.getAttribute("hallcart");
                                                CartController chartController = new CartController();
                                                double totalBill = 0;
                                                if (cartRoom != null) {
                                                    Iterator<RoomSessionInfo> iRoom = cartRoom.iterator();
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

                                                                                while (iRoom.hasNext()) {
                                                                                    i++;
                                                                                    RoomSessionInfo temp = iRoom.next();
                                                                                    double singleRowPrice = 0;
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
                    <%

                                                }
                                                if (cartHall != null) {
                                                    Iterator<HallSessionInfo> iHall = cartHall.iterator();
                    %>

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
                                                                            int i = 0;
                                                                            while (iHall.hasNext()) {
                                                                                i++;
                                                                                HallSessionInfo temp = iHall.next();
                                                                                double singleRowPrice = 0;
                                                                                if (temp.available) {
                                                                                    singleRowPrice = chartController.getHallPrice(temp.product_id);
                                                                                }
                                                                                totalBill += singleRowPrice;
                        %>
                        <tr>
                            <td><%=i%></td>
                            <td><%=chartController.getHallType(temp.product_id)%></td>
                            <td><%=(new SimpleDateFormat("dd/MM/yyyy")).format(temp.use_date)%></td>
                            <td><%=singleRowPrice%></td>
                            <td><%=temp.available%></td>
                        </tr>

                        <%                                }
                        %>

                    </table>
                    <%
                                                }
                    %>

                    <a href="reservation.jsp?step=1">Request more...</a>
                    <p>Your bill : Rp. <%=totalBill%></p>
                    <a href="reservation.jsp?step=3">Proceed</a>

                    <%
                                                session.setAttribute("totalprice", totalBill);
                                            } else if (request.getParameter("step").equals("3")) {
                                                /* INI HALAMAN KETIGA */
                                                double totalPrice = (Double) session.getAttribute("totalprice");
                    %>
                    <p>Please transfer Rp. <%=totalPrice%> to one of these account number : </p>
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

                    <a href="TambahKeranjang?action=proceed">Save Reservation</a>
                    <a href="#">Go to main page</a>

                    <%  } else if (request.getParameter("step").equals("4")) {
                                                /* INI HALAMAN KEEMPAT */
                    %>
                    <p> Your reservation has been saved. Please pay and confirm in 5 workdays. If you have paid, go to confirmation page below</p>
                    <a href="#">Confirm know</a>
                    <%
                    %>

                    <%                    }
                                } catch (NullPointerException ex) {
                                    response.sendRedirect("reservation.jsp?step=1");
                                    ex.printStackTrace();

                                }
                    %>
                </div>
                <div id="column">

                    <div class="subnav">
                        <h2>Promotion</h2>
                        <p>For best room rate and booking service, please contact our customer service.</p>
                    </div>

                </div>
                <div class="clear"></div>

            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
