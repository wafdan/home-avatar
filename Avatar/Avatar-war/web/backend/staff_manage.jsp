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
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 0){
%>
<%!
    String option0 = "<select id=\"position\" name=\"position\"> <option value=\"0\">Administartor</option><option value=\"1\">Receptionis</option> <option value=\"2\">Manager</option></select>";
    String option1 = "<select id=\"position\" name=\"position\"> <option value=\"0\">Administartor</option><option value=\"1\" selected=\"true \">Receptionis</option> <option value=\"2\">Manager</option></select>";
    String option2 = "<select id=\"position\" name=\"position\"> <option value=\"0\">Administartor</option><option value=\"1\">Receptionis</option> <option value=\"2\" selected=\"true\">Manager</option></select>";
    StaffJpaController s = new StaffJpaController();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function confirmAction()
            {return confirm("Do you really want to delete?")}
        </script>
    </head>
    <body>

        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->

        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">List of Staffs</h1>
                        <div class="post">
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
                                    if (editIndex == -1) {%>
                                    <table width="603" border="1" style="table-layout:fixed">
                                        <tr class="headertable">
                                            <th bgcolor="#262626" width="29">No.</th>
                                            <th bgcolor="#262626" width="179">Username</th>
                                            <th bgcolor="#262626" width="89">Name</th>
                                            <th bgcolor="#262626" width="77">ID</th>
                                            <th bgcolor="#262626" width="96">Position</th>
                                            <th bgcolor="#262626">Options</th>
                                        </tr>
                                        <%
                                        for (Iterator<Staff> i = staffList.iterator(); i.hasNext();) {
                                            Staff temp = i.next();
                                        %>
                                <tr>
                                    <td><%=++index%></td>
                                    <td><div style="overflow:auto"> <% out.write(temp.getUsername());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getName());%></div></td>
                                    <td><div style="overflow:auto"> <% out.write(temp.getEmploymentId());%></div></td>
                                    <td><div style="overflow:auto"> <% out.write(s.translatePosition(temp.getPosition()));%></div></td>
                                    <td><a href="staff_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a>
                                    <a onclick="return confirmAction()" href="HapusStaff?delete=<% out.write(temp.getUsername());%>">delete</a></td>
                                </tr>
                                <%}%>
                                </table>
                                <%} else {
                                    int iterator = 0;
                                    for (Iterator<Staff> i = staffList.iterator(); i.hasNext();) {
                                        Staff temp = i.next();
                                        iterator++;
                                %>
                                    <% index++;
                                    if (iterator == editIndex) {%>
                                    <%--<td>
                                        <%out.write(Integer.toString(index));%>
                                    </td>--%>
                                <form action="../MengelolaPengguna/EditStaff?username=<%= temp.getUsername()%>" method="get">
                                    <div class="required">
                                        <label class="flabel">No. </label>
                                        <input type="text" class="input_text" readonly="true" value="<%= editIndex%>"/>
                                    </div><br/>
                                    <div class="required">
                                        <label class="flabel">Username :</label>
                                        <input type="text" class="input_text" readonly="true" name="username" value="<%=temp.getUsername()%>"/>
                                    </div><br/>
                                    <div class="required">
                                        <label class="flabel">Name:</label>
                                        <input type="text" class="input_text" id="name" name="nama" value="<%=temp.getName()%>"/>
                                    </div><br/>
                                    <div class="required">
                                        <label class="flabel">Employee ID:</label>
                                        <input type="text" class="input_text" id="itype" name="emID" value="<%=temp.getEmploymentId()%>"/>
                                    </div><br/>
                                    <div class="required">
                                        <label class="flabel">Position:</label>
                                        <%if (temp.getPosition() == 0)
                                        {out.write(option0);}
                                        else if (temp.getPosition() == 1)
                                        {out.write(option1);}
                                        else
                                        {out.write(option2);}
                                        %>
                                    </div><br/>
                                        
                                    <input class="button" type="submit" value="save" onclick="this.form.username.disabled=false;"/>
                                    <a onclick="return confirmAction()" class="button" href="HapusStaff?delete=<% out.write(temp.getUsername());%>">delete</a>
                                    <a class="button" href="staff_manage.jsp"> cancel </a>
                                </form>
                                <% } 
                                
                                    }
                                }%>
                                
                        </div>
                    </div>
                
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>User Management</h2>

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
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>
