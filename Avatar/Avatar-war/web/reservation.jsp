<%-- 
    Document   : reservation
    Created on : 20 Okt 10, 13:03:55
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Accomodation"%>
<%@page import="AvatarEntity.Hall"%>
<%@page import="AvatarEntity.HallJpaController"%>
<%@page import="AvatarEntity.AccomodationJpaController"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            var nextMonth = function(date) {
                var retval = new Date(date);
                if(date.getMonth() == 11) {
                    retval.setYear(date.getFullYear() + 1);
                    retval.setMonth(0);
                } else {
                    retval.setMonth(date.getMonth() + 1);
                }
                return retval;
            }

            var prevMonth = function(date) {
                var retval = new Date(date);
                if(date.getMonth() == 0) {
                    retval.setYear(date.getFullYear() - 1);
                    retval.setMonth(11);
                } else {
                    retval.setMonth(date.getMonth() - 1);
                }
                return retval;
            }

            var nextYear = function(date) {
                var retval = new Date(date);
                retval.setYear(date.getFullYear() + 1);
                return retval;
            }

            var prevYear = function(date) {
                var retval = new Date(date);
                retval.setYear(date.getFullYear() - 1);
                return retval;
            }

            var pickDate = function(event, date) {
                document.getElementById("lbirthday").innerHTML = "Birthday";
                clickStat = 0;
                var today = new Date();
                if(date == null) {
                    var birthdayField = document.getElementById("birthday").value;
                    var bday = birthdayField.split("-");
                    if(!validateInput(3)) date = new Date(bday[0], (bday[1] - 1), bday[2]);
                    else date = today;
                }
                else date = new Date(date);
                var next = nextMonth(date);
                var prev = prevMonth(date);
                var nextY = nextYear(date);
                var prevY = prevYear(date);
                var target = document.getElementById("small-calendar");
                if(event != null) {
                    target.style.top = (event.clientY - 5) + "px";
                    target.style.left = (event.clientX + 20) + "px";
                    target.style.visibility = "inherit";
                }

                content = "<div class='cal-bar'>";
                content += "<div class='cal-prev' onclick='pickDate(null, "+prevY.getTime()+");'><<</div>";
                content += "<div class='cal-prev' onclick='pickDate(null, "+prev.getTime()+");'>&lt;</div>";
                content += "<div class='cal-next' onclick='pickDate(null, "+nextY.getTime()+");'>>></div>";
                content += "<div class='cal-next' onclick='pickDate(null, "+next.getTime()+");'>&gt;</div>";
                content += "<center>"+monthNames[date.getMonth()]+" "+date.getFullYear()+"</center>";
                content += "</div>";

                content += "<div class='clearboth'><div class='cal-sel cal-head'>S</div><div class='cal-sel cal-head'>M</div><div class='cal-sel cal-head'>T</div><div class='cal-sel cal-head'>W</div><div class='cal-sel cal-head'>T</div><div class='cal-sel cal-head'>F</div><div class='cal-sel cal-head'>S</div></div>";

                date.setDate(1);
                var firstDay = date.getDay();
                var currentMonth = date.getMonth();
                date.setTime(date.getTime() - (1000 * 60 * 60 * 24 * firstDay));
                for(var i = 0; i < 42; i++) {
                    if(i % 7 == 0) content += "<div class='clearboth'>";
                    content += "<div class='cal-sel";
                    if(date.getMonth() != currentMonth) content += " cal-other";
                    if(i % 7 == 0) content += " cal-sunday";
                    if(i % 7 == 5) content += " cal-friday";
                    content += "'";
                    if(date.getMonth() == currentMonth) content += " onclick='fillBirthday("+date.getDate()+", "+(date.getMonth()+1)+", "+date.getFullYear()+");'";
                    content += ">"+date.getDate()+"</div>";
                    if(i % 7 == 6) content += "</div>";
                    date.setTime(date.getTime() + (1000 * 60 * 60 * 24));
                }

                content += "&nbsp;";

                target.innerHTML = content;
                document.onclick = function() {
                    if(clickStat > 0) closePickDate();
                    clickStat++;
                }
            }

            var fillBirthday = function(date, month, year) {
                date = date.toString();
                month = month.toString();
                if(date.length == 1) date = "0" + date;
                if(month.length == 1) month = "0" + month;
                document.getElementById("birthday").value = year + "-" + month + "-" + date;
                closePickDate();
            }

            var closePickDate = function() {
                document.getElementById("small-calendar").innerHTML = "";
                document.getElementById("small-calendar").style.visibility = "hidden";
                document.getElementById("birthday").focus();
                document.getElementById("lbirthday").innerHTML = "Birthday";
                document.onclick = null;
            }
        </script>
    </head>
    <body>
        <%

                    try {

                        if (request.getParameter("step").equals("2")) {
                            /* INI HALAMAN KEDUA */
        %>
        <h1>THIS IS RESUME OF YOUR RESERVATION</h1>
        <h2>Room</h2>
        <table>
            <tr>
                <th>No.</th>
                <th>Facility</th>
                <th>Duration (days)</th>
                <th>Unit price</th>
                <th>Total price</th>
            </tr>
            <tr>
                <% /*ini buat produce isi td-nya*/%>
            </tr>
        </table>
        <%                    } else if (request.getParameter("step").equals("3")) {
                                    /* INI HALAMAN KETIGA */
        %> 
        <p>Please transfer Rp. <% /*Total pembayaran*/%> to one of these account number : </p>
        <ol>
            <li>
                Bank : BRI
                Acc. No. : 0992-19920776-1
                Holder name : Zulfikar Hakim
            </li>

            <li>
                Bank : BCA
                Acc No. : 03976-1-374-1
                Holder name : Zulfikar Hakim
            </li>
        </ol>

        <FORM METHOD="LINK" ACTION="#">
            <INPUT TYPE="submit" VALUE="Confirm payment now">
        </FORM>

        <form method="link" action="#">
            <input type="submit" value="Go to main page"
        </form>



        <%                            } else {
                                    /* INI HALAMAN PERTAMA */
        %>

        <%                    }
                            } catch (NullPointerException ex) {%>
        <h1> Reservation Form</h1>
        <p> Please fill up this form to book our facility. </p>
        <h2>ROOM</h2>

        <form action="AddFacility" method="POST">
            <label>Room type : </label>
            <select name="roomtype">
                <%
                                        /*INI BUAT MASUKKIN TIPE ROOMTYPENYA DARI DATABASE*/
                                        List<Accomodation> listAccomodation = (new AccomodationJpaController()).findAccomodationEntities();
                                        Iterator<Accomodation> i = listAccomodation.iterator();


                                        while (i.hasNext()) {
                                            Accomodation temp;
                                            temp = i.next();
                %>
                <option value="<%=temp.getProductId()%>"><%=temp.getProductType()%></option>
                <%
                                        }
                %>
            </select>
            <label>Total room</label> <input type="text" name="totalroom" />
            <label>Check-in date</label> <input name="roomcheckindate" type="text" readonly="true" /><button type="button" onclick="pickDate(event,null)"><img src="./img/calendar.jpg" alt="calendar" /></button>

            <label>Check-out date</label> <input name="roomcheckoutdate" type="text" readonly="true" />

            <h2>HALL</h2>
            <label>Package</label>
            <select name="packagetype">
                <%
                                        /*INI BUAT MASUKKIN TIPE HALLTYPE DARI DATABASE*/
                                        List<Hall> listhall = (new HallJpaController()).findHallEntities();
                                        Iterator<Hall> iHall = listhall.iterator();
                                        while (iHall.hasNext()) {
                                            Hall hall = iHall.next();
                %>
                <option value="<%=hall.getProductId()%>"> <%=hall.getProductType()%> </option>
                <%
                                        }
                %>
            </select>
            <label>Total hall needed</label> <input type="text" name="totalhall" />
            <label>Date</label> <input name="halldate" type="text" readonly="true" />
        </form>
        <%}
        %>
    </body>
</html>
