<%-- 
    Document   : fac_hall_add
    Created on : 21 Okt 10, 13:59:12
    Author     : TOSHIBA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page language="java" import="java.sql.*" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <style>
            #fmenu {
                border-bottom: 1px solid #ccc;
                margin: 0;
                padding-bottom: 19px;
                padding-left: 10px;
            }
            #fmenu ul,#fmenu li {
                display: inline;
                list-style-type: none;
                margin: 0;
                padding: 0;
            }
            #fmenu a:link, #fmenu a:visited {
                background: #E8EBF0;
                border: 1px solid #ccc;
                color: #666;
                float: left;
                font-size : small;
                font-weight : normal;
                line-height : 14px;
                margin-right : 8px;
                padding : 2px 10px 2px 10px;
                text-decoration : none;
            }
            #fmenu a:link.active, #fmenu a:visited.active {
                background: #fff;
                border-bottom: 1px solid #fff;
                color: #666;
            }
            #fmenu a:hover {
                color: #ff0;
            }
            #sidebar .curtab a{
                background: #666;
                padding: 5px;
                font-weight: bold;
                font-size: large;
            }

            label{
                float: left;
                width: 120px;
                font-weight: bold;
            }

            input, textarea{
                width: 180px;
                margin-bottom: 5px;
            }

            textarea{
                width: 250px;
                height: 150px;
            }

            .boxes{
                width: 1em;
            }

            #submitbutton{
                margin-left: 120px;
                margin-top: 5px;
                width: 90px;
            }

            br{
                clear: left;
            }
        </style>
        <script type="text/javascript">
            function confirmAction()
            {return confirm("Do you really want to delete?")}
        </script>
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
            <%
                        if ((session.getAttribute("name")) != null) {
            %>
            <div id="loginstatus">Anda Login sebagai : <%=session.getAttribute("name")%>
                <a href="../Logout">Logout</a>
            </div>
            <%}%>
            <div id="menu">
                <ul>
                    <li><a href="profile_manage.jsp">Profile</a></li>
                    <li ><a href="staff_manage.jsp">User</a></li>
                    <li class="current_page_item"><a href="#">Facilities</a></li>
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
                        <h1 class="title">Tambah Room</h1>
                        <ul id="fmenu">
                            <li id="fmenu-item1"><a href="fac_room_manage.jsp">Rooms</a></li>
                            <li id="fmenu-item2"><a href="#">Meetings & Events</a></li>
                            <li id="fmenu-item3"><a href="fac_serv_manage.jsp">Other Services</a></li>
                        </ul>
                        <div class="post">
                            <div class="fac1">
                                <form action="TambahHall" method="post">
                                    <label for="pid">Product ID</label>
                                    <input type="text" name="pid" id="pid" value="" maxlength="6"/><br />

                                    <label for="type">Product Type:</label>
                                    <input type="text" name="type" id="type" value="" maxlength="25"/><br />

                                    <label for="desc">Description:</label>
                                    <textarea name="desc" id="desc"></textarea><br />

                                    <label for="nrat">Normal Rate:</label>
                                    <input type="text" name="nrat" id="nrat" value="0" /><br />

                                    <label for="nrtu">Normal Rate Unit:</label>
                                    <input type="text" name="nrtu" id="nrtu" value="" maxlength="10"/><br />

                                    <label for="ovrt">Overcharge Rate:</label>
                                    <input type="text" name="ovrt" id="ovrt" value="0" /><br />

                                    <label for="ovu">Overcharge Unit:</label>
                                    <input type="text" name="ovu" id="ovu" value="" maxlength="10"/><br />

                                    <label for="stim">Start Time:</label>
                                    <input type="text" name="stim" id="stim" value="00:00:00" /><br />

                                    <label for="etim">End Time:</label>
                                    <input type="text" name="etim" id="etim" value="" /><br />

                                    <input type="submit" name="submitbutton" id="submitbutton" value="Submit" />
                                    <a href="fac_hall_manage.jsp">  Cancel  </a>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Facilities Management</h2><br />
                                </div>
                                <ul>
                                    <li>ROOMS
                                        <ul>
                                            <li><a href="fac_hall_add.jsp">Add New Room</a></li>
                                            <li><a href="fac_room_manage.jsp">Manage Room</a></li>
                                        </ul>
                                    </li>
                                    <li>MEETINGS & EVENTS
                                        <ul>
                                            <li><a href="#">Add New Packet</a></li>
                                            <li class="curtab"><a href="#">Manage Packet</a></li>
                                        </ul>
                                    </li>
                                    <li>OTHER SERVICES
                                        <ul>
                                            <li><a href="#">Add New Services</a></li>
                                            <li><a href="fac_serv_manage.jsp">Manage Services</a></li>
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
