<%--
    Document   : EditRoomReservation
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
    Modifier   : Wafdan, Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<%@page import="java.util.Date" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 1){
%>
<%
SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
AccomodationJpaController ajpa = new AccomodationJpaController();
RoomReservationJpaController rrjpa = new RoomReservationJpaController();
RoomReservation rr = rrjpa.findRoomReservation(Integer.parseInt(request.getParameter("item")));
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

            var getNow = function(IdToPlace) {
                document.getElementById(IdToPlace).value = "<%= datetime.format(new Date()) %>";
            }

            var getVacantRooms = function() {
                var room = document.editRoomForm.productId.value;
                var entryDate = document.editRoomForm.entryDate.value;
                var exitDate = document.editRoomForm.exitDate.value;
                var item = document.editRoomForm.reservationItemId.value;
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
                        var data= "room=" + room + "&entryDate=" + entryDate +"&exitDate="+ exitDate + "&item=" + item;
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
                        if (room[i].firstChild.firstChild.data == "<%= rr.getRoomNo().getRoomNo() %>") {
                            selRoomNo.options[i] = new Option(room[i].lastChild.firstChild.data, room[i].firstChild.firstChild.data, true, true);
                        } else {
                            selRoomNo.options[i] = new Option(room[i].lastChild.firstChild.data, room[i].firstChild.firstChild.data);
                        }
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
    <body onload="getVacantRooms();">
        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Edit Reservation</h1>
                        <div class="post">
                            <form method="post" name="editRoomForm" id="editRoomForm" action="room_reservation_edit">
                                <input type="hidden" name="reservationItemId" id="reservationItemId" value="<%= rr.getReservationItemId() %>" />
                                <label for="productId">Room Type</label>
                                <select name="productId" id="productId" onchange="getVacantRooms();">
                                    <% for (Accomodation acc : ajpa.findAccomodationEntities()) { %>
                                    <option value="<%= acc.getProductId() %>" <%
                                    if (rr.getRoomNo().getProductId().getProductId().equals(acc.getProductId())) {%>selected="selected"<%}
                                    %>><%= acc.getProductType() %></option>
                                    <% } %>
                                </select><br />

                                <label for="entryDate">Entry Date</label>
                                <input type="text" name="entryDate" id="entryDate" size="10" class="datepicker" maxlength="10" value="<%= dateOnly.format(rr.getEntryDate()) %>" onchange="getVacantRooms();" /><br />

                                <label for="exitDate">Exit Date</label>
                                <input type="text" name="exitDate" id="exitDate" size="10" class="datepicker" maxlength="10" value="<%= dateOnly.format(rr.getExitDate()) %>" onchange="getVacantRooms();" /><br />

                                <label for="actualEntry">Check In</label>
                                <input type="text" name="actualEntry" id="actualEntry" size="19" maxlength="19" value="<%= (rr.getActualEntry() == null ? "" : datetime.format(rr.getActualEntry())) %>" /><input type="button" name="checkInNow" id="checkInNow" value="Now" onclick="getNow('actualEntry');" /><br />

                                <label for="actualExit">Check Out</label>
                                <input type="text" name="actualExit" id="actualExit" size="19" maxlength="19" value="<%= (rr.getActualExit() == null ? "" : datetime.format(rr.getActualExit())) %>" /><input type="button" name="checkInNow" id="checkInNow" value="Now" onclick="getNow('actualExit');" /><br />

                                <label for="roomNo">Room No</label>
                                <select name="roomNo" id="roomNo">
                                </select><br />

                                <input type="submit" name="update" id="update" value="Update" />
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
