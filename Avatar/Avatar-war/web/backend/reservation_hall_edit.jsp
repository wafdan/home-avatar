<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
    Modifier   : Wafdan, Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
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
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat hour = new SimpleDateFormat("HH");
SimpleDateFormat min = new SimpleDateFormat("mm");
HallReservationJpaController hrjpa = new HallReservationJpaController();
HallReservation hr = hrjpa.findHallReservation(Integer.parseInt(request.getParameter("item")));
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

            var getDefaults = function() {
                var hall = document.editHallForm.productId.value;
                var layoutSelectObjValue = document.editHallForm.layout.value;
                var useDate = document.editHallForm.useDate.value;
                var attendees = document.editHallForm.attendees.value;
                var item = document.editHallForm.reservationItemId.value;
                if (layoutSelectObjValue != "" && useDate != "" && attendees != "") {
                    var ajaxpost = false;
                    if (window.XMLHttpRequest) {
                        ajaxpost = new XMLHttpRequest();
                    } else if (window.ActiveXObject) {
                        ajaxpost = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    if (ajaxpost) {
                        ajaxpost.open("POST", "HallReservationAjax");
                        ajaxpost.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                        ajaxpost.onreadystatechange = function() {
                            if (ajaxpost.readyState == 4 && ajaxpost.status == 200) {
                                var xmlDocument = ajaxpost.responseXML;
                                process(xmlDocument);
                            }
                        }
                        var data= "hall=" + hall + "&layout=" + layoutSelectObjValue+"&date="+useDate+"&att="+attendees+"&item="+item;
                        alert(data);
                        ajaxpost.send(data);
                    } else {
                        alert('Sorry, your browser does not support AJAX.\nThere might be lack of interactivity.');
                    }
                }
            }
            
            function process(xmlDocument) {
                var venue, hall, startHour, startMin, endHour, endMin, i;
                var selVenue = document.getElementById("venue");
                hall = xmlDocument.getElementsByTagName("hall");
                venue = xmlDocument.getElementsByTagName("venue");
                startHour = hall[0].firstChild.data; startMin = hall[1].firstChild.data;
                endHour = hall[2].firstChild.data; endMin = hall[3].firstChild.data;
                if (venue.length > 0) {
                    for (i = 0; i < venue.length; i++) {
                        selVenue.options[i] = new Option(venue[i].lastChild.firstChild.data, venue[i].firstChild.firstChild.data);
                    }
                } else {
                    alert('No available venue for the event.');
                }
                document.editHallForm.beginTimeHour.value = startHour;
                document.editHallForm.beginTimeMin.value = startMin;
                document.editHallForm.endTimeHour.value = endHour;
                document.editHallForm.endTimeMin.value = endMin;
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
                        <h1 class="title">Edit Reservation</h1>
                        <div class="post">
                            <form method="post" name="editHallForm" id="editHallForm" action="hall_reservation_edit">
                                <input type="hidden" name="reservationItemId" id="reservationItemId" value="<%= hr.getReservationItemId() %>" />
                                <label for="productId">Hall Type</label>
                                <div id="productId"><%= hr.getProductId().getProductType() %></div>
                                <%--<select name="productId" id="productId">
                                    <% for (Hall hall : hjpa.findHallEntities()) { %>
                                    <option value="<%= hall.getProductId() %>"<%= (hall.getProductId().equals(hr.getProductId().getProductId()) ? " selected=\"selected\"" : "") %>><%= hall.getProductType() %></option>
                                    <% } %>
                                </select><br />--%>

                                <label for="attendees">Attendees</label>
                                <input type="text" name="attendees" id="attendees" size="3" maxlength="3" value="<%= hr.getAttendees() %>" /><br />
                                
                                <%--<label for="layout">Layout</label>
                                <select name="layout" id="layout" onchange="getDefaults();">
                                    <option value="" selected="selected">--select layout--</option>
                                    <% for (Layout lay : ljpa.findLayoutEntities()) {%>
                                    <option value="<%= lay.getLayoutNo() %>"><%= lay.getLayoutName() %></option>
                                    <% } %>
                                </select><br />

                                <label for="useDate">Date</label>
                                <input type="text" name="useDate" id="useDate" size="10" class="datepicker" maxlength="10" onchange="getDefaults();" /><br />
                                
                                <label for="venue">Venue</label>
                                <select name="venue" id="venue">
                                </select><br />--%>

                                <label for="useDate">Date</label><div id="useDate"><%= sdf.format(hr.getUseDate()) %></div>
                                <label for="venue">Venue</label><div id="venue"><%= hr.getVenueNo().getVenueName() %></div><br/>

                                <label for="beginTimeHour">Begin</label>
                                <input type="text" name="beginTimeHour" id="beginTimeHour" size="2" maxlength="2" value="<%= hour.format(hr.getBeginTime()) %>" />:
                                <input type="text" name="beginTimeMin" id="beginTimeMin" size="2" maxlength="2" value="<%= min.format(hr.getBeginTime()) %>" /><br />

                                <label for="endTimeHour">End</label>
                                <input type="text" name="endTimeHour" id="endTimeHour" size="2" maxlength="2" value="<%= hour.format(hr.getEndTime()) %>" />:
                                <input type="text" name="endTimeMin" id="endTimeMin" size="2" maxlength="2" value="<%= min.format(hr.getEndTime()) %>" /><br />

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

