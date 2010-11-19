<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.RoomReservationJpaController" %>
<%@ page import="AvatarEntity.RoomReservation" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 1){
    RoomReservationJpaController cr = new RoomReservationJpaController();
    List<RoomReservation> lr = cr.findRoomReservationEntities();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
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
                        <h1 class="title">List of Room Reservations</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr class="headertable">
                                    <th bgcolor="#262626">Timestamp</th>
                                    <th bgcolor="#262626">Customer</th>
                                    <th bgcolor="#262626">Room</th>
                                    <th bgcolor="#262626">Entry</th>
                                    <th bgcolor="#262626">Exit</th>
                                    <th bgcolor="#262626">Resrv</th>
                                </tr>

                                <%
                                    RoomReservationJpaController jpr = new RoomReservationJpaController();
                                    List<RoomReservation> roomList = jpr.findRoomReservationEntities();
                                    Iterator<RoomReservation> ur = roomList.iterator();
                                    if (ur.hasNext()) {out.write(" has next ");} else {out.write(" no next ");}

                                    for (Iterator<RoomReservation> ir = lr.iterator(); ir.hasNext();) {
                                        RoomReservation temp = ir.next();
                                %>
                                <tr>
                                    <td><%= datetime.format(temp.getReservationTime()) %></td>
                                    <td><%= temp.getReservationId().getUsername().getName() %></td>
                                    <td><%= temp.getRoomNo().getRoomNo() %><br />(<%= temp.getRoomNo().getProductId().getProductType() %>)</td>
                                    <td style="white-space: nowrap"><%= sdf.format(temp.getEntryDate()) %><br />
                                        (<em><%= (temp.getActualEntry() != null ? sdf.format(temp.getActualEntry()) : "not yet") %></em>)</td>
                                    <td style="white-space: nowrap"><%= sdf.format(temp.getExitDate()) %><br />
                                        (<em><%= (temp.getActualExit() != null ? sdf.format(temp.getActualExit()) : "not yet") %></em>)</td>
                                    <td><a href="reservation_manage.jsp#res<%= temp.getReservationId().getReservationId() %>"><%= temp.getReservationId().getReservationId() %></a></td>
                                    <td><a href="reservation_<%= temp.getDiscriminator() %>_edit.jsp?item=<%= temp.getReservationItemId() %>">edit</a> | 
                                        <a href="reservation_item_delete?item=<%= temp.getReservationItemId() %>">delete</a></td>
                                </tr>
                                <% } %>

                            </table>
                            <h2 class="title">&nbsp;</h2>
                            <div class="post"></div>
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
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>
