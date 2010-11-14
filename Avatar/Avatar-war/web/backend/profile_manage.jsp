<%-- 
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Profile"%>
<%@page import="AvatarEntity.ProfileJpaController"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
            <!--
            div.box1 {margin:0 auto;
                      width:500px;
                      background:#222;
                      position:relative;
                      top:50px;
                      border:1px solid #262626;
            }
            -->
        </style>
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
                        <h1 class="title">Hotel's Profile</h1>
                        <div class="post">
                            <table width="592" border="0" style="table-layout: fixed">
                                <tr>
                                    <%
                                                ProfileJpaController pjc = new ProfileJpaController();
                                                Profile p = pjc.findProfile(Boolean.TRUE);
                                    %>
                                    <td width="136" height="35">Hotel Name:</td>
                                    <td width="389"><%=p.getHotelName()%></td>
                                </tr>
                                <tr>
                                    <td height="37">Address:</td>
                                    <td><%=p.getHotelAddress1()%></td>
                                </tr>
                                <tr>
                                    <td height="37">&nbsp;</td>
                                    <td><%=p.getHotelAddress2()%></td>
                                </tr>
                                <tr>
                                    <td height="34">Email: </td>
                                    <td><%=p.getHotelEmail()%></td>
                                </tr>
                                <tr>
                                    <td height="142">Logo</td>
                                    <td><img src="../images/cooltext471355125.png" width="235" height="103" alt="logo"></td>
                                </tr>
                                <tr>
                                    <td height="144">Description</td>
                                    <td><textarea style=" color:#FFF; background-color:#000; border-color:#000" cols="55" rows="6"><%=p.getHotelDescription()%></textarea></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Hotel Profile</h2>
                                </div>
                                <ul>
                                    <li><a href="profile_manage.jsp">Show Profile</a></li>
                                    <li><a href="profile_edit.jsp">Edit Profile</a></li>
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

