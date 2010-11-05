
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page language="java" import="java.sql.*" %>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
<%@page import="AvatarEntity.Profile" %>
<%@page import="AvatarEntity.ProfileJpaController" %>
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
        </style>
        <%--<script type="text/javascript" src="../script/backendHeader.js" />--%>
        <script type="text/javascript">
            function confirmAction()
            {return confirm("Do you really want to delete?")}
            function DoubleScroll(element) {
                var scrollbar= document.createElement('div');
                scrollbar.appendChild(document.createElement('div'));
                scrollbar.style.overflow= 'auto';
                scrollbar.style.overflowY= 'hidden';
                scrollbar.firstChild.style.width= element.scrollWidth+'px';
                scrollbar.firstChild.style.paddingTop= '1px';
                scrollbar.firstChild.appendChild(document.createTextNode('\xA0'));
                scrollbar.onscroll= function() {
                    element.scrollLeft= scrollbar.scrollLeft;
                };
                element.onscroll= function() {
                    scrollbar.scrollLeft= element.scrollLeft;
                };
                element.parentNode.insertBefore(scrollbar, element);
            }

            DoubleScroll(document.getElementById('doublescroll'));
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
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper" style="width: auto">
            <div id="wrapper-btm">
                <div id="page" style="width: auto;">
                    <!-- start content -->
                    <div id="content" style="width: 980px; overflow: visible;">
                        <h1 class="title">Daftar Fasilitas</h1>
                        <ul id="fmenu">
                            <li id="fmenu-item1"><a href="#">Rooms</a></li>
                            <li id="fmenu-item2"><a href="fac_hall_manage.jsp">Meetings & Events</a></li>
                            <li id="fmenu-item3"><a href="fac_serv_manage.jsp">Other Services</a></li>
                        </ul>
                        <div class="post" style="overflow: scroll;">
                            <div class="fac1">
                            Accomodations<br/>
                            <%--<table width="603" border="1" style="table-layout:fixed">--%>
                            <table align = "center" border = 1 width = "100%" cellpadding = "3" cellspacing = "0">
                                <%
                                int editIndex=0;
                                String Fac = request.getParameter("Fac");
                                try {
                                    String Index = request.getParameter("edit");
                                    editIndex = Integer.parseInt(Index);
                                } catch (NullPointerException ex) {
                                    editIndex = -1;
                                } catch (NumberFormatException ex) {
                                    editIndex = -1;
                                }
                                
                                int index = 0;
                                AccomodationJpaController jpa = new AccomodationJpaController();
                                List<Accomodation> accList = jpa.findAccomodationEntities();
                                if (editIndex == -1) {%>
                                    <tr>
                                    <th bgcolor="#262626">No.</th>
                                    <th bgcolor="#262626">Product Id</th>
                                    <th bgcolor="#262626" width="89">Product Type</th>
                                    <th bgcolor="#262626" width="100">Description</th>
                                    <th bgcolor="#262626" width="96">Image</th>
                                    <th bgcolor="#262626" width="20">Max Pax</th>
                                    <th bgcolor="#262626" width="40">Normal Entry</th>
                                    <th bgcolor="#262626" width="40">Normal Exit</th>
                                    <th bgcolor="#262626" width="40">Weekday Rate</th>
                                    <th bgcolor="#262626" width="40">Weekend Rate</th>
                                    <th bgcolor="#262626" width="40">Tolerance Early</th>
                                    <th bgcolor="#262626" width="40">Tolerance Late</th>
                                    <th bgcolor="#262626" width="30" colspan="2"></th>
                                </tr><%
                                            for (Iterator<Accomodation> i = accList.iterator(); i.hasNext();) {
                                            Accomodation temp = i.next();
                                %>
                                
                                <tr>
                                    <td><%index++;out.write(Integer.toString(index));%></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getProductId());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getProductType());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getDescription());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getImage());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getMaxPax());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(String.valueOf(temp.getNormalEntry().getHours()));%>:<% out.write(String.valueOf(temp.getNormalEntry().getMinutes()));%></div></td>
                                    <td><div style="overflow:auto"><% out.write(String.valueOf(temp.getNormalExit().getHours()));%>:<% out.write(String.valueOf(temp.getNormalExit().getMinutes()));%></div></td>
                                    <td><div style="overflow:auto"><% out.write(String.valueOf(temp.getWeekdayRate()));%></div></td>
                                    <td><div style="overflow:auto"><% out.write(String.valueOf(temp.getWeekendRate()));%></div></td>
                                    <td><div style="overflow:auto"><% out.write(String.valueOf(temp.getToleranceEarly().getHours()));%>:<% out.write(String.valueOf(temp.getToleranceEarly().getMinutes()));%></div></td>
                                    <td><div style="overflow:auto"><% out.write(String.valueOf(temp.getToleranceLate().getHours()));%>:<% out.write(String.valueOf(temp.getToleranceLate().getMinutes()));%></div></td>

                                    <td align="center"><a href="fac_room_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></td>
                                    <td align="center"><a onclick="return confirmAction()" href="HapusAcco?delete=<% out.write(temp.getProductId());%>">delete</a></td>
                                </tr>
                                <%}
                                } else{
                                    int iterator = 0;
                                    for (Iterator<Accomodation> i = accList.iterator(); i.hasNext();) {
                                            Accomodation temp = i.next();
                                            iterator++;
                                            index++;
                                            if(iterator == editIndex){%>
                                            <%--<form action="../MengelolaLayanan/EditAcco?pid=<%= temp.getProductId()%>" method="post">--%>
                                            <form action="EditAcco" method="post">
                                                <tr>
                                                    <th bgcolor="#262626" width="20%">No</th>
                                                    <th bgcolor="#262626" width="80%"><%out.write(Integer.toString(index));%></th>
                                                </tr>
                                                <tr>
                                                    <td>Product Id   :</td>
                                                    <td><input type="text" value= "<%= temp.getProductId()%>" id="pid" name="pid" disabled="true" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Product Type :</td>
                                                    <td><input type="text" value= "<%= temp.getProductType()%>" id="type" name="type" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Description  :</td>
                                                    <td><textarea id="desc" name="desc" cols=80% rows=3><%= temp.getDescription()%></textarea></td>
                                                </tr>
                                                <tr>
                                                    <td>Image        :</td>
                                                    <td><input type="text" value= "<%= temp.getImage()%>" id="img" name="img" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Max Pax:</td>
                                                    <td><input type="text" name="max" id="max" value="<%= temp.getMaxPax()%>" maxlength="11"/><br /></td>
                                                </tr>
                                                <tr>
                                                    <td>Normal Entry:</td>
                                                    <td>
                                                        <input type="text" name="nent" id="nent" value="<%= temp.getNormalEntry().getHours()%>" maxlength="2" style="width: 20px" /> :
                                                        <input type="text" name="nent2" id="nent2" value="<%= temp.getNormalEntry().getMinutes()%>" maxlength="2" style="width: 20px" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Normal Exit:</td>
                                                    <td>
                                                        <input type="text" name="noxt" id="noxt" value="<%= temp.getNormalExit().getHours()%>" maxlength="2" style="width: 20px" /> :
                                                        <input type="text" name="noxt2" id="noxt2" value="<%= temp.getNormalExit().getMinutes()%>" maxlength="2" style="width: 20px" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Weekday Rate:</td>
                                                    <td>
                                                        <input type="text" name="wday" id="wday" value="<%= temp.getWeekdayRate()%>" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Weekend Rate:</td>
                                                    <td>
                                                        <input type="text" name="wend" id="wend" value="<%= temp.getWeekendRate()%>" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Tolerance Early:</td>
                                                    <td>
                                                        <input type="text" name="terl" id="terl" value="<%= temp.getToleranceEarly().getHours()%>" maxlength="2" style="width: 20px" /> :
                                                        <input type="text" name="terl2" id="terl2" value="<%= temp.getToleranceEarly().getMinutes()%>" maxlength="2" style="width: 20px" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Tolerance Late:</td>
                                                    <td>
                                                        <input type="text" name="tlat" id="tlat" value="<%= temp.getToleranceLate().getHours()%>" maxlength="2" style="width: 20px" /> :
                                                        <input type="text" name="tlat2" id="tlat2" value="<%= temp.getToleranceLate().getMinutes()%>" maxlength="2" style="width: 20px" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                    </td>
                                                    <td>
                                                        <input type="submit" value="save" onclick="this.form.pid.disabled=false;" />
                                                        <a onclick="return confirmAction()" href="HapusAcco?delete=%3C%%20out.write(temp.getProductId());%%3E">
                                                            delete</a>
                                                        <a href="fac_room_manage.jsp">
                                                            cancel</a>
                                                    </td>
                                                </tr>
                                            </form>
                                            <%    
                                            }
                                %>
                                
                                <%  }
                                }%>
                            </table>
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
