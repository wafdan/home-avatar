<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.RoomReservationJpaController" %>
<%@ page import="AvatarEntity.RoomReservation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%! RoomReservationJpaController cr = new RoomReservationJpaController();
    List<RoomReservation> lr = null;
%>
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
            <% } %>
            <div id="menu">
                <ul>
                    <li><a href="profile_manage.jsp">Profile</a></li>
                    <li class="current_page_item"><a href="#">User</a></li>
                    <li><a href="fac_room_manage.jsp">Facilities</a></li>
                    <li><a href="#">Statistic</a></li>
                    <li><a href="#">Post</a></li>
                    <li><a href="#">Repository</a></li>
                    <li><a href="#">Reservation</a></li>
                    <li><a href="#">Payment</a></li>
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
                        <h1 class="title">Daftar Reservasi Kamar</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr>
                                    <th bgcolor="#262626" width="29">No.</th>
                                    <th bgcolor="#262626" width="179">Username</th>
                                    <th bgcolor="#262626" width="89">No. Kamar</th>
                                    <th bgcolor="#262626" width="77">Tgl Masuk</th>
                                    <th bgcolor="#262626" width="96">Tgl Keluar</th>

                                </tr>

                                <%
                                            out.write("tes masuk 1");
                                                int editIndex = 0;
                                            try {
                                                String Index = request.getParameter("edit");
                                                editIndex = Integer.parseInt(Index);
                                            } catch (NullPointerException ex) {
                                                editIndex = -1;
                                            } catch (NumberFormatException ex) {
                                                editIndex = -1;
                                            }

                                            int index = 0;
                                            RoomReservationJpaController jpr = new RoomReservationJpaController();
                                            List<RoomReservation> roomList = jpr.findRoomReservationEntities();
                                            if (editIndex == -1) {
                                                out.write("tes masuk 2");
                                                lr = cr.findRoomReservationEntities();
                                                out.write("tes masuk 3");
                                                for (Iterator<RoomReservation> ir = lr.iterator(); ir.hasNext();) {
                                                    out.write("tes masuk 4");
                                                    RoomReservation temp = ir.next();
                                                    index++;
                                %>
                                <tr>
                                    <td><%=index %></td>
                                    <td><div style="overflow:auto"><% /* = temp.getReservationItemId() */  %></div></td>
                                    <td><div style="overflow:auto"><%= temp.getRoomNo().toString() %></div></td>
                                    <td> <%= temp.getEntryDate().toString() %> </td>
                                    <td> <%= temp.getExitDate().toString() %> </td>
                                    <td><a href="?edit=<%=index%>">edit</a></td>
                                    <td><a href="HapusRoomReservasi?delete=<%= temp.getReservationItemId() %>">delete</a></td>
                                </tr>
                                <% }
                                                }
            else
            {
                int iterator=0;
                for(Iterator<RoomReservation> ir = roomList.iterator(); ir.hasNext();)
                {
                    RoomReservation temp=ir.next();
                    iterator++;

                %>
                <tr><td><%=iterator%></td>
                <%
                if(iterator==editIndex){
                 %>

                 <form action="EditCustomer" method="get">
                     <td><input type="text" name="username" id="username" disabled="true" value="<%= temp.getReservationItemId() %>"></td>
                     <td><input type="text" name="name" id="name" value="<%=temp.getRoomNo().toString() %>"> </td>
                     <td><input type="text" name="itype" id="itype" value="<%=temp.getEntryDate().toString()%>"></td>
                     <td><input type="text" name="inumber" id="inumber" value="<%=temp.getExitDate().toString() %>"></td>
                     <td><input type="submit" value="Save" onclick="this.form.username.disabled=false;"/> </td>
                 </form>
                <td><a href="HapusRoomReservasi?delete=<%= temp.getReservationItemId() %>"> delete</a></td>
                <td><a href="ManageCustomer.jsp"> cancel </a></td>



                 <% }else{%>

       <td><div style="overflow:auto"><%= temp.getReservationItemId()%></div></td>
                                    <td><div style="overflow:auto"><%= temp.getRoomNo().toString() %></div></td>
                                    <td> <%= temp.getEntryDate().toString()%> </td>
                                    <td> <%= temp.getExitDate().toString()%> </td>
                                    <td><a href="?edit=<%=iterator%>">edit</a></td>
                                    <td><a href="HapusRoomReservasi?delete=<%= temp.getReservationItemId() %>">delete</a></td>

           <%}}}%>

                            </table>
                            <h2 class="title">&nbsp;</h2>
                            <div class="post"></div>
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
                                    <li>Room
Reservation                                        
  <ul>
                                            <li><a href="reservation_add_room.jsp">Add New Reservation</a></li>
                                            <li><a href="reservation_manage_room.jsp">Manage Reservation</a></li>
                                        </ul>
                                    </li>
                                    <hr />
                                    <li>Hall
Reservation                                        
  <ul>
                                            <li><a href="reservation_add_hall.jsp">Add New Reservation </a></li>
                                            <li><a href="reservation_manage_hall.jsp">Manage Reservation </a></li>
                                        </ul>
                                    </li>
                                    <hr/>
                                    <li>Services Reservation 
                                        <ul>
                                            <li><a href="reservation_add_services.jsp">Add New Reservation</a></li>
                                            <li><a href="reservation_manage_services.jsp">Manage Reservation</a></li>
                                        </ul>
                                    </li>                                    
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
