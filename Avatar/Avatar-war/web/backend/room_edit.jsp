<%-- 
    Document   : room_edit
    Created on : 26 Okt 10, 16:24:09
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.Room" %>
<%@page import="java.util.List" %>
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 2){
%>
<%
Room toEdit = (Room) request.getAttribute("toEdit");
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
        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Edit Individual Room</h1>
                        <form method="post" name="editIndRoom" id="editIndRoom" action="room_edit">
                            <input type="hidden" name="roomNo" id="roomNo" value="<%= toEdit.getRoomNo() %>" />
                            <table border="0">
                                <tr>
                                    <td>Room No</td>
                                    <td>:</td>
                                    <td><%= toEdit.getRoomNo() %></td>
                                </tr>
                                <tr>
                                    <td>Room Type</td>
                                    <td>:</td>
                                    <td><select name="roomType" id="roomType">
                                            <%
                                            AccomodationJpaController ajpa = new AccomodationJpaController();
                                            for (Accomodation ind : ajpa.findAccomodationEntities()) {
                                            %>
                                            <option value="<%= ind.getProductId() %>"<%=
                                            (toEdit.getProductId().getProductId().equals(ind.getProductId()) ?
                                                " selected=\"selected\"" : "")
                                            %>><%= ind.getProductType() %></option>
                                            <% } %>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Floor</td>
                                    <td>:</td>
                                    <td><input type="text" name="floor" id="floor" size="3" maxlength="3" value="<%= toEdit.getFloor() %>" /></td>
                                </tr>
                                <tr>
                                    <td>Room Name <small><i>(optional)</i></small></td>
                                    <td>:</td>
                                    <td><input type="text" name="roomName" id="roomName" size="15" maxlength="20" value="<%= (toEdit.getRoomName() != null ? toEdit.getRoomName() : "") %>" /></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="left">
                                        <input type="submit" name="update" id="update" value="Update" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="fac_sidebar.jsp" />
                    <!-- end sidebar -->
                    <div style="clear:both;">&nbsp;</div>
                </div>
            </div>
        </div>
        <!-- end page -->
        <%}%>
    </body>
</html>
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>