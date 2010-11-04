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
    List<Customer> l = null;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
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

                                            int editIndex = 0;
                                            try {
                                                String Index = request.getParameter("edit");
                                                editIndex = Integer.parseInt(Index);
                                            } catch (NullPointerException ex) {
                                                editIndex = -1;
                                            } catch (NumberFormatException ex) {
                                                editIndex = -1;
                                            }

                                            int index = 0;
                                            CustomerJpaController jpa = new CustomerJpaController();
                                            List<Customer> staffList = jpa.findCustomerEntities();
                                            if (editIndex == -1) {
                                                l = c.findCustomerEntities();
                                                for (Iterator<Customer> i = l.iterator(); i.hasNext();) {
                                                    Customer temp = i.next();
                                                    index++;
                                %>
                                <tr>
                                    <td><%=index%></td>
                                    <td><div style="overflow:auto"><%= temp.getUsername()%></div></td>
                                    <td><div style="overflow:auto"><%= temp.getName()%></div></td>
                                    <td> <%= temp.getIdentityType()%> </td>
                                    <td> <%= temp.getIdentityNumber()%> </td>
                                    <td><a href="?edit=<%=index%>">edit</a></td>
                                    <td><a href="HapusCustomer?delete=<%= temp.getUsername()%>">delete</a></td>
                                </tr>
                                <% }
                                                                            } else {
                                                                                int iterator = 0;
                                                                                for (Iterator<Customer> i = staffList.iterator(); i.hasNext();) {
                                                                                    Customer temp = i.next();
                                                                                    iterator++;

                                %>
                                <tr><td><%=iterator%></td>
                                    <%
                                                        if (iterator == editIndex) {
                                    %>

                                <form action="EditCustomer" method="get">
                                    <td><input type="text" name="username" id="username" disabled="true" value="<%= temp.getUsername()%>"></td>
                                    <td><input type="text" name="name" id="name" value="<%=temp.getName()%>"> </td>
                                    <td><input type="text" name="itype" id="itype" value="<%=temp.getIdentityType()%>"></td>
                                    <td><input type="text" name="inumber" id="inumber" value="<%=temp.getIdentityNumber()%>"></td>
                                    <td><input type="submit" value="Save" onclick="this.form.username.disabled=false;"/> </td>
                                </form>
                                <td><a href="HapusCustomer?delete=<%= temp.getUsername()%>"> delete</a></td>
                                <td><a href="ManageCustomer.jsp"> cancel </a></td>



                                <% } else {%>

                                <td><div style="overflow:auto"><%= temp.getUsername()%></div></td>
                                <td><div style="overflow:auto"><%= temp.getName()%></div></td>
                                <td> <%= temp.getIdentityType()%> </td>
                                <td> <%= temp.getIdentityNumber()%> </td>
                                <td><a href="?edit=<%=iterator%>">edit</a></td>
                                <td><a href="HapusCustomer?delete=<%= temp.getUsername()%>">delete</a></td>

                                <%}
                           }
                       }%>

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
                                <hr />
                                <ul>
                                    <li>STAFF
                                        <ul>
                                            <li><a href="staff_add.jsp">Add New Staff</a></li>
                                            <li><a href="staff_manage.jsp">Manage Staff</a></li>
                                        </ul>
                                    </li>
                                    <hr />
                                    <li>CUSTOMER
                                        <ul>
                                            <li><a href="customer_add.jsp">Add New Customer</a></li>
                                            <li><a href="customer_manage.jsp">Manage Customer</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                <hr />
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

