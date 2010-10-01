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
    List<OtherServices> services = ctrl.getPublishedOtherServicesList();
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
                        <li><a href="hall.jsp">Meeting & Events</a></li>
			<li class="current_page_item"><a href="services.jsp">Other Services</a></li>
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
    <h2 class="title">Range of services</h2>
    <br />
    <a class="book" href="">Book Now!</a></p>
				<div class="entry">
					<p>We want you to be able to relax, whether you are here on business or  on holiday. Do you need to get a shirt ironed, or need help finding the  vehicle to the Tangkuban Perahu mountain or to stay in touch with your  loved ones? Our range of services is extensive and designed to suit  many different needs. We also have a concierge available to provide you  with expert advice on everything Bandung has to offer.<br />
					  <br />
					  <strong>Read more about:<br />
				    </strong></p>
                    <ul>
                      <li><a href="spa.html">In-Room Spa</a></li>
                      <li><a href="">Fitness</a></li>
                      <li><a href="">Laundry &amp; Travelite</a></li>

                      <li><a href="">Room Service</a></li>
                      <li><a href="">Parking</a></li>
                      <li><a href="">WiFi/High Speed Internet Access</a></li>
                      <li><a href="">24h security</a></li>
                    </ul>
                </div>
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
                                                out.println("<li><a href='MelihatLayanan'>"+srv.getProductType()+"</a></li>");
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
