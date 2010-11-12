<%-- 
    Document   : statistic
    Created on : 05 Nov 10, 23:15:14
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String period = "";
if (request.getAttribute("from") != null && request.getAttribute("to") != null)
    period = "&from=" + request.getAttribute("from") + "&to=" + request.getAttribute("to");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Statistics of Services</h1>
                        <% if (request.getAttribute("specify") == null ||
                            (request.getAttribute("specify") != null && request.getAttribute("specify").equals("room"))) {
                        %>
                        <img src ="generator?type=room<%=period%>" alt="Statistics of room" />
                        <% } %>
                        <% if (request.getAttribute("specify") != null && request.getAttribute("specify").equals("room")) { %>
                        <img src ="generator?type=room<%=period%>&subtype=1" alt="Statistics of room" />
                        <!--<img src ="generator?type=room<%=period%>&subtype=2" alt="Statistics of room" /> -->
                        <% } %>
                        <% if (request.getAttribute("specify") == null ||
                            (request.getAttribute("specify") != null && request.getAttribute("specify").equals("hall"))) {
                        %>
                        <img src ="generator?type=hall<%=period%>" alt="Statistics of hall" />
                        <% } %>
                        <% if (request.getAttribute("specify") != null && request.getAttribute("specify").equals("hall")) { %>
                        <img src ="generator?type=hall<%=period%>&subtype=1" alt="Statistics of room" />
                        <!--<img src ="generator?type=hall<%=period%>&subtype=2" alt="Statistics of room" />-->
                        <% } %>
                        <% if (request.getAttribute("specify") == null ||
                            (request.getAttribute("specify") != null && request.getAttribute("specify").equals("other"))) {
                        %>
                        <img src ="generator?type=other<%=period%>" alt="Statistics of other facilites/services" />
                        <% } %>
                        <% if (request.getAttribute("specify") != null && request.getAttribute("specify").equals("other")) { %>
                        <!--<img src ="generator?type=other<%=period%>&subtype=1" alt="Statistics of other facilites/services" />
                        <img src ="generator?type=other<%=period%>&subtype=2" alt="Statistics of other facilites/services" />-->
                        <% } %>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Statistics</h2>
                                </div>
                                <ul>
                                    <li>VIEWS
                                        <ul>
                                            <li><a href="statistic">Current Statistics</a></li>
                                            <li><a href="statistic_selector.jsp">Switch View</a></li>
                                        </ul>
                                    </li>
                                </ul>
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
