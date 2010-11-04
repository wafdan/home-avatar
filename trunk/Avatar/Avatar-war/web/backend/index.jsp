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
                        BACKEND for managing profile, user, staff, services, facilities, reservation data.
                        Choose menu above to start managing data.
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <%
                                        ProfileJpaController pjc=new ProfileJpaController();
                                        Profile p=pjc.findProfile(Boolean.TRUE);
                                    %>
                                    <h2><%=p.getHotelName() %></h2>
                                </div>
                                <ul>
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

