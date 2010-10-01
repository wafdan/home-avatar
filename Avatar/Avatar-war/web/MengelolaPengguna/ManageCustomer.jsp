<%-- 
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.CustomerJpaController" %>
<%@ page import="AvatarEntity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%! CustomerJpaController c = new CustomerJpaController();
    List<Customer> l=null;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>

        <!-- start header -->
        <div id="header">
            <div id="menu">
                <ul>
                    <li><a href="#">Profile</a></li>
                    <li class="current_page_item"><a href="#">User</a></li>
                    <li><a href="#">Facilities</a></li>
                    <li><a href="#">Statistic</a></li>
                    <li><a href="#">Post</a></li>
                    <li><a href="#">Repository</a></li>
                    <li><a href="#">Reservation</a></li>
                    <li><a href="#">Payment</a></li>
                </ul>
            </div>
        </div>
        <!-- end header -->

        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Daftar Customer</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr>
                                    <th bgcolor="#262626" width="29">No.</th>
                                    <th bgcolor="#262626" width="179">Username</th>
                                    <th bgcolor="#262626" width="89">Name</th>
                                    <th bgcolor="#262626" width="77">Identity Type</th>
                                    <th bgcolor="#262626" width="96">Identity Number</th>

                                </tr>

                                <%
                                l=c.findCustomerEntities();
                                            for (Iterator<Customer> i =  l.iterator(); i.hasNext();) {
                                                Customer temp = i.next();
                                                
                                %>
                                <tr>
                                    <td></td>
                                    <td><div style="overflow:auto"><%= temp.getUsername() %></div></td>
                                    <td><div style="overflow:auto"><%= temp.getName() %></div></td>
                                    <td> <%= temp.getIdentityType() %> </td>
                                    <td> <%= temp.getIdentityNumber() %> </td>
                                    <td><a href="#">edit</a></td>
                                    <td><a href="#">delete</a></td>
                                </tr>
                                <% }%>

                            </table>
                            <h2 class="title">&nbsp;</h2>
                            <div class="post"></div>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>User Management</h2>
                                </div>
                                <ul>
                                    <li><a href="TambahStaf.jsp">Add New Staff</a></li>
                                    <li><a href="TambahCustomer.jsp">Add New Customer</a></li>
                                    <li><a href="ManageStaff.jsp">Manage Staff</a></li>
                                    <li><a href="ManageCustomer.jsp">Manage Customer</a></li>
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

