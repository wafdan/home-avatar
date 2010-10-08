<%-- 
    Document   : services
    Created on : 30 Sep 10, 21:44:44
    Author     : kamoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Domesticated
Description: A two-column, fixed-width design for 1024x768 screen resolutions.
Version    : 1.0
Released   : 20100701

-->
<%@ page import="AvatarEntity.OtherServices" %>
<%@ page import="Layanan.MelihatLayananController" %>
<%@ page import="Layanan.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Other Services</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.slidertron-0.1.js"></script>
        <link href="styles/style.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">
            @import "styles/slidertron.css";
        </style>
    </head>
    <body>
        <!-- end #header-wrapper -->
        <%
                    MelihatLayananController ctrl = new MelihatLayananController();
                    List<OtherServices> services = ctrl.getPublishedOtherServicesList();
                    OtherServices cur;
                    String pid = request.getParameter("id");
                    if (pid == null) {
                        cur = services.get(0);
                    } else {
                        cur = ctrl.getOtherServices(pid);
                    }

                    Boolean isLogin = false;
                    if (session.getAttribute("uname") != null) {
                        isLogin = true;
                    }
        %>
        <div id="header">
            <div id="logo">
                <h1><a href="#">Spons Hotel</a></h1>
            </div>
            <div id="menu">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="reservation.jsp" class="first">Reservation</a></li>
                    <li><a href="rooms.jsp">Rooms</a></li>
                    <li><a href="hall.jsp">Meeting & Events</a></li>
                    <li class="current_page_item"><a href="services.jsp">Other Services</a></li>
                    <li><a href="contactus.jsp">Contact Us</a></li>
                </ul>
            </div>
            <!-- end #menu -->
        </div>
        <%
        if ((session.getAttribute("name")) != null) {
        %>
        <div id="loginstatus">Anda Login sebagai : <%=session.getAttribute("name")%>
            <a href="Logout">Logout</a>
        </div>
        <%}%>
        <!-- end #header -->
        <hr />
        <div id="page">
            <div id="page-bgtop">
                <div id="content">
                    <div class="cartbox"><% out.println(ctrl.c.count() + " Items");%>In Your Cart</div>
                    <div class="post">
                        <%  out.println("<h2 class='title'>" + cur.getProductType() + "</h2>");
                                    out.println("<br />");
                                    /*if (!isLogin) {
                                    out.println("<a class='book' href='index.jsp'>Add to Cart</a>");
                                    } else {
                                    out.println("<a class='book' href='#'>Add to Cart</a>");
                                    }*/
                                    out.println("<div class='entry'>");
                                    out.println("<p><img src='" + cur.getImage() + "' />" + cur.getDescription() + "</p>");
                                    out.println("</div>");
                        %>
                    </div>
                </div>
                <!-- end #content -->
                <div id="sidebar">
                    <ul>
                        <li>
                            <h2>SERVICE TYPE</h2>
                            <ul>
                                <%
                                            Iterator Iter = services.iterator();
                                            while (Iter.hasNext()) {
                                                OtherServices srv = (OtherServices) Iter.next();
                                                out.println("<li><a href='services.jsp?id=" + srv.getProductId() + "'>" + srv.getProductType() + "</a></li>");
                                            }
                                %>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- end #sidebar -->
                <div style="clear: both;">&nbsp;</div>
            </div>
            <!-- end #page -->
        </div>
        <div id="footer">
            <p>Copyright (c) 2008 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/">CSS Templates</a>.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>
