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
                            <table width="600" border="0" style="table-layout: fixed">
                                <tr>
                                    <%
                                                ProfileJpaController pjc = new ProfileJpaController();
                                                Profile p = pjc.findProfile(Boolean.TRUE);
                                    %>
                                    <td width="136" height="35" class="tablekiri">Hotel Name :</td>
                                    <td width="389"><%=p.getHotelName()%></td>
                                </tr>
                                <tr>
                                    <td height="37" class="tablekiri">Address:</td>
                                    <td><%=p.getHotelAddress1()%></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Phone :</td>
                                    <td><%=p.getHotelPhone() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Fax : </td>
                                    <td><%=p.getHotelFax() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">City</td>
                                    <td><%=p.getHotelCity() %></td>
                                </tr>
                                <tr>
                                    <td height="34" class="tablekiri">Email: </td>
                                    <td><%=p.getHotelEmail()%></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Country :</td>
                                    <td><%=p.getHotelCountry() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Hotel Account No 1 : </td>
                                    <td><%=p.getAccountNumber1() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Hotel Bank Name 1 : </td>
                                    <td><%=p.getBankName1() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Hotel Account Name 1 :</td>
                                    <td><%=p.getAccountName1() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Hotel Account No 2 : </td>
                                    <td><%=p.getAccountNumber2() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Hotel Bank Name 2 : </td>
                                    <td><%=p.getBankName2() %></td>
                                </tr>
                                <tr>
                                    <td class="tablekiri">Hotel Account Name 2 :</td>
                                    <td><%=p.getAccountName2() %></td>
                                </tr>
                                <tr>
                                    <td height="142" class="tablekiri">Logo</td>
                                    <td><img src="../<%=p.getHotelLogo() %>" width="125" height="125" alt="logo"></td>
                                </tr>
                                <tr>
                                    <td height="144" class="tablekiri">Description</td>
                                    <td><textarea disabled="true" style=" color:#FFF; background-color:#000; border-color:#000" cols="55" rows="6"><%=p.getHotelDescription()%></textarea></td>
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

