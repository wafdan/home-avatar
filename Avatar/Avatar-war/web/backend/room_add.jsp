<%-- 
    Document   : room_add
    Created on : 26 Okt 10, 13:31:02
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.Room" %>
<%@page import="java.util.List" %>
<%
List<Room> lroom = (List<Room>) request.getAttribute("returnList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Backend Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
        if ((session.getAttribute("name")) != null) {
        %>
        <div id="loginstatus">Anda Login sebagai : <%=session.getAttribute("name")%>
            <a href="../Logout">Logout</a>
        </div>
        <div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Add Individual Room</h1>
                        <form method="post" name="addIndRoom" id="addIndRoom" action="room_add">
                            <table border="0">
                                <tr>
                                    <th>Room No</th>
                                    <th>Room Type</th>
                                    <th>Floor</th>
                                    <th>Room Name <small><i>(optional)</i></small></th>
                                </tr>
                                <tr>
                                    <td><input type="text" name="roomNo" id="roomNo" size="3" maxlength="6" /></td>
                                    <td><select name="roomType" id="roomType">
                                            <%
                                            AccomodationJpaController ajpa = new AccomodationJpaController();
                                            for (Accomodation ind : ajpa.findAccomodationEntities()) {
                                            %>
                                            <option value="<%= ind.getProductId() %>"><%= ind.getProductType() %></option>
                                            <% } %>
                                        </select></td>
                                    <td><input type="text" name="floor" id="floor" size="3" maxlength="3" /></td>
                                    <td><input type="text" name="roomName" id="roomName" size="15" maxlength="20" /></td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="left">
                                        <input type="submit" name="add" id="add" value="Add" />
                                    </td>
                                </tr>
                            </table>
                            <h2>List of Rooms</h2>
                            <table border="1">
                                <tr>
                                    <th>Room No</th>
                                    <th>Room Type</th>
                                    <th>Floor</th>
                                    <th>Room Name</th>
                                    <th>Action</th>
                                </tr>
                                <%
                                for (Room ind : lroom) {
                                %>
                                <tr>
                                    <td><%= ind.getRoomNo() %></td>
                                    <td><%= ind.getProductId().getProductType() %></td>
                                    <td><%= ind.getFloor() %></td>
                                    <td><%= (ind.getRoomName() != null ? ind.getRoomName() : "none" ) %></td>
                                    <td><a href="room_delete?roomNo=<%= ind.getRoomNo() %>">delete</a> |
                                        <a href="room_edit?roomNo=<%= ind.getRoomNo() %>">edit</a></td>
                                </tr>
                                <%}%>
                            </table>
                        </form>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="fac_sidebar.jsp" />
                    <!-- end sidebar -->
                </div>
            </div>
        </div>
        <!-- end page -->
        <%}%>
    </body>
</html>
