
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page language="java" import="java.sql.*" %>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
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
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Services List</h1>
                        <ul id="fmenu">
                            <li id="fmenu-item1"><a href="fac_room_manage.jsp">Rooms</a></li>
                            <li id="fmenu-item2"><a href="fac_hall_manage.jsp">Meetings & Events</a></li>
                            <li id="fmenu-item3"><a href="#">Other Services</a></li>
                        </ul>
                        <div class="post">
                            <div class="fac1">
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
                                OtherServicesJpaController jpah = new OtherServicesJpaController();
                                List<OtherServices> hList = jpah.findOtherServicesEntities();
                                if (editIndex == -1) {
                                    %>
                                <tr>
                                    <th bgcolor="#262626" width="20">No.</th>
                                    <th bgcolor="#262626" width="50">Product Id</th>
                                    <th bgcolor="#262626" width="89">Product Type</th>
                                    <th bgcolor="#262626" width="100">Description</th>
                                    <th bgcolor="#262626" width="96">Image</th>
                                </tr>
                                <%for (Iterator<OtherServices> i = hList.iterator(); i.hasNext();) {
                                            OtherServices temp = i.next();
                                %>
                                <tr>
                                    <td><%index++;out.write(Integer.toString(index));%></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getProductId());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getProductType());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getDescription());%></div></td>
                                    <td><div style="overflow:auto"><% out.write(temp.getImage());%></div></td>
                                    <td align="center"><div style="overflow:auto"><a href="fac_serv_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></div></td>
                                    <td align="center"><div style="overflow:auto"><a onclick="return confirmAction()" href="HapusServ?delete=<% out.write(temp.getProductId());%>">delete</a></div></td>
                                </tr>
                                <%}
                                } else {
                                    int iterator = 0;
                                    for (Iterator<OtherServices> i = hList.iterator(); i.hasNext();) {
                                        OtherServices temp = i.next();
                                        iterator++;
                                        index++;
                                        if(iterator == editIndex){%>
                                        <form action="EditServ" method="post">
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
                                                <td>Image  :</td>
                                                <td><input type="text" value= "<%= temp.getImage()%>" id="img" name="img" /></td>
                                            </tr>
                                            <tr>
                                                <td>Pricing Unit :</td>
                                                <td><input type="text" value= "<%= temp.getPricingUnit()%>" id="prcu" name="prcu" /></td>
                                            </tr>
                                            <tr>
                                                <td>Unit Price :</td>
                                                <td><input type="text" value= "<%= temp.getUnitPrice()%>" id="uprc" name="uprc" /></td>
                                            </tr>
                                            <tr>
                                                <td>Published :</td>
                                                <td>
                                                <% if (temp.getPublished()){%>
                                                    <input style="width:20px" type="radio" name="pub" id="pub" value="true" checked="checked" />Yes
                                                    <input style="width:20px" type="radio" name="pub" id="pub" value="false" />No
                                                <%}else{%>
                                                    <input style="width:20px" type="radio" name="pub" id="pub" value="true" />Yes
                                                    <input style="width:20px" type="radio" name="pub" id="pub" value="false" checked="checked"/>No
                                                <%}%>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                </td>
                                                <td>
                                                    <input type="submit" value="save" onclick="this.form.pid.disabled=false;" />
                                                    <a onclick="return confirmAction()" href="HapusServ?delete=%3C%%20out.write(temp.getProductId());%%3E">
                                                        delete</a>
                                                    <a href="fac_serv_manage.jsp">
                                                        cancel</a>
                                                </td>
                                            </tr>
                                        </form>

                                       <%}
                                    }
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
