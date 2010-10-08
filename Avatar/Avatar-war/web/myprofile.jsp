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
                        <form id="accountform" method="post" action="TambahAkun">

                            <h2>User Account Information</h2>

                            <label for="username">Username : </label><div class="accountinfo"><%= cust.getUsername()%></div>
                      <br /><br />
                            <label for="name">Name : </label><%= cust.getName()%><br /><br />
                            <label for="identity_type">Identity Type : </label><%= cust.getIdentityType()%><br /><br />
                            <label for="identity_number">Identity Number : </label><%= cust.getIdentityNumber()%><br /><br />
                            <label for="telephone">Telephone : </label><%= cust.getTelephone()%><br /><br />
                            <label for="email">Email : </label><<%= cust.getEmail()%><br /><br />
                            <label for="address1">Address : </label><%= cust.getAddress1()%><br /><br />
                            <% if (cust.getAddress2() != null) {
                                            out.println(cust.getAddress2() + "<br />");
                                        } else {
                                            out.println("");
                                        }%>
                            <%= cust.getCity()%>, <%= cust.getCountry()%>

                            <br /><a href="Edit.jsp">Edit</a>
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
