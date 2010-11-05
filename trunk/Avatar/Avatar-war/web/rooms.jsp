<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="Layanan.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
    <title>Hotel Graha Bandung - Rooms and Facilities</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta http-equiv="imagetoolbar" content="no" />
    <link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">
     <%
        MelihatLayananController ctrl = new MelihatLayananController();
        List<Accomodation> rooms = ctrl.getAccomodationList();
        Accomodation cur;
        String pid = request.getParameter("id");
        if (pid == null) {
            cur = rooms.get(0);
        } else {
            cur = ctrl.getAccomodation(pid);
        }

        Boolean isLogin = false;
        if (session.getAttribute("name") != null) {
            isLogin = true;
        }
    %>
<jsp:include page="header.jsp"/>

<div class="wrapper col3">
  <div id="breadcrumb">
    <h1>Rooms and Facilities</h1>
  </div>
</div>

<div class="wrapper col4">
  <div id="container">
    <div id="content">
       <%
            if (rooms.size() < 1) {
                out.println("<div class='entry'>");
                out.println("<p>No Room Availaible</p>");
                out.println("</div>");
            } else {
                out.println("<h1 class='title'>" + cur.getProductType() + "</h1>");
                out.println("<br />");

                if (!isLogin) {
                    out.println("<a class='book' href='index.jsp'>Add to Cart</a>");
                } else {
                    if (ctrl.c.isOnCart((Object) cur)) {
                        out.println("<a class='book' href='cart.jsp?add=2&type=1&id=" + cur.getProductId() + "'>Remove from Cart</a>");
                    } else {
                        out.println("<a class='book' href='cart.jsp?add=1&type=1&id=" + cur.getProductId() + "'>Add to Cart</a>");
                    }
                }

                out.println("<div class='entry'>");
                out.println("<p><img src='" + cur.getImage() + "' />" + cur.getDescription() + "</p>");
                out.println("</div>");
            }
        %>
    </div>


    <div id="column">
    	<div class="holder">
            <%if ((session.getAttribute("name")) != null) {%>
                <h2 class="title"><img src="images/demo/cart.png" alt="" /><% out.print(ctrl.c.count()); %> Item(s) in Your Cart</h2>
            <%}/*else {*/%>
        </div>

      <div class="subnav">
        <h2>Room's Type</h2>
        <ul>
            <%
                //Mengambil list room dari basis data
                Iterator Iter = rooms.iterator();
                while (Iter.hasNext()) {
                    Accomodation acc = (Accomodation) Iter.next();
                    out.println("<li><a href='rooms.jsp?id=" + acc.getProductId() + "'>" + acc.getProductType() + "</a></li>");
                }
            %>
        </ul>
      </div>

    </div>
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>