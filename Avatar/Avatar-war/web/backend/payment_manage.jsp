<%--
    Document   : KelolaPembayaran
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="AvatarEntity.Reservation" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

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
                    <li><a href="#">Payment</a></li>
                </ul>
            </div>
        </div>
        <!-- end header -->

        <!-- start object initialization -->
        <%
        List<Reservation> lres = (List<Reservation>) request.getAttribute("returnList");
        %>
        <!-- end object initialization -->

        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Daftar Reservasi dan Status Pembayaran</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr>
                                    <th bgcolor="#262626">ID</th>
                                    <th bgcolor="#262626">Username</th>
                                    <th bgcolor="#262626">Reservation Time</th>
                                    <th bgcolor="#262626">Total</th>
                                    <th bgcolor="#262626">On Spot?</th>
                                    <th bgcolor="#262626">Confirmed?</th>
                                    <th bgcolor="#262626">Verified?</th>
                                    <th bgcolor="#262626">Notes</th>
                                </tr>
                                <%
                                for (Reservation item : lres) {
                                %>
                                <tr>
                                    <td><div style="overflow:auto"><%= item.getReservationId().toString() %></div></td>
                                    <td><div style="overflow:auto"><%= item.getUsername().getUsername() %></div></td>
                                    <td><div style="overflow:auto"><%= item.getReservationTime() %></div></td>
                                    <td><div style="overflow:auto"><%= item.getTotalPrice() %></div></td>
                                    <td><div style="overflow:auto"><%= item.getIsOnspot() ? "yes" : "no" %></div></td>
                                    <td><div style="overflow:auto"><%= item.getPayment() != null ? "yes" : "no" %></div></td>
                                    <td><div style="overflow:auto"><%= item.getPayment().getUsername() != null ? "yes" : "no" %></div></td>
                                    <td><div style="overflow:auto"><%= item.getNote() %></div></td>
                                </tr>
                                <%}%>
                            </table>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Reservation Management</h2>
                                </div>
                                <hr />
                                <ul>
                                  <li><a href="reservation_add.jsp">Add Reservation</a></li>
                                  <li><a href="reservation_manage.jsp">Manage Reservation</a></li>
                                </ul>
                              <hr />
                            </li>
                        </ul>
                    </div>
                    <!-- end sidebar -->
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

