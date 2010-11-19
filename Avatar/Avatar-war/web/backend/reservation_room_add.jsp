<%--
    Document   : TambahRoomReservation
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
    Modifier   : Wafdan, Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Customer" %>
<%@page import="AvatarEntity.CustomerJpaController" %>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.RoomReservation" %>
<%@page import="AvatarEntity.RoomReservationJpaController" %>
<%@page import="AvatarEntity.HallReservation" %>
<%@page import="AvatarEntity.HallReservationJpaController" %>
<%@page import="AvatarEntity.Layout" %>
<%@page import="AvatarEntity.LayoutJpaController" %>
<%@page import="AvatarEntity.OtherServicesReservation" %>
<%@page import="AvatarEntity.OtherServicesReservationJpaController" %>
<%@page import="AvatarEntity.Reservation" %>
<%@page import="AvatarEntity.ReservationJpaController" %>
<%@page import="AvatarEntity.ReservationItem" %>
<%@page import="AvatarEntity.ReservationItemJpaController" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 1){
%>
<%
String res = request.getParameter("res");
String dep = request.getParameter("dep");
AccomodationJpaController ajpa = new AccomodationJpaController();
CustomerJpaController cjpa = new CustomerJpaController();
String param = "";
if (request.getParameter("res") != null)
    param += "?res=" + res;
else if (request.getParameter("dep") != null)
    param += "?dep=" + dep;
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../jquery/jquery_hoo.js"></script>
        <script type="text/javascript" src="../jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="../jquery/jqueryui.js"></script>
        <link rel="stylesheet" type="text/css" href="../styles/jquerystyle.css" />
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

            var getVacantRooms = function() {
                var room = document.addRoomForm.productId.value;
                var entryDate = document.addRoomForm.entryDate.value;
                var exitDate = document.addRoomForm.exitDate.value;
                if (entryDate != "" && exitDate != "") {
                    var ajaxpost = false;
                    if (window.XMLHttpRequest) {
                        ajaxpost = new XMLHttpRequest();
                    } else if (window.ActiveXObject) {
                        ajaxpost = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    if (ajaxpost) {
                        ajaxpost.open("POST", "RoomReservationAjax");
                        ajaxpost.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                        ajaxpost.onreadystatechange = function() {
                            if (ajaxpost.readyState == 4 && ajaxpost.status == 200) {
                                var xmlDocument = ajaxpost.responseXML;
                                process(xmlDocument);
                            }
                        }
                        var data= "room=" + room + "&entryDate=" + entryDate +"&exitDate="+ exitDate;
                        ajaxpost.send(data);
                    } else {
                        alert('Sorry, your browser does not support AJAX.\nThere might be lack of interactivity.');
                    }
                }
            }
            
            function process(xmlDocument) {
                var room, i;
                var selRoomNo = document.getElementById("roomNo");
                room = xmlDocument.getElementsByTagName("room");
                if (room.length > 0) {
                    selRoomNo.options.length = 0;
                    for (i = 0; i < room.length; i++) {
                        selRoomNo.options[i] = new Option(room[i].lastChild.firstChild.data, room[i].firstChild.firstChild.data);
                    }
                } else {
                    alert('No available room for selected type during these dates.');
                }
            }
        </script>
        <style>
            .subresv { width: 92%; padding-left: 8%; }
            td { vertical-align: top; }
        </style>
    </head>
    <body>
        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Add Reservation</h1>
                        <ul id="fmenu">
                            <li id="fmenu-item1"><em>Rooms</em></li>
                            <li id="fmenu-item2"><a href="reservation_hall_add.jsp<%= param %>">Meetings & Events</a></li>
                            <li id="fmenu-item3"><a href="reservation_other_add.jsp<%= param %>">Other Services</a></li>
                        </ul>
                        <div class="post">
                            <form method="post" name="addRoomForm" id="addRoomForm" action="room_reservation_add">
                                <% if (res != null) { %><input type="hidden" name="reservationId" id="reservationId" value="<%= res %>" />
                                <% } else if (dep != null) { %><input type="hidden" name="parent" id="parent" value="<%= dep %>" />
                                <% } else { %><label for="username">User</label>
                                <select name="username" id="username">
                                    <% for (Customer cust : cjpa.findCustomerEntities()) { %><option value="<%= cust.getUsername() %>"><%= cust.getName() %></option><% } %>
                                </select><br /><% } %>
                                <label for="productId">Room Type</label>
                                <select name="productId" id="productId" onchange="getVacantRooms();">
                                    <% for (Accomodation acc : ajpa.findAccomodationEntities()) { %>
                                    <option value="<%= acc.getProductId() %>"><%= acc.getProductType() %></option>
                                    <% } %>
                                </select><br />

                                <label for="entryDate">Entry Date</label>
                                <input type="text" name="entryDate" id="entryDate" size="10" class="datepicker" maxlength="10" onchange="getVacantRooms();" /><br />

                                <label for="exitDate">Exit Date</label>
                                <input type="text" name="exitDate" id="exitDate" size="10" class="datepicker" maxlength="10" onchange="getVacantRooms();" /><br />
                                
                                <label for="roomNo">Room No</label>
                                <select name="roomNo" id="roomNo">
                                </select><br />

                                <input type="submit" name="add" id="add" value="Add" />
                            </form>
                        </div>
                        <!-- end content -->    
                        <div style="clear: both;">&nbsp;</div>
                    </div>
                    <jsp:include page="resv_sidebar.jsp" />
                    <!-- end page -->
                </div>
            </div>
            <!-- start footer -->
            <div id="footer">
                <div id="footer-wrap">
                    <p id="legal">(c)2010 AVATAR. Design by <a href="http://www.freecsstemplates.org/">Hakuna Matata</a>.</p>
                </div>
            </div>
        <!-- end footer -->
        </div>
    </body>
</html>
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>
