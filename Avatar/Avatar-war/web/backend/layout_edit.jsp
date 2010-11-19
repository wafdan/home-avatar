<%-- 
    Document   : layout_edit
    Created on : 26 Okt 10, 17:29:59
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
Layout toEdit = (Layout) request.getAttribute("toEdit");
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
                        <h1 class="title">Edit Layout</h1>
                        <form method="post" name="editLayout" id="editLayout" action="layout_edit">
                            <input type="hidden" name="layoutNo" id="layoutNo" value="<%= toEdit.getLayoutNo() %>" />
                            <table border="0">
                                <tr>
                                    <td>Layout No</td>
                                    <td>:</td>
                                    <td><%= toEdit.getLayoutNo() %></td>
                                </tr>
                                <tr>
                                    <td>Layout Name</td>
                                    <td>:</td>
                                    <td><input type="text" name="layoutName" id="layoutName" size="15" maxlength="20" value="<%= toEdit.getLayoutName() %>" /></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="left">
                                        <input type="submit" name="update" id="update" value="Update" />
                                    </td>
                                </tr>
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