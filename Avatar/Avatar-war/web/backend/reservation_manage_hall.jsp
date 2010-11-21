<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.HallReservationJpaController" %>
<%@ page import="AvatarEntity.HallReservation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 1){
    HallReservationJpaController c = new HallReservationJpaController();
    List<HallReservation> l = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm");
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
                        <h1 class="title">List of Hall Reservations</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr class="headertable">
                                    <th bgcolor="#262626">Timestamp</th>
                                    <th bgcolor="#262626">Customer</th>
                                    <th bgcolor="#262626">Package Type</th>
                                    <th bgcolor="#262626">Date</th>
                                    <th bgcolor="#262626">Time</th>
                                    <th bgcolor="#262626">Reservation</th>

                                </tr>

                                <%
                                    l = c.findHallReservationEntities();
                                    for (Iterator<HallReservation> i = l.iterator(); i.hasNext();) {
                                        HallReservation temp = i.next();
                                %>
                                <tr>
                                    <td><%= datetime.format(temp.getReservationTime()) %></td>
                                    <td><%= temp.getReservationId().getUsername().getName() %></td>
                                    <td><%= temp.getProductId().getProductType() %></td>
                                    <td><%= sdf.format(temp.getUseDate()) %></td>
                                    <td><%= time.format(temp.getBeginTime()) %> - <%= time.format(temp.getEndTime()) %></td>
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
