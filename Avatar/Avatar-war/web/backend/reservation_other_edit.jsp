<%--
    Document   : TambahRoomReservation
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
    Modifier   : Wafdan, Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Customer" %>
<%@page import="AvatarEntity.CustomerJpaController" %>
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
OtherServicesJpaController osjpa = new OtherServicesJpaController();
OtherServicesReservationJpaController osrjpa = new OtherServicesReservationJpaController();
HallReservation osr = osrjpa.findOtherServicesReservation(Integer.parseInt(request.getParameter("item")));
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

            var getUnit = function() {
                var product = document.addOtherForm.productId.value;
                var ajaxpost = false;
                if (window.XMLHttpRequest) {
                    ajaxpost = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    ajaxpost = new ActiveXObject("Microsoft.XMLHTTP");
                }
                if (ajaxpost) {
                    ajaxpost.open("POST", "OtherReservationAjax");
                    ajaxpost.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    ajaxpost.onreadystatechange = function() {
                        if (ajaxpost.readyState == 4 && ajaxpost.status == 200) {
                            document.getElementById("unit").innerHTML = ajaxpost.responseText;
                        }
                    }
                    var data= "product=" + product;
                    ajaxpost.send(data);
                } else {
                    alert('Sorry, your browser does not support AJAX.\nThere might be lack of interactivity.');
                }
            }
        </script>
        <style>
            .subresv { width: 92%; padding-left: 8%; }
            td { vertical-align: top; }
        </style>
    </head>
    <body onload="getUnit();">
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
                            <form method="post" name="addOtherForm" id="addOtherForm" action="other_reservation_add">
                                <input type="hidden" name="reservationItemId" id="reservationItemId" value="<%= osr.getReservationItemId() %>" />
                                <label for="productId">Product</label>
                                <select name="productId" id="productId" onchange="getUnit();">
                                    <% for (OtherServices os : osjpa.findOtherServicesEntities()) { %>
                                    <option value="<%= os.getProductId() %>"<%= (os.getProductId().equals(osr.getProductId().getProductId()) ? " selected=\"selected\"" : "") %>><%= os.getProductType() %></option>
                                    <% } %>
                                </select><br />

                                <label for="amount">Quantity</label>
                                <input type="text" name="amount" id="amount" size="2" maxlength="3" />&nbsp;<span id="unit"></span><br />

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

