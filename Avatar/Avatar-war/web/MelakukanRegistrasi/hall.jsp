<%-- 
    Document   : hall
    Created on : 30 Sep 10, 21:45:28
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
<%@ page import="AvatarEntity.Venue" %>
<%@ page import="AvatarEntity.Hall" %>
<%@ page import="Layanan.MelihatLayananController" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Domesticated by Free CSS Templates</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="jquery/jquery.slidertron-0.1.js"></script>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
@import "slidertron.css";
</style>
</head>
<body>
<!-- end #header-wrapper -->
<%
    MelihatLayananController ctrl = new MelihatLayananController();
    List<Venue> venues = ctrl.getVenueList();
    List<Hall> packages = ctrl.getHallList();
%>
<div id="header">
	<div id="logo">
		<h1><a href="#">Spons Hotel</a></h1>
	</div>
	<div id="menu">
		<ul>
                        <li><a href="index.jsp">Overview</a></li>
                        <li><a href="reservation.jsp" class="first">Reservation</a></li>
			<li><a href="rooms.jsp">Rooms</a></li>
                        <li class="current_page_item"><a href="hall.jsp">Meeting & Events</a></li>
			<li><a href="services.jsp">Other Services</a></li>
                        <li><a href="contactus.jsp">Contact Us</a></li>
		</ul>
	</div>
	<!-- end #menu -->
</div>
<!-- end #header -->
<hr />
<div id="page">
  <div id="page-bgtop">
		<div id="content">
			<div class="post">
    <h2 class="title">&nbsp;</h2>
				<div class="entry">
					<p>&nbsp;</p>
</div>
			</div>
</div>
		<!-- end #content -->
		<div id="sidebar">
			<ul>
				<li>
					<h2>VENUE TYPE</h2>
					<ul>
					  <%
                                            Iterator Iter = venues.iterator();
                                            while (Iter.hasNext()) {
                                                Venue ven = (Venue) Iter.next();
                                                out.println("<li><a href='MelihatLayanan'>"+ven.getVenueName()+"</a></li>");
                                            }
                                          %>
                                        </ul>
                                </li>
                                <li>
					<h2>PACKAGE TYPE</h2>
					<ul>
					  <%
                                            Iter = packages.iterator();
                                            while (Iter.hasNext()) {
                                                Hall pkg = (Hall) Iter.next();
                                                out.println("<li><a href='MelihatLayanan'>"+pkg.getProductType()+"</a></li>");
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
