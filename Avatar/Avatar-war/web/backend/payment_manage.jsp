<%--
    Document   : KelolaPembayaran
    Created on : 29 Sep 10, 19:47:02
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="java.util.List, java.util.Calendar, java.util.Iterator, java.util.Locale, AvatarEntity.Payment, AvatarEntity.Reservation, java.text.SimpleDateFormat, java.text.NumberFormat" %>

<%-- start object initialization --%>
<%
List<Reservation> lres = (List<Reservation>) request.getAttribute("returnList");
int totalpage = (Integer) request.getAttribute("totalpage");
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
SimpleDateFormat onlyDate = new SimpleDateFormat("yyyy-MM-dd");
Locale locale = Locale.getDefault();
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
Calendar today = Calendar.getInstance();
today.set(Calendar.HOUR_OF_DAY, 0); today.set(Calendar.MINUTE, 0);
today.set(Calendar.SECOND, 0); today.set(Calendar.MILLISECOND, 0);
Calendar curr = Calendar.getInstance(); curr.setTimeInMillis(0);
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
    <style type="text/css">
        .onDue { font-weight: bold; }
        .overDue { font-weight: bold; color: #FF0000 }
    </style>
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
        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
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
                                    <th>Reservation Status</th>
                                    <th>Customer</th>
                                    <th>Total Bill</th>
                                    <th>Payment Status</th>
                                </tr>
                                <%
                                Payment pay;
                                for (Reservation item : lres) {
                                    String verifyAction;
                                    if (item.getPayment() == null) {
                                        verifyAction = "Remind";
                                    } else {
                                        verifyAction = "Verify";
                                    }
                                    pay = item.getPayment();
                                    curr.setTime(item.getReservationPaymentLimit());
                                %>
                                <tr<%= (pay != null ? "" : (curr.before(today) ? " class=\"overDue\"" : (curr.equals(today) ? " class=\"onDue\"" : ""))) %>>
                                    <td>
                                        <%= formatter.format(item.getReservationTime()) %>
                                        (<%= item.getIsOnspot() ? "on-spot" : "online" %>)
                                    </td>
                                    <td><%= item.getUsername().getUsername() %></td>
                                    <td><%= currencyFormat.format(item.getTotalPrice()) %></td>
                                    <td style="white-space:nowrap"><% if (pay == null) { %>
                                        not yet<% if (item.getReservationPaymentLimit() != null) {
                                            %>, due <%= onlyDate.format(item.getReservationPaymentLimit()) %>
                                        <% } %>
                                        <% } else { %>
                                        confirmed <%= formatter.format(pay.getConfirmTime()) %><br />
                                        amount <%= currencyFormat.format(pay.getAmount()) %><br />
                                        paid at <%= onlyDate.format(pay.getPaymentDate()) %><br />
                                        <% if (pay.getUsername() == null) { %>
                                        <em>not verified</em>
                                        <% } else { %>
                                        verified by <%= pay.getUsername().getUsername() %>
                                        <% } %>
                                        <% } %>
                                    </td>
                                    <td><% if (item.getPayment() == null) { %>
                                        <form method="post" name="confirmForm<%= item.getReservationId() %>" id="confirmForm<%= item.getReservationId() %>" action="payment_form.jsp">
                                            <input type="hidden" name="reservationId" id="reservationId" value="<%= item.getReservationId() %>" />
                                            <input type="submit" name="confirm" id="confirm" value="Direct Confirm" />
                                        </form>
                                        <% } %>
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
                            <center>Page: <% for (int i = 1; i <= totalpage; i++) { %>
                                <a href="payment_manage?pg=<%=i%>"><%=i%></a>&nbsp;<% } %></center>
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
                                
                                <ul>
                                    <li>PAYMENT
                                        <ul id="fmenu">
                                            <li id="fmenu-item1"><a href="payment_manage">All</a></li>
                                            <li id="fmenu-item2"><a href="payment_manage?mode=unconf">Unconfirmed</a></li>
                                            <li id="fmenu-item3"><a href="payment_manage?mode=unver">Unverified Confirmed</a></li>
                                            <li id="fmenu-item4"><a href="payment_manage?mode=ver">Verified</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- end sidebar -->
                <div style="clear:both;">&nbsp;</div>
                </div>
            </div>
        </div>
        <!-- end page -->
    </body>
</html>