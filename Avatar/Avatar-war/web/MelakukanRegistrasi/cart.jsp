<%-- 
    Document   : cart
    Created on : 01 Okt 10, 13:57:48
    Author     : kamoe
--%>

<%@ page import="Layanan.MelihatLayananController" %>
<%@ page import="Layanan.Cart" %>

<%
    MelihatLayananController ctrl = new MelihatLayananController();
    String add = request.getParameter("add");
    int type = Integer.parseInt(request.getParameter("type"));
    String id = request.getParameter("id");

    /*if (add.equals("1")) {
        if (type == 1) {
            ctrl.c.addCart(type, (Object) ctrl.getAccomodation(id));
        } else {
            ctrl.c.addCart(type, (Object) ctrl.getHall(id));
        }
    } else {
        if (type == 1) {
            ctrl.c.deleteCart((Object) ctrl.getAccomodation(id));
        } else {
            ctrl.c.deleteCart((Object) ctrl.getHall(id));
        }
    }*/

    if (type == 1) { %>
        <jsp:forward page="rooms.jsp?id=<=%id%>" />
    <% } else { %>
        <jsp:forward page="hall.jsp?type=2&id=<=%id%>" />
    <%}
%>