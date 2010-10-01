<%-- 
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="AvatarEntity.StaffJpaController" %>
<%@ page import="AvatarEntity.Staff" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%!    String option0 = "<select id=\"position\" name=\"position\"> <option value=\"0\">Administartor</option><option value=\"1\">Receptionis</option> <option value=\"2\">Manager</option></select>";
    String option1 = "<select id=\"position\" name=\"position\"> <option value=\"0\">Administartor</option><option value=\"1\" selected=\"true \">Receptionis</option> <option value=\"2\">Manager</option></select>";
    String option2 = "<select id=\"position\" name=\"position\"> <option value=\"0\">Administartor</option><option value=\"1\">Receptionis</option> <option value=\"2\" selected=\"true\">Manager</option></select>";
    StaffJpaController s = new StaffJpaController();
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
                        <h1 class="title">Daftar Staff</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr>
                                    <th bgcolor="#262626" width="29">No.</th>
                                    <th bgcolor="#262626" width="179">Username</th>
                                    <th bgcolor="#262626" width="89">Nama</th>
                                    <th bgcolor="#262626" width="77">ID</th>
                                    <th bgcolor="#262626" width="96">Position</th>

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
                                            StaffJpaController jpa = new StaffJpaController();
                                            List<Staff> staffList = jpa.findStaffEntities();
                                            if (editIndex == -1) {
                                                for (Iterator<Staff> i = staffList.iterator(); i.hasNext();) {
                                                    Staff temp = i.next();
                                %>
                                <tr>
                                    <td><%index++;
                                                                                        out.write(Integer.toString(index));
                                        %></td>

                                    <td><div style="overflow:auto"> <% out.write(temp.getName());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getUsername());%></div></td>
                                    <td><div style="overflow:auto"> <% out.write(temp.getEmploymentId());%></div></td>
                                    <td><div style="overflow:auto"> <% out.write(s.translatePosition(temp.getPosition()));%></div></td>
                                    <td><a href="ManageStaff.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></td>
                                    <td><a href="HapusStaff?delete=<% out.write(temp.getUsername());%>">delete</a></td>
                                </tr>

                                <%
                                                                                }
                                                                            } else {
                                                                                int iterator = 0;
                                                                                for (Iterator<Staff> i = staffList.iterator(); i.hasNext();) {
                                                                                    Staff temp = i.next();
                                                                                    iterator++;
                                %>
                                <tr>
                                    <% index++;
                                                                                                                        if (iterator == editIndex) {%>
                                    <td><%out.write(Integer.toString(index));
                                        %></td>
                                    
                                <form action="EditStaff?username=<%= temp.getUsername() %>" method="get">
                                    <td><% out.write("<input type=\"text\" value=\""+temp.getUsername()+ "\" id=\"username\" name=\"username\" disabled=\"true\"></input>");%></td>
                                    <td> <% out.write("<input type=\"text\" value=\"" + temp.getName() + "\" id=\"nama\" name=\"nama\"></input>");%></td>
                                    
                                    <td><% out.write("<input type=\"text\" value=\"" + temp.getEmploymentId() + "\" id=\"emID\" name=\"emID\"></input>");%></td>
                                    <td>
                                            <%
                                                                                                                                                                    if (temp.getPosition() == 0) {
                                                                                                                                                                        out.write(option0);
                                                                                                                                                                    } else if (temp.getPosition() == 1) {
                                                                                                                                                                        out.write(option1);
                                                                                                                                                                    } else {
                                                                                                                                                                        out.write(option2);
                                                                                                                                                                    }


                                            %></td>
                                    <td><input type="submit" value="save" onclick="this.form.username.disabled=false;"/> </td>
                                    <td><a href="HapusStaff?delete=<% out.write(temp.getUsername());%>">delete</a></td>
                                    <td><a href="ManageStaff.jsp"> cancel </a></td>
                                    
                                </form>
                                    <% } else {%>
                                    <td><%
                                                                                                                                                            out.write(Integer.toString(index));
                                        %></td>
                                    <td><div style="overflow:auto"> <% out.write(temp.getName());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getUsername());%></div></td>
                                    <td><div style="overflow:auto"> <% out.write(temp.getEmploymentId());%></div></td>
                                    <td><div style="overflow:auto"> <% out.write(s.translatePosition(temp.getPosition()));%></div></td>
                                    <td><a href="ManageStaff.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></td>
                                    <td><a href="HapusStaff?delete=<% out.write(temp.getUsername());%>">delete</a></td>

                                <%              }
                                                }
                                            }%>
                                <!--<tr>
                                    <td>&nbsp;</td>
                                    <td><div style="overflow:auto"> asfasfafafa&nbsp;</div></td>
                                    <td><div style="overflow:auto">&nbsp; knights_of_kangouw@yahoo.com</div></td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td><a href="#">edit</a></td>
                                    <td><a href="#">delete</a></td>
                                </tr>   -->                     </table>
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

