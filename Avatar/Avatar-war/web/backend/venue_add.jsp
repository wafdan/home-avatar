<%-- 
    Document   : venue_add
    Created on : 26 Okt 10, 17:46:23
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Layout" %>
<%@page import="AvatarEntity.LayoutJpaController" %>
<%@page import="AvatarEntity.Venue" %>
<%@page import="AvatarEntity.VenueLayout" %>
<%@page import="java.util.List" %>
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 2){
%>
<%
            LayoutJpaController ljpa = new LayoutJpaController();
            List<Venue> lVen = (List<Venue>) request.getAttribute("returnList");
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
                        <h1 class="title">Add Venue</h1>
                        <form method="post" name="addVenue" id="addVenue" action="venue_add">
                            <table border="0">
                                <tr>
                                    <th>Venue No</th>
                                    <th>Venue Name</th>
                                    <th>Description</th>
                                </tr>
                                <tr style="vertical-align:top;">
                                    <td><input type="text" name="venueNo" id="venueNo" size="4" maxlength="6" /></td>
                                    <td><input type="text" name="venueName" id="venueName" size="15" maxlength="25"></td>
                                    <td><textarea name="description" id="description" cols="25" rows="2"></textarea></td>
                                </tr>
                            </table>
                            <h2>Capacity per Layout</h2>
                            <table border="0">
                                <% for (Layout lay : ljpa.findLayoutEntities()) {%>
                                <tr>
                                    <td><%= lay.getLayoutName()%></td><td>:</td>
                                    <td>
                                        <input type="text" name="layout_<%= lay.getLayoutNo()%>"
                                               id="layout_<%= lay.getLayoutNo()%>" size="3" maxlength="4" />
                                    </td>
                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="3" align="left">
                                        <input type="submit" name="add" id="add" value="Add" />
                                    </td>
                                </tr>
                            </table>
                            <h2>List of Venues</h2>
                            <table border="1">
                                <tr>
                                    <th>Venue No</th>
                                    <th>Venue Name</th>
                                    <th>Description</th>
                                    <th>Capacity</th>
                                    <th>Venue Images</th>
                                    <th>Action</th>
                                </tr>
                                <%
                                            for (Venue ind : lVen) {
                                %>
                                <tr>
                                    <td style="vertical-align: middle;"><%= ind.getVenueNo()%></td>
                                    <td style="vertical-align: middle;"><%= ind.getVenueName()%></td>
                                    <td style="vertical-align: middle;"><%= (ind.getDescription() != null ? ind.getDescription() : "")%></td>
                                    <td style="vertical-align: middle;"><% for (VenueLayout vl : ind.getVenueLayoutCollection()) {%>
                                        <%= vl.getLayout().getLayoutName()%>: <%= vl.getCapacity()%><br />
                                        <% }%></td>
                                    <td style="vertical-align: middle;"><img src="../<%=ind.getImage() %>" alt="<%=ind.getVenueName() %> Image" width="125" /></td>
                                    <td style="vertical-align: middle;"> <a href="venue_delete?venueNo=<%= ind.getVenueNo()%>">delete</a> |
                                        <a href="venue_edit?venueNo=<%= ind.getVenueNo()%>">edit</a></td>
                                    
                                </tr>
                                <%}%>
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