
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

            var roomSuccessStatus=-2;
            /* -2 : Disabled
             * -1 : Check out date nya harus setelah check in date
             * 0 : tidak ada room yang tersedia
             * 1 : selain yang di atas
             * */

           /* var onloadFunction=function(){
                //Hilangkan checkbox nya
                document.syalala.roomcheckbox.checked=false;
                document.syalala.hallcheckbox.checked=false;
                
                //form buat room di disabled
                document.syalala.roomcheckindate.disabled=true;
                document.syalala.roomcheckoutdate.disabled=true;
                document.syalala.roomtype.disabled=true;
                document.syalala.totalroom.disabled=true;

                //form buat hall di disabled
                document.syalala.halldate.disabled=true;
                document.syalala.packagetype.disabled=true;
                document.syalala.totalhall.disabled=true;

            }

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

                if(roomSuccessStatus==-1){
                    alert("Check-in date must be before check-out date");
                }

                if(roomSuccessStatus==0){
                    alert("There no vacant room for your dates");
                }

                if(!retval)
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
                    document.syalala.totalroom.length=0;
                    var newOption1 = document.createElement('option');
                    newOption1.text = "Please fill dates first";
                    var selectObj=document.syalala.totalroom;
                    try{
                        selectObj.add(newOption1,null);
                    }catch(ex){
                        selectObj.add(newOption1);
                    }

                    roomSuccessStatus=-2;
                }else{
                    document.syalala.roomcheckindate.disabled=false;
                    document.syalala.roomcheckoutdate.disabled=false;
                    document.syalala.roomtype.disabled=false;
                    //document.syalala.totalroom.disabled=false;
                    roomSuccessStatus=1;
                    getTotalRoomAvailable();
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

            }*/

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
                            
                            if(roomAvailable==0){
                                //document.getElementById('errormessage').innerHTML="There no vacant room for your dates";
                                alert("There no vacant room for your dates");
                                roomSuccessStatus=0;
                            }else if(roomAvailable==-1){
                                roomSuccessStatus=-1;
                                alert("Check-in date must be before check-out date");
                            }else if(roomAvailable==-2){
                                roomSuccessStatus=-2;
                                alert("Date format must be in MM/dd/yyyy");
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
                String type = null;
                if (request.getParameter("id") != null) {
                    id = request.getParameter("id");
                }
                if (request.getParameter("type") != null) {
                    type = request.getParameter("type");
                }
    %>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="wrapper col3">
            <div id="breadcrumb">
                <h1>Reservations</h1>
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
                <div id="column">

                    <div class="subnav">
                        <h2>Promotion</h2>
                        <p>For best room rate and booking service, please contact our customer service.</p>
                    </div>

                </div>
                <%
                                        } else if (request.getParameter("step").equals("3")) {
                                            /* INI HALAMAN KETIGA */
                                            double totalPrice = (Double) session.getAttribute("totalprice");
                %>
                <p>Your reservation has been saved on our system.
                    Please transfer Rp. <%=totalPrice%> to one of these account number : </p>
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
                <p>please tranfer and confirm your within 5 days. However, your
                reservation will be deleted after 5 days if you do not confirm payment
                in the specified time</p>
                <a href="reservation_status.jsp">Confirm now</a>
                <a href="reservation.jsp?step=1">Request more facility</a><br>
                <a href="index.jsp">Go to main page</a>

                <%  } 
                
                            } catch (NullPointerException ex) {
                                response.sendRedirect("reservation.jsp?step=1");
                                //ex.printStackTrace();

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
        <jsp:include page="footer.jsp"/>
    </body>
</html>
