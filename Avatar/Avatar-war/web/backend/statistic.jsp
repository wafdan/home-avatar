<%-- 
    Document   : statistic
    Created on : 05 Nov 10, 23:15:14
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
                        <img src ="generator?type=room" alt="Current month statistic of room" />
                        <img src ="generator?type=hall" alt="Current month statistic of hall" />
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Statistics Views</h2><br />
                                </div>
                                <ul>
                                    <li>STAFF
                                        <ul>
                                            <li><a href="staff_add.jsp">Add New Staff</a></li>
                                            <li><a href="staff_manage.jsp">Manage Staff</a></li>
                                        </ul>
                                    </li>
                                    <li>CUSTOMER
                                        <ul>
                                            <li><a href="customer_add.jsp">Add New Customer</a></li>
                                            <li><a href="customer_manage.jsp">Manage Customer</a></li>
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
