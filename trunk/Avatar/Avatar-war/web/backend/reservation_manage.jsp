<%--
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
    Modifier   : Wafdan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Customer" %>
<%@page import="AvatarEntity.CustomerJpaController" %>

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
<%
Locale locale = Locale.getDefault();
NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
ReservationJpaController jpar = new ReservationJpaController();
List<Reservation> rList = jpar.findReservationEntities();
ReservationItemJpaController jpari = new ReservationItemJpaController();
List<ReservationItem> riList = jpari.findReservationItemEntities();
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/backend_facilities.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../jquery/jquery_hoo.js"></script>
        <script type="text/javascript">
            function confirmAction()
            {return confirm("Do you really want to delete?")}
        </script>
        <style>
 table.detail, table.detail td, table.detail th {
         border-collapse:collapse;
         border:1px solid white;
 }
 table.detail tr.parent {
         font-weight:bold;
 }
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
                        <div class="post">
                            <h1 class="title">Reservation Management</h1>
                            <form method="post" name="addResv" id="addResv" action="resv_add">
                                <h2>Add Reservation</h2>
                                <table border="0">
                                    <tr>
                                        <th>Username</th>
                                        <th>Is On Spot</th>
                                        <th>Note</th>
                                    </tr>
                                    <tr>
                                        <td><input type="text" name="uname" id="uname" size="20" maxlength="25" /></td>
                                        <td>
                                            <input type="radio" name="ison" id="ison" value="true"/>Yes<br/>
                                            <input type="radio" name="ison" id="ison" value="false"  checked="true"/>No

                                        </td>
                                        <td><textarea name="description" id="description" cols="25" rows="2"></textarea></td>
                                    </tr>
                                </table><br/>

                                <h2>Reservation List</h2>
                                <script>
                                    $(function() {
                                        $('tr.parent')
                                        .css("cursor","pointer")
                                        .attr("title","Click to expand/collapse")
                                        .click(function(){
                                            $(this).siblings('.child-'+this.id).toggle();
                                        });
                                        $('tr[@class^=child-]').hide().children('td');
                                    });
                                    $(function(){
                                        $('tr[@class^=child-]')
                                        .css("cursor","pointer")
                                        .attr("title","Click to expand/collapse")
                                        .click(function(){
                                            $(this).siblings('.child2-'+this.id).toggle();
                                        });
                                        $('tr[@class^=child2-]').hide().children('td');
                                    });
                                </script>
                                <table id="detail_table" border="1"class="detail" cellpadding="3">
                                    <col style="width: 20px;"/>
                                    <col style="width: 20px;"/>
                                    <col style="width: 20px;"/>
                                    <col style="width: 60px;"/>
                                    <col style="width: 40px;"/>
                                    <col style="width: 90px;"/>
                                    <thead>
                                    <tr>
                                        <%--<th bgcolor="#262626"></th>--%>
                                        <th bgcolor="#262626">Reservation Id</th>
                                        <th bgcolor="#262626">Is Onspot</th>
                                        <th bgcolor="#262626">Username</th>
                                        <th bgcolor="#262626" colspan="2">Note</th>
                                        <th bgcolor="#262626">Action</th>
                                    </tr>
                                    </thead>
                                <%
                                for (Reservation resv: rList){
                                %>
                                <tr class="parent" id="row<%= resv.getReservationId()%>">
                                    <%--<td><a href="#" style="font-size: large">+</a></td>--%>
                                    <td><%= resv.getReservationId()%></td>
                                    <td><%= resv.getIsOnspot()%></td>
                                    <td><%= resv.getUsername().getUsername()%></td>
                                    <td colspan="2"><%= resv.getNote()%></td>
                                    <td>
                                        <a href="HapusResv?rid=<%= resv.getReservationId()%>">delete</a> |
                                        <a href="EditResv?rid=<%= resv.getReservationId()%>">edit</a>
                                    </td>
                                </tr>
                                <tr class="child-row<%= resv.getReservationId()%>">
                                    <td>&nbsp;</td>
                                    <td bgcolor="#262626">Item Id</td>
                                    <td bgcolor="#262626">Reservation Time</td>
                                    <td bgcolor="#262626">Price</td>
                                    <td bgcolor="#262626">DTYPE</td>
                                    <td bgcolor="#262626">Action</td>
                                </tr>
                                <%
                                for (ReservationItem resvi: riList){
                                if(resvi.getReservationId().equals(resv)){
                                %>
                                <tr class="child-row<%= resv.getReservationId()%>">
                                    <td>&nbsp;</td>
                                    <td><%= resvi.getReservationItemId()%></td>
                                    <td><%= resvi.getReservationTime()%></td>
                                    <td><%= currencyFormat.format(resvi.getPrice())%></td>
                                    <td>DTYPE</td>
                                    <td>
                                        <a href="#">edit</a>|
                                        <a href="#">delete</a>
                                    </td>
                                </tr>
                                <tr class="child2-row<%= resvi.getReservationItemId()%>">
                                    <td>&nsbp;</td>
                                    <td>HOOOOOO</td>
                                </tr>
                                <%}
                                }%>
                                <%}%>
                                </table>
                            </form>
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

