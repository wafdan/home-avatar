<%--
    Document   : KelolaPembayaran
    Created on : 29 Sep 10, 19:47:02
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.RoomReservationJpaController" %>
<%@ page import="AvatarEntity.RoomReservation" %>
<%@ page import="AvatarEntity.HallReservationJpaController" %>
<%@ page import="AvatarEntity.HallReservation" %>
<%@ page import="AvatarEntity.OtherServicesReservationJpaController" %>
<%@ page import="AvatarEntity.OtherServicesReservation" %>
<%@ page import="AvatarEntity.CustomerJpaController" %>
<%@ page import="AvatarEntity.Customer" %>
<%@ page import="AvatarEntity.ReservationJpaController" %>
<%@ page import="AvatarEntity.Reservation" %>
<%@ page import="AvatarEntity.ReservationItem" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
Integer id = Integer.parseInt(request.getParameter("reservationId"));
ReservationJpaController resjpa = new ReservationJpaController();
Reservation res = resjpa.findReservation(id);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat detail = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
Locale locale = Locale.getDefault();
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="../styles/jquerystyle.css" />
        <script type="text/javascript" src="../jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="../jquery/jqueryui.js"></script>
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

        </script>
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
                        <h1 class="title">Form Konfirmasi Pembayaran</h1>
                        <div class="post">
                            <form method="post" name="formConfirm" id="formConfirm" action="payment_manage">
                            <table border="0">
                                <tr>
                                    <td>Reservation ID</td>
                                    <td>:</td>
                                    <td>#<%= res.getReservationId() %>
                                        <input type="hidden" name="reservationId" id="reservationId" value="<%= res.getReservationId() %>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Reservation Status</td>
                                    <td>:</td>
                                    <td><%= detail.format(res.getReservationTime()) + " (" + (res.getIsOnspot() ? "on-spot" : "online") + ")" %></td>
                                </tr>
                                <tr valign="top">
                                    <td>Reservation Items</td>
                                    <td>:</td>
                                    <td><table border="0" cellpadding="0" cellspacing="1" width="100%">
                                            <% for (ReservationItem item : res.getReservationItemCollection()) { %>
                                            <tr>
                                                <td><%= item instanceof RoomReservation ?
                                                    ((RoomReservation) item).getRoomNo().getProductId().getProductType() + " in " + sdf.format(((RoomReservation) item).getEntryDate()) :
                                                    (item instanceof HallReservation ? ((HallReservation) item).getProductId().getProductType() :
                                                    item instanceof OtherServicesReservation ? ((OtherServicesReservation) item).getProductId().getProductType() : "unknown")
                                                %></td>
                                                <td align="right"><%= currencyFormat.format(item.getPrice()) %></td>
                                            </tr>
                                            <% } %>
                                            <tr>
                                                <td align="right"><b>Total = </b></td>
                                                <td align="right"><b><%= currencyFormat.format(res.getTotalPrice()) %></b></td>
                                            </tr>
                                        </table></td>
                                </tr>
                                <tr>
                                    <td>Verifier</td>
                                    <td>:</td>
                                    <td><%= session.getAttribute("username") %>
                                        <input type="hidden" name="username" id="username" value="<%= session.getAttribute("username")%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Payment Date</td>
                                    <td>:</td>
                                    <td><input type="text" name="paymentDate" id="paymentDate" class="datepicker" size="10" maxlength="10" value="<%= sdf.format(new Date()) %>" /></td>
                                </tr>
                                <tr>
                                    <td>Payment Method</td>
                                    <td>:</td>
                                    <td><select name="paymentMethod" id="paymentMethod">
                                            <option value="cash">cash</option>
                                            <option value="transfer">bank transfer</option>
                                            <option value="credit card">credit card</option>
                                            <option value="cheque">cheque</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Payment Bank</td>
                                    <td>:</td>
                                    <td><input type="text" name="paymentBank" id="paymentBank" size="15" maxlength="20" /></td>
                                </tr>
                                <tr>
                                    <td>Account Number</td>
                                    <td>:</td>
                                    <td><input type="text" name="accountNumber" id="accountNumber" size="20" maxlength="40" /></td>
                                </tr>
                                <tr>
                                    <td>Amount</td>
                                    <td>:</td>
                                    <td><input type="text" name="amount" id="amount" /></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><input type="submit" name="confirm" id="confirm" value="Confirm"/></td>
                                </tr>
                            </table>
                            </form>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="resv_sidebar.jsp" flush="true"/>
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

