<%-- 
    Document   : statistic
    Created on : 05 Nov 10, 23:15:14
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                        <h1 class="title">Statistics of Services</h1>
                        <form method="post" name="statform" id="statform" action="statistic">
                            <table border="0">
                                <tr>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Statistic Type</th>
                                </tr>
                                <tr>
                                    <td><input type="text" name="from" id="from" size="10" maxlength="10" /></td>
                                    <td><input type="text" name="to" id="to" size="10" maxlength="10" /></td>
                                    <td><select name="specify" id="specify">
                                            <option value="">All</option>
                                            <option value="room">Room</option>
                                            <option value="hall">Hall</option>
                                            <option value="other">Others</option>
                                        </select></td>
                                </tr>
                                <tr><td colspan="3">
                                        <input type="submit" name="view" id="view" value="View" />
                                    </td></tr>
                            </table>
                        </form>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Statistics</h2><br />
                                </div>
                                <ul>
                                    <li>VIEWS
                                        <ul>
                                            <li><a href="statistic">Current Statistics</a></li>
                                            <li><a href="statistic_selector.jsp">Switch View</a></li>
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
