<%--
    Document   : fac_hall_add
    Created on : 21 Okt 10, 13:59:12
    Author     : TOSHIBA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page language="java" import="java.sql.*" %>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 2){
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <link href="../styles/timePicker.css" rel="stylesheet" type="text/css" />
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
        <%--<script type="text/javascript" src="../script/jquery.js"></script>
        <script type="text/javascript" src="../script/jquery.timePicker.js"></script>
        <script type="text/javascript" src="../script/jquery.timePicker.min.js"></script>--%>
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
                        <h1 class="title">Add Room</h1>
                        <div class="post">
                            <div class="fac1">
                                <form action="TambahAcco" method="post">
                                    <label for="pid">Product ID</label>
                                    <input type="text" name="pid" id="pid" value="" maxlength="6"/><br />

                                    <label for="type">Room Type Name:</label>
                                    <input type="text" name="type" id="type" value="" maxlength="25"/><br />

                                    <label for="desc">Description:</label>
                                    <textarea name="desc" id="desc"></textarea><br />

<!--                                    <label for="img">Image:</label>-->
                                    <input type="hidden" name="img" id="img" value="" /><br />

                                    <label for="max">Max Pax:</label>
                                    <input type="text" name="max" id="max" value="0" maxlength="11"/><br />

                                    <label for="nent">Normal Entry:</label>
                                    <input type="text" name="nent" id="nent" value="00" maxlength="2" style="width: 20px" /> :
                                    <input type="text" name="nent2" id="nent2" value="00" maxlength="2" style="width: 20px" />
                                    <br />
                                    <label for="noxt">Normal Exit:</label>
                                    <input type="text" name="noxt" id="noxt" value="00" maxlength="2" style="width: 20px" /> :
                                    <input type="text" name="noxt2" id="noxt2" value="00" maxlength="2" style="width: 20px" />
                                    <br />

                                    <label for="wday">Weekday Rate:</label>
                                    <input type="text" name="wday" id="wday" value="0" /><br />

                                    <label for="wend">Weekend Rate:</label>
                                    <input type="text" name="wend" id="wend" value="0" /><br />

                                    <label for="terl">Tolerance Early:</label>
                                    <input type="text" name="terl" id="terl" value="00" maxlength="2" style="width: 20px" /> :
                                    <input type="text" name="terl2" id="terl2" value="00" maxlength="2" style="width: 20px" />
                                    <br />

                                    <label for="tlat">Tolerance Late:</label>
                                    <input type="text" name="tlat" id="tlat" value="00" maxlength="2" style="width: 20px" /> :
                                    <input type="text" name="tlat2" id="tlat2" value="00" maxlength="2" style="width: 20px" />
                                    <br />
                                    <input type="submit" name="submitbutton" id="submitbutton" value="Submit" />
                                    <a href="fac_room_manage.jsp">  Cancel  </a>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="fac_sidebar.jsp" />
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
<%
}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>