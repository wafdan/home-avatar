<%--
    Document   : KelolaPembayaran
    Created on : 29 Sep 10, 19:47:02
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="java.util.List, java.util.Iterator, java.util.Locale, AvatarEntity.Reservation, java.text.SimpleDateFormat, java.text.NumberFormat" %>

<%-- start object initialization --%>
<%
List<Reservation> lres = (List<Reservation>) request.getAttribute("returnList");
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
Locale locale = Locale.getDefault();
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<%-- end object initialization --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
    </head>
    <script language="javascript" type="text/javascript">
    <!--
        function verifyConfirm(formname, username) {
            var ans = window.confirm("Are you sure to send e-mail to " + username + "?");
            if (ans) {
                document.forms[formname].submit();
            }
        }
    //-->
    </script>
    <body>
        <%= request.getAttribute("popup") %>
        <div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>

        <!-- start header -->
        <div id="header">
            <%
            if ((session.getAttribute("name")) != null) {
            %>
            <div id="loginstatus">Anda Login sebagai : <%=session.getAttribute("name")%>
                <a href="../Logout">Logout</a>
            </div>
            <%}%>
            <div id="menu">
                <ul>
                    <li><a href="profile_manage.jsp">Profile</a></li>
                    <li class="current_page_item"><a href="#">User</a></li>
                    <li><a href="#">Facilities</a></li>
                    <li><a href="#">Statistic</a></li>
                    <li><a href="#">Post</a></li>
                    <li><a href="#">Repository</a></li>
                    <li><a href="#">Reservation</a></li>
                    <li><a href="payment_manage">Payment</a></li>
                </ul>
            </div>
        </div>
        <!-- end header -->

        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Reservation and Payment List</h1>
                        <div class="post">
                            <table width="*" border="1" style="table-layout:fixed">
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Reservation Time</th>
                                    <th>Total</th>
                                    <th>On Spot?</th>
                                    <th>Confirmed</th>
                                    <th>Verified?</th>
                                    <th>Reservation Notes</th>
                                </tr>
                                <%
                                for (Reservation item : lres) {
                                    String verifyAction;
                                    if (item.getPayment() == null) {
                                        verifyAction = "Remind";
                                    } else {
                                        verifyAction = "Verify";
                                    }
                                %>
                                <tr>
                                    <td><%= item.getReservationId() %></td>
                                    <td><%= item.getUsername().getUsername() %></td>
                                    <td><%= formatter.format(item.getReservationTime()) %></td>
                                    <td><%= currencyFormat.format(item.getTotalPrice()) %></td>
                                    <td><%= (item.getIsOnspot() ? "yes" : "no") %></td>
                                    <td>
                                        <%= (item.getPayment() == null ? "not yet" :
                                            currencyFormat.format(item.getPayment().getAmount())) %>
                                    </td>
                                    <td>
                                        <%= (item.getPayment() != null ?
                                        (item.getPayment().getUsername() != null ? "yes" : "no") : "no") %>
                                    </td>
                                    <td><%= item.getNote() %></td>
                                    <td>
                                        <% if (item.getPayment() == null || (item.getPayment() != null && item.getPayment().getUsername() == null)) { %>
                                        <form method="post" name="verifyForm<%= item.getReservationId() %>" id="verifyForm<%= item.getReservationId() %>" action="payment_manage">
                                            <input type="hidden" name="reservationId" id="reservationId" value="<%= item.getReservationId() %>" />
                                            <input type="hidden" name="verify" id="verify" value="<%= verifyAction %>" />
                                            <input type="submit" name="submitButt" id="submitButt" value="<%= verifyAction %>" onclick="verifyConfirm('verifyForm<%= item.getReservationId() %>', '<%= item.getUsername().getUsername() %>'); return false;" />
                                        </form>
                                        <% } %>
                                    </td>
                                </tr>
                                <%
                                }
                                %>
                            </table>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Payment Management</h2>
                                </div>
                                <hr />
                                <ul>
                                    <li>PAYMENT
                                        <ul>
                                            <li><a href="payment_manage">Payment Management</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                <hr />
                            </li>
                        </ul>
                    </div>
                    <!-- end sidebar -->
                </div>
            </div>
        </div>
        <!-- end page -->
    </body>
</html>