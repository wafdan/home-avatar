
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page language="java" import="java.sql.*" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 2){
%>
<%
Locale locale = Locale.getDefault();
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
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
                        <h1 class="title">Hall List</h1>
                        <ul id="fmenu">
                            <li id="fmenu-item1"><a href="fac_room_manage.jsp">Rooms</a></li>
                            <li id="fmenu-item2"><a href="#">Meetings & Events</a></li>
                            <li id="fmenu-item3"><a href="fac_serv_manage.jsp">Other Services</a></li>
                        </ul>
                        <div class="post">
                            <div class="fac1">
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
                                HallJpaController jpah = new HallJpaController();
                                List<Hall> hList = jpah.findHallEntities();
                                if (editIndex == -1) {
                                    %>
                                <tr>
                                    <th bgcolor="#262626" width="20">No.</th>
                                    <th bgcolor="#262626" width="50">Product Id</th>
                                    <th bgcolor="#262626" width="89">Product Type</th>
                                    <th bgcolor="#262626" width="100">Description</th>
                                    <th bgcolor="#262626" width="96">Normal Rate</th>
                                    <th bgcolor="#262626" width="96">Start Time</th>
                                    <th bgcolor="#262626" width="96">End Time</th>
                                    <th bgcolor="#262626" width="96">Overcharge Rate</th>
                                    <th colspan="2" bgcolor="#262626"></th>
                                </tr>
                                <%for (Iterator<Hall> i = hList.iterator(); i.hasNext();) {
                                            Hall temp = i.next();
                                %>
                                <tr>
                                    <td style="vertical-align: 0%"><%index++;out.write(Integer.toString(index));%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getProductId());%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getProductType());%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getDescription());%></td>
                                    <td style="vertical-align: 0%"><% out.write(currencyFormat.format(temp.getNormalRate()) + " per " + temp.getNormalRateUnit());%></td>
                                    <td style="vertical-align: 0%"><% out.write(String.valueOf(temp.getStartTime().getHours()));%> : <% out.write(String.valueOf(temp.getStartTime().getMinutes()));%></td>
                                    <td style="vertical-align: 0%"><% out.write(String.valueOf(temp.getEndTime().getHours()));%> : <% out.write(String.valueOf(temp.getEndTime().getMinutes()));%></td>
                                    <td style="vertical-align: 0%"><% out.write(currencyFormat.format(temp.getOverchargeRate()) + " per " + temp.getOverchargeUnit());%></td>
                                    <td style="vertical-align: 0%;width:20px;" align="center"><a href="fac_hall_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></td>
                                    <td style="vertical-align: 0%;width:20px;" align="center"><a onclick="return confirmAction()" href="HapusHall?delete=<% out.write(temp.getProductId());%>">delete</a></td>
                                </tr>
                                <%}
                                } else {
                                    int iterator = 0;
                                    for (Iterator<Hall> i = hList.iterator(); i.hasNext();) {
                                        Hall temp = i.next();
                                        iterator++;
                                        index++;
                                        if(iterator == editIndex){%>
                                        <form action="EditHall" method="post">
                                            <tr>
                                                <th bgcolor="#262626" width="20%">No</th>
                                                <th bgcolor="#262626" width="80%"><%out.write(Integer.toString(index));%></th>
                                            </tr>
                                            <tr>
                                                <td>Product ID :</td>
                                                <td><input type="text" value= "<%= temp.getProductId()%>" id="pid" name="pid" disabled="true" maxlength="6"/></td>
                                            </tr>
                                            <tr>
                                                <td>Package Name :</td>
                                                <td><input type="text" value= "<%= temp.getProductType()%>" id="type" name="type" maxlength="25"/></td>
                                            </tr>
                                            <tr>
                                                <td>Description  :</td>
                                                <td><textarea id="desc" name="desc" cols=70% rows=3><%= temp.getDescription()%></textarea></td>
                                            </tr>
                                            <tr>
                                                <td>Normal Rate  :</td>
                                                <td><input type="text" value= "<%= temp.getNormalRate()%>" id="nrat" name="nrat" /></td>
                                            </tr>
                                            <tr>
                                                <td>Normal Rate Unit  :</td>
                                                <td><input type="text" value= "<%= temp.getNormalRateUnit()%>" id="nrtu" name="nrtu" maxlength="10"/></td>
                                            </tr>
                                            <tr>
                                                <td>Overcharge Rate  :</td>
                                                <td><input type="text" value= "<%= temp.getOverchargeRate()%>" id="ovrt" name="ovrt" /></td>
                                            </tr>
                                            <tr>
                                                <td>Overcharge Rate Unit  :</td>
                                                <td><input type="text" value= "<%= temp.getOverchargeUnit()%>" id="ovu" name="ovu" maxlength="10"/></td>
                                            </tr>
                                            <tr>
                                                <td>Start Time  :</td>
                                                <td>
                                                    <input type="text" maxlength="2" style="width: 20px" value= "<%= temp.getStartTime().getHours()%>" id="stim" name="stim" />
                                                    <input type="text" maxlength="2" style="width: 20px" value= "<%= temp.getStartTime().getMinutes()%>" id="stim2" name="stim2" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>End Time  :</td>
                                                <td>
                                                    <input type="text" maxlength="2" style="width: 20px" value= "<%= temp.getEndTime().getHours()%>" id="etim" name="etim" />
                                                    <input type="text" maxlength="2" style="width: 20px" value= "<%= temp.getEndTime().getMinutes()%>" id="etim2" name="etim2" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                </td>
                                                <td>
                                                    <input type="submit" value="save" onclick="this.form.pid.disabled=false;" />
                                                    <a onclick="return confirmAction()" href="HapusHall?delete=%3C%%20out.write(temp.getProductId());%%3E">
                                                        delete</a>
                                                    <a href="fac_hall_manage.jsp">
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
                    <jsp:include page="fac_sidebar.jsp" />
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