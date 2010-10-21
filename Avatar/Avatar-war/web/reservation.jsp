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
        <script type="text/javascript" src="script/jquery.js"></script>
        <script type="text/javascript">


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
            <!-- BAGIAN DHANA -->
            <label>Check-in date</label> <input name="roomcheckindate" type="text" readonly="true" class="date-pick" />
            <label>Check-out date</label> <input name="roomcheckoutdate" type="text" readonly="true" />
            <!-- END OF BAGIAN DHANA -->

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
            <!-- BAGIAN DHANA LAGI -->
            <label>Date</label> <input name="halldate" type="text" readonly="true" />
            <!-- END OF BAGIAN DHANA -->
        </form>
        <%}
        %>
    </body>
</html>
