<%-- 
    Document   : sidebar
    Created on : 22 Okt 10, 10:54:18
    Author     : TOSHIBA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- start sidebar -->
<div id="sidebar">
    <div id="sidebar-title">
        <h2>Reservation Management</h2><br />
    </div>
    <%-- <label>ACCOMODATIONS</label>--%>
    <ul>
        <strong>ACCOMODATIONS</strong>
        <li><a href="reservation_add_room.jsp">Add Reservation</a></li>
        <li><a href="reservation_manage_room.jsp">Manage Reservation</a></li>
    </ul>
    <ul>
        <strong>MEETINGS & EVENTS</strong>
        <li><a href="reservation_add_hall.jsp">Add Reservation</a></li>
        <li><a href="reservation_manage_hall.jsp">Manage Reservation</a></li>
    </ul>
    <ul>
        <strong>OTHER SERVICES</strong>
        <li><a href="reservation_add_services.jsp">Add Reservation</a></li>
        <li><a href="reservation_manage_services.jsp">Manage Reservation</a></li>
    </ul>
    <ul>
        <strong>NOTIFICATIONS</strong>
        <li><a href="reservation_manage_notification.jsp">Due Payment</a></li>
    </ul>
</div>
<!-- end sidebar -->