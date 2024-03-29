<%-- 
    Document   : layout_add
    Created on : 26 Okt 10, 17:04:33
    Author     : Christian
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Layout" %>
<%@page import="AvatarEntity.LayoutJpaController" %>
<%@page import="java.util.List" %>
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 2){
%>
<%
List<Layout> lLay = (List<Layout>) request.getAttribute("returnList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Backend Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
        if ((session.getAttribute("name")) != null) {
        %>
        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Add Layout Type</h1>
                        <form method="post" name="addLayout" id="addLayout" action="layout_add">
                            <table border="0">
                                <tr>
                                    <td>Layout name</td>
                                    <td>:</td>
                                    <td><input type="text" name="layoutName" id="layoutName" size="15" maxlength="20" /></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="left">
                                        <input type="submit" name="add" id="add" value="Add" />
                                    </td>
                                </tr>
                            </table>
                            <h2>List of Layouts (for Events)</h2>
                            <table border="1">
                                <tr>
                                    <th>No</th>
                                    <th>Layout Name</th>
                                    <th>Action</th>
                                </tr>
                                <%
                                for (Layout ind : lLay) {
                                %>
                                <tr>
                                    <td><%= ind.getLayoutNo() %></td>
                                    <td><%= ind.getLayoutName() %></td>
                                    <td><a href="layout_delete?layoutNo=<%= ind.getLayoutNo() %>">delete</a> |
                                        <a href="layout_edit?layoutNo=<%= ind.getLayoutNo() %>">edit</a></td>
                                </tr>
                                <%}%>
                            </table>
                        </form>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="fac_sidebar.jsp" />
                    <!-- end sidebar -->
                    <div style="clear:both;">&nbsp;</div>
                </div>
            </div>
        </div>
        <!-- end page -->
        <%}%>
    </body>
</html>
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>