<%-- 
    Document   : cart
    Created on : 01 Okt 10, 13:57:48
    Author     : kamoe
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Pemesanan.*" %>

<%
    ArrayList<RoomSessionInfo> cartRoom = (ArrayList<RoomSessionInfo>) session.getAttribute("roomcart");
    ArrayList<HallSessionInfo> cartHall = (ArrayList<HallSessionInfo>) session.getAttribute("hallcart");
    int size = 0;
    if (cartRoom != null) {
        size += cartRoom.size();
    }
    if (cartHall != null) {
        size += cartHall.size();
    }
    session.setAttribute("cartSize", size);
%>