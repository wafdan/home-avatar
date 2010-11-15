<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>

<%@page import="AvatarEntity.RoomReservation" %>
<%@page import="AvatarEntity.RoomReservationJpaController" %>
<%@page import="AvatarEntity.HallReservation" %>
<%@page import="AvatarEntity.HallReservationJpaController" %>
<%@page import="AvatarEntity.OtherServicesReservation" %>
<%@page import="AvatarEntity.OtherServicesReservationJpaController" %>
<%@page import="AvatarEntity.Reservation" %>
<%@page import="AvatarEntity.ReservationJpaController" %>
<%@page import="AvatarEntity.ReservationItem" %>
<%@page import="AvatarEntity.ReservationItemJpaController" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/backend_facilities.css" rel="stylesheet" type="text/css" />
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
                        <h1 class="title">Reservation List</h1>
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
                                try {
                                    String Index = request.getParameter("edit");
                                    editIndex = Integer.parseInt(Index);
                                } catch (NullPointerException ex) {
                                    editIndex = -1;
                                } catch (NumberFormatException ex) {
                                    editIndex = -1;
                                }

                                int index = 0;
                                ReservationJpaController jpar = new ReservationJpaController();
                                List<Reservation> rList = jpar.findReservationEntities();
                                if (editIndex == -1) {
                                    %>
                                <tr>
                                    <th bgcolor="#262626" width="20">No.</th>
                                    <th bgcolor="#262626" width="50">Reservation Id</th>
                                    <th bgcolor="#262626" width="89">Is Onspot</th>
                                    <th bgcolor="#262626" width="100">Username</th>
                                    <th bgcolor="#262626" width="96">Note</th>
                                    <th colspan="2" bgcolor="#262626"></th>
                                </tr>
                                <%for (Iterator<Reservation> i = rList.iterator(); i.hasNext();) {
                                            Reservation temp = i.next();
                                %>
                                <tr>
                                    <td style="vertical-align: 0%"><%index++;out.write(Integer.toString(index));%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getReservationId());%></td>
                                    <td style="vertical-align: 0%"><% out.write(String.valueOf(temp.getIsOnspot()));%></td>
                                    <td style="vertical-align: 0%"><% out.write(temp.getNote());%></td>
                                    <td style="vertical-align: 0%;width:20px;" align="center"><a href="reservation_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></td>
                                    <td style="vertical-align: 0%;width:20px;" align="center"><a onclick="return confirmAction()" href="HapusHall?delete=<% out.write(temp.getReservationId());%>">delete</a></td>
                                </tr>
                                <%}
                                } else {
                                    int iterator = 0;
                                    for (Iterator<Reservation> i = rList.iterator(); i.hasNext();) {
                                        Reservation temp = i.next();
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
                                                <td><input type="text" value= "<%= temp.getReservationId()%>" id="pid" name="pid" disabled="true" /></td>
                                            </tr>
                                            <tr>
                                                <td>Package Name :</td>
                                                <td><input type="text" value= "<%= temp.getUsername()%>" id="type" name="type" /></td>
                                            </tr>
                                            <tr>
                                                <td>Description  :</td>
                                                <td><textarea id="desc" name="desc" cols=70% rows=3><%= temp.getNote()%></textarea></td>
                                            </tr>
                                                <td>
                                                    <input type="submit" value="save" onclick="this.form.pid.disabled=false;" />
                                                    <a onclick="return confirmAction()" href="HapusHall?delete=%3C%%20out.write(temp.getProductId());%%3E">
                                                        delete</a>
                                                    <a href="fac_hall_manage.jsp">
                                                        cancel</a>
                                                </td>
                                        </form>

                                       <%}else{%>
                                       <tr>
                                           <td style="vertical-align: 0%"><%index++;
                                                                                out.write(Integer.toString(index));%></td>
                                           <td style="vertical-align: 0%"><% out.write(temp.getReservationId());%></td>
                                           <td style="vertical-align: 0%"><% out.write(String.valueOf(temp.getIsOnspot()));%></td>
                                           <td style="vertical-align: 0%"><% out.write(temp.getNote());%></td>
                                           <td style="vertical-align: 0%;width:20px;" align="center"><a href="reservation_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></td>
                                           <td style="vertical-align: 0%;width:20px;" align="center"><a onclick="return confirmAction()" href="HapusHall?delete=<% out.write(temp.getReservationId());%>">delete</a></td>
                                       </tr>
                                       <%}
                                    }
                                }%>
                            </table>
                            </div>
                        </div>
                    </div>
                    <!-- end content -->
                    <jsp:include page="resv_sidebar.jsp" />
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

