<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
    Modifier   : Wafdan, Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Customer" %>
<%@page import="AvatarEntity.CustomerJpaController" %>

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
Locale locale = Locale.getDefault();
SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat detail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
ReservationJpaController jpar = new ReservationJpaController();
List<Reservation> rList = jpar.findParentReservationEntities();
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../jquery/jquery_hoo.js"></script>
        <script type="text/javascript">
            function confirmAction()
            {return confirm("Do you really want to delete?")}
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
                        <div class="post">
                            <h1 class="title">Manage Reservation</h1>
                            <h2>Reservation List</h2>
                            <p><a href="reservation_room_add.jsp">Create New Reservation</a></p>
                            <% for (Reservation res : rList) { if (res.getParent() == null) { %><a name="res<%= res.getReservationId() %>"></a>
                            <p><b>Reservation #<%= res.getReservationId() %> [<%= res.getIsOnspot() ? "on-spot": "online" %>]</b> (<a href="reservation_delete?id=<%= res.getReservationId() %>">delete</a>)</p>
                            Customer: <%= res.getUsername().getName() %> (<%= res.getUsername().getUsername() %>)<br />
                            Payment status: <%= (res.getPayment() == null ? "not yet" + (res.getReservationPaymentLimit() == null ? "" : ", due " + dateOnly.format(res.getReservationPaymentLimit())) : "paid at " + dateOnly.format(res.getPayment().getPaymentDate())) %><br />
                            Reservation Items: (<a href="reservation_hall_add.jsp?res=<%= res.getReservationId() %>">add</a>)<br />
                            <table border = 1 cellpadding = "3" cellspacing = "0">
                                <tr>
                                    <th>Timestamp</th>
                                    <th>Product</th>
                                    <th>Subtotal Price</th>
                                </tr>
                                <% for (ReservationItem item : res.getReservationItemCollection()) { %>
                                <tr>
                                    <td><%= detail.format(item.getReservationTime()) %></td>
                                    <td><%= item.getDescription() %><% if (item.getDetails() != null) { %><br /><%= item.getDetails() %><% } %></td>
                                    <td align="right"><%= currencyFormat.format(item.getPrice()) %></td>
                                    <td><a href="reservation_<%= item.getDiscriminator() %>_edit.jsp?item=<%= item.getReservationItemId() %>">edit</a> | <a href="reservation_item_delete?item=<%= item.getReservationItemId() %>">delete</a></td>
                                </tr>
                                <% } %>
                                <tr>
                                    <td colspan="2" align="right">Total = </td>
                                    <td align="right"><%= currencyFormat.format(res.getTotalPrice()) %></td>
                                </tr>
                            </table>
                            <p><u>Related Reservations:</u> (<a href="reservation_hall_add.jsp?dep=<%= res.getReservationId() %>">add</a>)</p>
                            <div class="subresv">
                            <% for (Reservation resChild : res.getReservationCollection()) { %><a name="res<%= res.getReservationId() %>"></a>
                            <p><b>^ Reservation #<%= resChild.getReservationId() %> [<%= resChild.getIsOnspot() ? "on-spot": "online" %>]</b> (<a href="reservation_delete?id=<%= resChild.getReservationId() %>">delete</a>)</p>
                            Payment status: <%= (resChild.getPayment() == null ? "not yet" + (resChild.getReservationPaymentLimit() == null ? "" : ", due " + dateOnly.format(resChild.getReservationPaymentLimit())) : "paid at " + dateOnly.format(resChild.getPayment().getPaymentDate())) %><br />
                            Reservation Items: (<a href="reservation_hall_add.jsp?res=<%= resChild.getReservationId() %>">add</a>)<br />
                            <table border = 1 cellpadding = "3" cellspacing = "0">
                                <tr>
                                    <th>Timestamp</th>
                                    <th>Product</th>
                                    <th>Subtotal Price</th>
                                </tr>
                                <% for (ReservationItem item : resChild.getReservationItemCollection()) { %>
                                <tr>
                                    <td><%= detail.format(item.getReservationTime()) %></td>
                                    <td><%= item.getDescription() %><% if (item.getDetails() != null) { %><br /><%= item.getDetails() %><% } %></td>
                                    <td align="right"><%= currencyFormat.format(item.getPrice()) %></td>
                                    <td><a href="reservation_<%= item.getDiscriminator() %>_edit.jsp?item=<%= item.getReservationItemId() %>">edit</a> | <a href="reservation_item_delete?item=<%= item.getReservationItemId() %>">delete</a></td>
                                </tr>
                                <% } %>
                                <tr>
                                    <td colspan="2" align="right">Total = </td>
                                    <td align="right"><%= currencyFormat.format(resChild.getTotalPrice()) %></td>
                                </tr>
                            </table>
                            <% } %>
                            </div>
                            <hr />
                            <% } %>
                            <% } %>
                        </div>
                    </div>
                    <!-- end content -->
                    <jsp:include page="resv_sidebar.jsp" />
                    <div style="clear: both;">&nbsp;</div>
                </div>
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
    </body>
</html>
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>
