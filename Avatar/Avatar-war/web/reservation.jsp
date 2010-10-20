<%-- 
    Document   : reservation
    Created on : 20 Okt 10, 13:03:55
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

        try{

            if (request.getParameter("step").equals("2")) {
                /* INI HALAMAN KEDUA */
            } else if (request.getParameter("step").equals("3")) {
                /* INI HALAMAN KETIGA */
            } else {
                /* INI HALAMAN PERTAMA */
        %>
        
        <%
                
            }
            }
        catch(NullPointerException ex){%>
          <h1> Reservation Form</h1>
        <p> Please fill up this form to book our facility. </p>
        <h2>ROOM</h2>

        <form action="AddFacility" method="POST">
            <label>Room type : </label>
            <select name="roomtype">
                <%
                    /*INI BUAT MASUKKIN TIPE ROOMTYPENYA DARI DATABASE*/
                %>
            </select>
            <label>Total room</label> <input type="text" name="totalroom" />
            <label>Check-in date</label> <input name="roomcheckindate" type="text" readonly="true" />
            <label>Check-out date</label> <input name="roomcheckoutdate" type="text" readonly="true" />

            <h2>HALL</h2>
            <label>Package</label>
            <select name="packagetype">
                <%
                    /*INI BUAT MASUKKIN TIPE HALLTYPE DARI DATABASE*/
                %>
            </select>
            <label>Total hall needed</label> <input type="text" name="totalhall" />
            <label>Date</label> <input name="halldate" type="text" readonly="true" />
        </form>
        <%}
        %>
    </body>
</html>
