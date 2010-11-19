<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.RoomReservationJpaController" %>
<%@ page import="AvatarEntity.RoomReservation" %>
<%@ page import="AvatarEntity.CustomerJpaController" %>
<%@ page import="AvatarEntity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 1){
%>
<%! RoomReservationJpaController cr = new RoomReservationJpaController();
    List<RoomReservation> lr = cr.findRoomReservationEntities();

   /* CustomerJpaController sr = new CustomerJpaController();
    List<Customer> kir = sr.findCustomerEntities(); */
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
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
                        <h1 class="title">List of Room Reservations</h1>
                        <div class="post">
                            <table width="603" border="1" style="table-layout:fixed">
                                <tr>
                                    <th bgcolor="#262626" width="29">No.</th>
                                    <th bgcolor="#262626" width="179">Username</th>
                                    <th bgcolor="#262626" width="89">No. Kamar</th>
                                    <th bgcolor="#262626" width="77">Tgl Masuk</th>
                                    <th bgcolor="#262626" width="96">Tgl Keluar</th>

                                </tr>

                                <%
                                            out.write("tes masuk 1");
                                                int editIndex = 0;
                                            try {
                                                String Index = request.getParameter("edit");
                                                editIndex = Integer.parseInt(Index);
                                            } catch (NullPointerException ex) {
                                                editIndex = -1;
                                            } catch (NumberFormatException ex) {
                                                editIndex = -1;
                                            }

                                            int index = 0;
                                            RoomReservationJpaController jpr = new RoomReservationJpaController();
                                            List<RoomReservation> roomList = jpr.findRoomReservationEntities();

                                         /*   CustomerJpaController jpc = new CustomerJpaController();
                                            List<Customer> cusList = jpc.findCustomerEntities();
                                            Iterator<Customer> cur = kir.iterator();
                                                if (cur.hasNext()) {out.write(" cur has next ");} else {out.write(" cur no next ");}
                                         */
                                            if (editIndex == -1) {
                                                out.write("tes masuk 2");
                                                
                                                out.write("tes masuk 3");

                                                Iterator<RoomReservation> ur = roomList.iterator();
                                                if (ur.hasNext()) {out.write(" has next ");} else {out.write(" no next ");}

                                                for (Iterator<RoomReservation> ir = lr.iterator(); ir.hasNext();) {
                                                    out.write("tes masuk 4");
                                                    RoomReservation temp = ir.next();
                                                    index++;
                                %>
                                <tr>
                                    <td><%=index %></td>
                                    <td><div style="overflow:auto"><%= temp.getReservationItemId() %></div></td>
                                    <td><div style="overflow:auto"><%= temp.getRoomNo().toString() %></div></td>
                                    <td> <%= temp.getEntryDate().toString() %> </td>
                                    <td> <%= temp.getExitDate().toString() %> </td>
                                    <td><a href="?edit=<%=index%>">edit</a></td>
                                    <td><a href="HapusRoomReservasi?delete=<%= temp.getReservationItemId() %>">delete</a></td>
                                </tr>
                                <% }
                                                }
            else
            {
                int iterator=0;
                for(Iterator<RoomReservation> ir = roomList.iterator(); ir.hasNext();)
                {
                    RoomReservation temp=ir.next();
                    iterator++;

                %>
                <tr><td><%=iterator%></td>
                <%
                if(iterator==editIndex){
                 %>

                 <form action="EditCustomer" method="get">
                     <td><input type="text" name="username" id="username" disabled="true" value="<%= temp.getReservationItemId() %>"></td>
                     <td><input type="text" name="name" id="name" value="<%=temp.getRoomNo().toString() %>"> </td>
                     <td><input type="text" name="itype" id="itype" value="<%=temp.getEntryDate().toString()%>"></td>
                     <td><input type="text" name="inumber" id="inumber" value="<%=temp.getExitDate().toString() %>"></td>
                     <td><input type="submit" value="Save" onclick="this.form.username.disabled=false;"/> </td>
                 </form>
                <td><a href="HapusRoomReservasi?delete=<%= temp.getReservationItemId() %>"> delete</a></td>
                <td><a href="ManageCustomer.jsp"> cancel </a></td>



                 <% }else{%>

       <td><div style="overflow:auto"><%= temp.getReservationItemId()%></div></td>
                                    <td><div style="overflow:auto"><%= temp.getRoomNo().toString() %></div></td>
                                    <td> <%= temp.getEntryDate().toString()%> </td>
                                    <td> <%= temp.getExitDate().toString()%> </td>
                                    <td><a href="?edit=<%=iterator%>">edit</a></td>
                                    <td><a href="HapusRoomReservasi?delete=<%= temp.getReservationItemId() %>">delete</a></td>

           <%}}}%>

                            </table>
                            <h2 class="title">&nbsp;</h2>
                            <div class="post"></div>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="resv_sidebar.jsp" flush="true"/>
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
<%}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>
