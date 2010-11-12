<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.Venue" %>
<%@ page import="AvatarEntity.Hall" %>
<%@ page import="AvatarEntity.VenueJpaController" %>
<%@ page import="AvatarEntity.HallJpaController" %>
<%@ page import="Layanan.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<jsp:include page="title.jsp"/>- Events</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">
    <%
        MelihatLayananController ctrl = new MelihatLayananController();
        List<Venue> venues = ctrl.getVenueList();
        List<Hall> packages = ctrl.getHallList();
        Object cur;
        String type = request.getParameter("type");
        String pid = request.getParameter("id");
        if (pid == null) {
                type = "1";
                cur = venues.get(0);
        } else {
                if (type.equals("1")) {
                        cur = ctrl.getVenue(pid);
                } else {
                        cur = ctrl.getHall(pid);
                }
        }

        Boolean isLogin = false;
        if (session.getAttribute("name") != null) {
                isLogin = true;
        }
     %>
<jsp:include page="header.jsp"/>

<div class="wrapper col3">
  <div id="breadcrumb">
    <h1>Events</h1>
  </div>
</div>

<div class="wrapper col4">
  <div id="container">
    <div id="content">
      <%
            if (type == null) {
                if (venues.size() < 1) {
                    out.println("<div class='entry'>");
                    out.println("<p>No Venue Availaible</p>");
                    out.println("</div>");
                } else {
                    out.println("<h2 class='title'>" + ((Venue) cur).getVenueName() + "</h2>");
                }
            } else {
                if (type.equals("1")) {
                    out.println("<h2 class='title'>" + ((Venue) cur).getVenueName() + "</h2>");
                } else {
                    out.println("<h2 class='title'>" + ((Hall) cur).getProductType() + "</h2>");
                }
            }

            if (type.equals("1")) {
                out.println("<br /><p>" + ((Venue) cur).getDescription() + "</p><img src='" + ((Venue) cur).getImage() + "' alt='' />");
            } else {
                out.println("<br />");
                if (!isLogin) {
                    out.println("<a class='book' href='index.jsp'>Reserve</a>");
                } else {
                    /*if (ctrl.c.isOnCart((Object) cur)) {
                        out.println("<a class='book' href='cart.jsp?add=2&type=2&id=" + ((Hall) cur).getProductId() + "'>Remove from Cart</a>");
                    } else {
                        out.println("<a class='book' href='cart.jsp?add=1&type=2&id=" + ((Hall) cur).getProductId() + "'>Add to Cart</a>");
                        System.out.println(ctrl.c.count());
                    }*/
                    out.println("<a class='book' href='hallreservation.jsp?step=1&id=" + ((Hall) cur).getProductId() + "'>Reserve</a>");
                }
                out.println("<div class='entry'>");
                out.println("<p>" + ((Hall) cur).getDescription() + "</p>");
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
        <h2>Venue Type</h2>
        <ul>
            <%
                Iterator Iter = venues.iterator();
                while (Iter.hasNext()) {
                    Venue ven = (Venue) Iter.next();
                    out.println("<li><a href='hall.jsp?type=1&id=" + ven.getVenueNo() + "'>" + ven.getVenueName() + "</a></li>");
                }
            %>
        </ul>
      </div>

     <div class="subnav">
        <h2>Package Type</h2>
        <ul>
            <%
                Iter = packages.iterator();
                while (Iter.hasNext()) {
                    Hall pkg = (Hall) Iter.next();
                    out.println("<li><a href='hall.jsp?type=2&id=" + pkg.getProductId() + "'>" + pkg.getProductType() + "</a></li>");
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
