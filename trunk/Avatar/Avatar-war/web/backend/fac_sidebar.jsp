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
        <h2>Facilities Management</h2><br />
    </div>
    <%-- <label>ACCOMODATIONS</label>--%>
    <ul>
        <strong>ACCOMODATIONS</strong>
        <li><a href="fac_room_add.jsp">Add New Room Type</a></li>
        <li><a href="fac_room_manage.jsp">Manage Room Type</a></li>
    </ul>
    <ul>
        <strong>MEETINGS & EVENTS</strong>
        <li><a href="fac_hall_add.jsp">Add New Hall</a></li>
        <li><a href="fac_hall_manage.jsp">Manage Hall</a></li>
    </ul>
    <ul>
        <strong>OTHER SERVICES</strong>
        <li><a href="fac_serv_add.jsp">Add New Services</a></li>
        <li><a href="fac_serv_manage.jsp">Manage Services</a></li>
    </ul>
    <ul>
        <strong>ROOMS</strong>
        <li><a href="room_add">Manage Room</a></li>
    </ul>
    <ul>
        <strong>VENUES</strong>
        <li><a href="venue_add">Manage Venue</a></li>
    </ul>
    <ul>
        <strong>LAYOUTS</strong>
        <li><a href="layout_add">Manage Layout</a></li>
    </ul>
</div>
<!-- end sidebar -->
