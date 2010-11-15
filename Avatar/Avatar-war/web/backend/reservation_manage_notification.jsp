<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
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
<%@page import="AvatarEntity.OtherServicesReservation" %>
<%@page import="AvatarEntity.OtherServicesReservationJpaController" %>
<%@page import="AvatarEntity.Reservation" %>
<%@page import="AvatarEntity.ReservationJpaController" %>
<%@page import="AvatarEntity.ReservationItem" %>
<%@page import="AvatarEntity.ReservationItemJpaController" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="KelolaReservasi.*" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/backend_facilities.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function confirmSendEmail()
            {return confirm("Do you want to send reminder email?")}
            function confirmDelete()
            {return confirm("Do you really want to delete?")}
        </script>
    </head>
    <%
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
    %>
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
                        <h1 class="title">Due Payment Reservation List</h1>
                        <div class="post">
                            <div class="fac1">
                            <%
                            MengelolaReservasiController ctrl = new MengelolaReservasiController();
                            List<DueReservation> rList = ctrl.getDuePaymentReservation();
                            if (rList.isEmpty() == true) {
                                    out.println("No Reservation Item on Due Payment");
                                } else {
                            %>
                            <%--<table width="603" border="1" style="table-layout:fixed">--%>
                            <table align = "center" border = 1 width = "100%" cellpadding = "3" cellspacing = "0">
                                <tr>
                                    <th bgcolor="#262626" width="5%">No.</th>
                                    <th bgcolor="#262626" width="15%">Reservation Id</th>
                                    <th bgcolor="#262626" width="35%">Username</th>
                                    <th bgcolor="#262626" width="35%">Due Date</th>
                                    <th colspan="2" bgcolor="#262626" width="10%">Action</th>
                                </tr>
                                <%
                                int index=0;
                                Iterator<DueReservation> i = rList.iterator();
                                while (i.hasNext()) {
                                    DueReservation temp = i.next();
                                %>
                                <tr>
                                    <td style="vertical-align: 0%"><% index++;out.write(Integer.toString(index));%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getReservationId().toString());%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getUsername().getName());%></td>
                                    <td style="vertical-align: 0%"><% out.write(formatter.format(temp.getDueDate()));%></td>
                                    <td style="vertical-align: 0%;width:20px;" align="center"><a onclick="return confirmSendEmail()" href="#">Send email</a></td>
                                    <td style="vertical-align: 0%;width:20px;" align="center"><a onclick="return confirmDelete()" href=<%out.write("'HapusResv?id="+temp.getReservationId()+"'");%>>Delete</a></td>
                                </tr>
                                <%}
                                }
                                %>
                            </table>
                            </div>
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

