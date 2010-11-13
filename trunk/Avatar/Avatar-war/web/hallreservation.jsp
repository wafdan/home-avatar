<%--
    Document   : hallreservation
    Created on : 07 Nov 10, 10:07:05
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Layout" %>
<%@page import="AvatarEntity.LayoutJpaController" %>
<%@page import="AvatarEntity.Hall" %>
<%@page import="AvatarEntity.HallJpaController" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List" %>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="Layanan.*" %>
<%@page import="AvatarEntity.Accomodation"%>
<%@page import="AvatarEntity.Hall"%>
<%@page import="AvatarEntity.HallJpaController"%>
<%@page import="AvatarEntity.AccomodationJpaController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="Pemesanan.CartSessionBeanLocal"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="Pemesanan.*" %>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Locale locale = Locale.getDefault();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
    <head profile="http://gmpg.org/xfn/11">
        <%
                    ProfileJpaController pjc = new ProfileJpaController();
                    Profile p = pjc.findProfile(Boolean.TRUE);
        %>
        <title><%=p.getHotelName()%> - Reservation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="imagetoolbar" content="no" />
        <link rel="stylesheet" href="styles/layout.css" type="text/css" />
        <script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="jquery/jqueryui.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/jquerystyle.css" />
        <script type="text/javascript">
            $(function(){
                // Datepicker
                $('.datepicker').datepicker({
                    inline: true
                });
                //hover states on the static widgets
                $('#dialog_link, ul#icons li').hover(
                function() { $(this).addClass('ui-state-hover'); },
                function() { $(this).removeClass('ui-state-hover'); }
            );
            });

            var dateValid=false;
            var capacityValid=false;

            var validateDate=function(){
                var regex=/^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$/;
                var target=document.syalala.reservationdate.value;
                if(!regex.test(target)){
                    alert("Date format must be MM/dd/yyyy");
                    dateValid=false;
                    return false;
                }
                else{
                    dateValid=true;
                    getTotalHallAvailable();
                }
            }

            var validateCapacity=function(){
                var target=document.syalala.capacity.value;
                if(isNaN(target)){
                    alert("Capacity should be number between 0 and 999");
                    return false;
                }else if (parseInt(target)>999){
                    alert("Capacity should be number between 0 and 999");
                }else{
                    capacityValid=true;
                    getTotalHallAvailable();
                }
            }

            var validateLayout=function(){
                if(document.syalala.layout.value=="notchoosed"){
                    document.syalala.tombol.disabled=true;
                }else{
                    getTotalHallAvailable();
                }
            }

            var validatePackage=function(){
                if(document.syalala.hallpackage.value=="notchoosed"){
                    document.syalala.tombol.disabled=true;
                }
                else{
                    getTotalHallAvailable();
                }
            }


            var getTotalHallAvailable=function(){
                //Mendapatkan kapasitas maksimal yang ada di basis data untuk layout tertentu yang disediaan hotel
                var layoutSelectObjValue=document.syalala.layout.value;
                var date=document.syalala.reservationdate.value;
                var capacity=document.syalala.capacity.value;
                if(layoutSelectObjValue=="notchoosed" || date==null || capacity==null){
                    //Kalo belum milih di disable dulu
                    return;
                }
                else if(date=="" || capacity==""){

                }
                else{
                    //Kalo value nya yang lain ambil kapasitas yang available secara asinkron
                    document.syalala.capacity.disabled=false;
                    var ajaxpost = new XMLHttpRequest();
                    if(ajaxpost){
                        ajaxpost.open("POST", "HallReservationAjax");
                        ajaxpost.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                        ajaxpost.onreadystatechange = function() {
                            if (ajaxpost.readyState == 4 && ajaxpost.status == 200) {
                                //alert(ajaxpost.responseText);
                                var i;
                                var batasAtas=parseInt(ajaxpost.responseText);
                                document.syalala.hallneeded.length=0;
                                document.syalala.hallneeded.disabled=false;
                                document.syalala.tombol.disabled=false;
                                for(i=1;i<=batasAtas;i++){
                                    var optionObj=document.createElement('option');
                                    optionObj.text=i+"";
                                    optionObj.value=i+'';
                                    try{
                                        document.syalala.hallneeded.add(optionObj,null);
                                    }catch(ex){
                                        document.syalala.hallneeded.add(optionObj);
                                    }
                                }
                            }
                        }
                        var data= "layout=" + layoutSelectObjValue+"&reservationdate="+date+"&capacity="+capacity;
                        //alert(data);
                        ajaxpost.send(data);
                    }

                }


            }


        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="wrapper col3">
            <div id="breadcrumb">
                <h1>Hall Reservations</h1>
            </div>
        </div>
    <%

                String step = request.getParameter("step");
                String id = null;
                if (request.getParameter("id") != null) {
                    id = request.getParameter("id");
                }
                if (step == null) {
                    response.sendRedirect("hallreservation.jsp?step=1");
                } else if (step.equals("1")) {
                    //Inisialisasi List nya
                    List<Layout> listLayout = (new LayoutJpaController()).findLayoutEntities();
                    List<Hall> listHall = (new HallJpaController()).findHallEntities();

                    //Inisialisasi iterator untuk masing-masing list
                    Iterator<Layout> iLayout = listLayout.iterator();
                    Iterator<Hall> iHall = listHall.iterator();


    %>
    <div class="wrapper col4">
    <div id="container">
    <div id="content" style="width:500px;">
    <jsp:include page="showcart.jsp" />
    <form method="POST" name="syalala" action="TambahKeranjang?action=addhall">
        <ul>
            <li>
                <label>Package :</label>
                <select name="hallpackage" onchange="validatePackage();">
                    <option value="notchoosed">Please choose package...</option>
                    <% // di sini buat masukkin packagenya secara dinamis
                        while (iHall.hasNext()) {
                            Hall temp = iHall.next(); %>
                            <option value=<% out.write("'"+temp.getProductId()+"'"); if (id != null) {if (id.equals(temp.getProductId())) {out.write(" selected='true'");}} %>><% out.write(temp.getProductType()); %></option>
                     <% }
                    %>
                </select>
            </li

            <li>
                <label>Date : </label>
                <input type="text" name="reservationdate" class="datepicker" onchange="validateDate();">
            </li>

            <li>
                <label for="layout">Layout : </label>
                <select name="layout" onchange="validateLayout();">
                    <option value="notchoosed">Please choose layout...</option>
                    <% // di sini buat masukkin layoutnya secara dinamis ambil dari database
                                            while (iLayout.hasNext()) {
                                                Layout temp = iLayout.next();
                                                out.write("<option value=" + temp.getLayoutNo() + ">" + temp.getLayoutName() + "</option>");
                                            }
                    %>
                </select>
            </li>

            <li>
                <label for="capacity">Attendees: </label>
                <input name="capacity" type="text" onchange="validateCapacity();"/>person
            </li>

            <li>
                <label for="hallneeded">Hall needed : </label>
                <select name="hallneeded">
                    <% //di sini buat masukkin hall yang available buat user nya. %>
                </select>
            </li>

            <li>
                <input type="submit" value="submit" disabled="true" name="tombol"/>
            </li>
        </ul>

    </form>
    </div>
    <% //endif untuk step==1
                            }%>
    <div id="column">
        <div class="subnav">
            <h2>Reservation Status</h2>
            <p>See your reservation status <a href="reservation_status.jsp" class="link_res_stat">here</a></p>
        </div>
        <div class="subnav">
            <h2>Promotion</h2>
            <p>For best room rate and booking service, please contact our customer service.</p>
        </div>
    </div>
    </div>
    <div class="clear"></div>
     </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
