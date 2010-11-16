
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@page import="Layanan.*" %>
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
<%@page import="java.util.Locale"%>
<%@page import="Pemesanan.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Locale locale = Locale.getDefault();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
    <head profile="http://gmpg.org/xfn/11">
        <%
                    ProfileJpaController pjc = new ProfileJpaController();
                    Profile p = pjc.findProfile(Boolean.TRUE);
        %>
        <title><%=p.getHotelName()%> - Reservation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="imagetoolbar" content="no" />
        <link rel="stylesheet" href="styles/layout.css" type="text/css" />
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jqueryui.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/jquerystyle.css" />
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

            var roomSuccessStatus=-2;
            /* -2 : Disabled
             * -1 : Check out date nya harus setelah check in date
             * 0 : tidak ada room yang tersedia
             * 1 : selain yang di atas
             * */
            
            function getTotalRoomAvailable() {
                var ajaxpost = new XMLHttpRequest();
                var tanggalMulai=document.syalala.roomcheckindate.value;
                var tanggalSelesai=document.syalala.roomcheckoutdate.value;
                var productId=document.syalala.roomtype.value;
                var selectObj=document.syalala.totalroom;
                if(tanggalMulai.length==0 || tanggalSelesai==0){
                    //alert("Please fill up Check-in Date in Check-out Date first");
                    return;
                }
                if (ajaxpost) {
                    //var obj = document.getElementById('friendpanel');

                    ajaxpost.open("POST", "ReservationAjax");
                    ajaxpost.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    var selectObj=document.syalala.totalroom;
                    
                    selectObj.disabled=true;
                    ajaxpost.onreadystatechange = function() {
                        if (ajaxpost.readyState == 4 && ajaxpost.status == 200) {
                            //alert("Debug Data received :"+ajaxpost.responseText);
                            var roomAvailable=parseInt(ajaxpost.responseText);
                            var i;
                            document.syalala.tombol.disabled=true;
                            
                            if(roomAvailable==0){
                                //document.getElementById('errormessage').innerHTML="There no vacant room for your dates";
                                alert("There no vacant room for your dates");
                                roomSuccessStatus=0;
                                selectObj.disabled=true;
                                document.syalala.tombol.disabled=true;
                            }else if(roomAvailable==-1){
                                roomSuccessStatus=-1;
                                alert("Check-in date must be before check-out date");
                                selectObj.disabled=true;
                                document.syalala.tombol.disabled=true;
                            }else if(roomAvailable==-2){
                                roomSuccessStatus=-2;
                                alert("Date format must be in MM/dd/yyyy");
                                document.syalala.tombol.disabled=true;
                                selectObj.disabled=true;
                            }
                            else{
                                selectObj.length=0;
                                selectObj.disabled=false;
                                roomSuccessStatus=1;
                                for(i=1;i<=roomAvailable;i++){
                                    var newOption1 = document.createElement('option');
                                    newOption1.text = i+'';
                                    newOption1.value = i;
                                    try{
                                        selectObj.add(newOption1,null);
                                    }catch(ex){
                                        selectObj.add(newOption1);
                                    }
                                }
                                document.syalala.tombol.disabled=false;
                            }
                        }
                    }
                    var data;
                    data = "checkindate=" + tanggalMulai+"&checkoutdate="+tanggalSelesai+"&productid="+productId;
                    //alert("Debug data : "+data);
                    ajaxpost.send(data);
                }
            }
        </script>
    </head>
    <%
                String step = request.getParameter("step");
                String id = null;
                if (request.getParameter("id") != null) {
                    id = request.getParameter("id");
                }
    %>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="wrapper col3">
            <div id="breadcrumb">
                <h1>Room Reservations</h1>
            </div>
        </div>
        <%
            try {
                /*HALAMAN PERTAMA*/
                if (request.getParameter("step").equals("1")) {
        %>


        <div class="wrapper col4">
            <div id="container">
                <div id="content" style="width:500px;">
                    <jsp:include page="showcart.jsp" />
                    <h1>Please fill up this form to book our facility. </h1>

                    <form action="TambahKeranjang?action=add" method="POST" name="syalala" onsubmit="return checkDateKosong();">
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
                                <label>Room type</label> <select name="roomtype" onchange="getTotalRoomAvailable();">
                                        <%
                                                                    /*INI BUAT MASUKKIN TIPE ROOMTYPENYA DARI DATABASE*/
                                                                    List<Accomodation> listAccomodation = (new AccomodationJpaController()).findAccomodationEntities();
                                                                    Iterator<Accomodation> i = listAccomodation.iterator();
                                                                    while (i.hasNext()) {
                                                                        Accomodation temp;
                                                                        temp = i.next();
                                        %>
                                        <option value="<%=temp.getProductId()%>" <% try {
                                                                                                    if (id.equals(temp.getProductId())) {
                                                                                                        out.write("selected='true'");
                                                                                                    }
                                                                                                } catch (NullPointerException e) {
                                                                                                }%> ><%=temp.getProductType()%></option>
                                    <%
                                                                }
                                    %>
                                </select> </li>

                            <!--li> <label>Total room</label> <input type="text" name="totalroom" /></li -->

                            <li><label>Check-in date</label> <input onchange="getTotalRoomAvailable();" name="roomcheckindate" type="text" class="datepicker" /></li>
                            <li><label>Check-out date</label> <input onchange="getTotalRoomAvailable();" name="roomcheckoutdate" type="text" class="datepicker" /></li>
                            <li>
                                <label>Room needed</label> <select name="totalroom" >
                                    <option>Please fill dates first</option>
                                </select>
                            </li>
                        </ul>
                        <input type="submit" value="SUBMIT" name="tombol" disabled="true">
                    </form>
                </div>
                <%
                    } else if (request.getParameter("step").equals("3")) {
                        /* INI HALAMAN KETIGA */
                        double totalPrice = (Double) session.getAttribute("totalprice");
                %>
                <div class="wrapper col4">
                <div id="container">
                    <div id="content" style="width:500px;">
                        <jsp:include page="showcart.jsp" />
                            <p>Your reservation has been saved on our system.</p>
                            <p>Please transfer Rp. <%=totalPrice%> to one of these account number : </p>
                            <ol>
                                <li>
                                    Bank : <% out.write(p.getBankName1()); %>
                                    Acc. Number : <% out.write(p.getAccountNumber1()); %>
                                    Acc. Name : <% out.write(p.getAccountName1()); %>
                                </li>
                                <% if (p.getAccountName2() != null) { %>
                                <li>
                                    Bank : <% out.write(p.getBankName2()); %>
                                    Acc. Number : <% out.write(p.getAccountNumber2()); %>
                                    Acc. Name : <% out.write(p.getAccountName2()); %>
                                </li>
                                <% } %>
                            </ol>
                            <p>please tranfer and confirm your within 5 days. However, your
                reservation will be deleted after 5 days if you do not confirm payment
                in the specified time</p>
                <a href="reservation_status.jsp">Confirm now</a> <br>
                <a href="reservation.jsp?step=1">Request more facility</a><br>
                <a href="index.jsp">Go to main page</a>
                            
                            </div>


                <%  } 
                
                            } catch (NullPointerException ex) {
                                response.sendRedirect("reservation.jsp?step=1");
                                //ex.printStackTrace();

                            }
                %>
            <div id="column">
                <div class="subnav">
                    <h2>Reservation</h2>
                    <ul>
                        <li><a href="reservation.jsp?step=1">Room Reservation</a></li>
                        <li><a href="hallreservation.jsp?step=1">Hall Reservation</a></li>
                    </ul>
                </div>

                <div class="subnav">
                    <h2>Reservation Status</h2>
                    <p>See your reservation status <a href="reservation_status.jsp" class="link_res_stat">here</a></p>
                </div>
                
            </div>

            </div>
            <div class="clear"></div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
