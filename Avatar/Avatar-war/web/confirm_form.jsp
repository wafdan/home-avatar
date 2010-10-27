<%-- 
    Document   : confirm_form
    Created on : 27 Okt 10, 9:28:12
    Author     : kamoe
--%>

<%@page import="AvatarEntity.Reservation" %>
<%@page import="KonfirmasiPembayaran.*" %>

<%
    String rid = request.getParameter("id");
    String show = request.getParameter("show");
    KonfirmasiPembayaranController c = new KonfirmasiPembayaranController();
    Reservation r = c.getReservationById(Integer.parseInt(rid));

    if (show.equals("true")) {
%>
    <form method="post" name="confirmPayment" id="confirmPayment" action="ConfirmPayment">
    <input type="hidden" name="reservationId" id="reservationId" value="<%= r.getReservationId() %>" />
    <table>
    <tr>
    <td><label for="acc_no">Account Number</label></td>
    <td><label for="bank">Bank</label></td>
    <td><label for="amount">Amount</label></td>
    <td><label for="payment_date">Payment Date (dd/mm/yyyy)</label></td>
    </tr>
    <tr>
    <td><input type="text" id="acc_no<%= r.getReservationId() %>" name="acc_no" /></td>
    <td><input type="text" id="bank<%= r.getReservationId() %>" name="bank" /></td>
    <td><input type="text" id="amount<%= r.getReservationId() %>" name="amount" /></td>
    <td><input type="text" id="payment_date<%= r.getReservationId() %>" name="payment_date" />
    </td>
    </tr>
    </table>
    <input class="button" type="submit" value="Confirm">
    <input class="button" type ="button" onclick="javascript:showForm(<%= r.getReservationId() %>,0)" value="Cancel">
    </form>
    
<%  } else { %>
        <input class="button" type ="button" onclick="javascript:showForm(<%= r.getReservationId() %>,1)" value="Confirm">
<%  }   %>