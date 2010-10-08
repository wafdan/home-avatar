<%-- 
    Document   : AccountInfo
    Created on : 30 Sep 10, 12:03:18
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "java.util.List,java.util.Iterator,AvatarEntity.Customer,AvatarEntity.CustomerJpaController,AvatarEntity.Country,AvatarEntity.CountryJpaController" %>
<%
            String username = session.getAttribute("username").toString();
            //TODO: ganti dengan session beneran
            CustomerJpaController control = new CustomerJpaController();
            Customer cust = control.findCustomer(username);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account Information</title>
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jquery.slidertron-0.1.js"></script>
        <link href="styles/style.css" rel="stylesheet" type="text/css" media="screen" />
        <style type="text/css">
            @import "styles/slidertron.css";
        </style>
    </head>
    <body>
        <!-- end #header-wrapper -->
        <div id="header">
            <div id="logo">
                <h1><a href="#">Spons hotel</a></h1>
            </div>
            <div id="menu">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="reservation.jsp" class="first">Reservation</a></li>
                    <li><a href="rooms.jsp">Rooms</a></li>
                    <li><a href="hall.jsp">Meeting & Events</a></li>
                    <li><a href="services.jsp">Other Services</a></li>
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
                <div id="centerside">
                    <div id="wrap">
                        <form id="form_20674" class="appnitro"  method="post" action="TambahAkun">

                            <div class="form_description">
                                <h2>User Account Information</h2>
                            </div>

                            <ul id="list_field">
                                <li id="li_0" >
                                    <label class="description" for="fullname">Username </label>
                                    <div>
                                        <%= cust.getUsername()%>
                                    </div>
                                </li>
                                <li id="li_1" >
                                    <label class="description" for="fullname">Full Name </label>
                                    <div>
                                        <%= cust.getName()%>
                                    </div>
                                </li>

                                <li id="li_2" >
                                    <label class="description" for="identity_type">ID Type </label>
                                    <div>
                                        <%= cust.getIdentityType()%>
                                    </div>
                                </li>

                                <li id="li_3" >
                                    <label class="description" for="identity_number">ID Number </label>
                                    <div>
                                        <%= cust.getIdentityNumber()%>
                                    </div>
                                </li>

                                <li id="li_4" >
                                    <label class="description" for="telephone">Phone Number </label>
                                    <div>
                                        <%= cust.getTelephone()%>
                                    </div>
                                </li>

                                <li id="li_5" >
                                    <label class="description" for="email">Email </label>
                                    <div>
                                        <%= cust.getEmail()%>
                                    </div>
                                </li>

                                <li id="li_6" >
                                    <label for="address1">Street Address</label>

                                    <div>
                                        <%= cust.getAddress1()%><br />
                                        <% if (cust.getAddress2() != null) {
                                                    out.println(cust.getAddress2() + "<br />");
                                                } else {
                                                    out.println("");
                                                }%>
                                        <%= cust.getCity()%>, <%= cust.getCountry()%>
                                    </div>
                                </li>

                                <li class="buttons">
                                    <input type="hidden" name="form_id" value="20674" />

                                    <a href="Edit.jsp">Edit</a>
                                </li>
                            </ul>
                        </form>
                    </div>
                    <!--END wrap register-->
                </div>
                <!--END wrap-->
            </div>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>
                <!-- end #content -->
                <!-- end #sidebar -->
            </p>
            <div style="clear: both;">&nbsp;</div>
        </div>
        <!-- end #page -->
        <div id="footer">
            <p>Copyright (c) 2008 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/">CSS Templates</a>.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>