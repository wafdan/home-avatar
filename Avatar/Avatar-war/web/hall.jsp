terator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Hotel Graha Bandung - Events</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">
<div class="wrapper col1">
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

  <div id="topbar">
       <%if ((session.getAttribute("name")) == null) {%>
    <form name="login" action="Login" method="post">
        <fieldset>
        <ul>
            <li>Username <input name="username" id="username" type="text" /> Password <input name="password" id="password" type="password" /></li>
      	<li class="last"><input class="button_top" type="submit" name="loginbutton" id="news_go" value="Login" /></li>
    	</ul>
        </fieldset>
    </form>
    <%} else {%>
        <ul>
            <li>Welcome, <a href="myprofile.jsp"><%= session.getAttribute("name")%></a></li>
            <li class="last"><a href="Logout">Logout</a></li>
    	</ul>
    <%}%>
    <br class="clear" />
  </div>
</div>

<div class="wrapper col2">
  <div id="header">

    <div id="topnav">
      <ul>
        <li class="last"><a href="reservations.html">Reservation</a><span>make an order</span></li>
        <li><a href="services.jsp">Services</a><span>Our best services</span></li>
        <li class="active"><a href="hall.jsp">Events</a><span>Meeting and Conference</span></li>
        <li><a href="rooms.jsp">Rooms</a><span>Rooms and Facilities</span></li>
        <li><a href="index.jsp">Home</a><span></span></li>
      </ul>
    </div>

    <div id="logo">
    	<div id="logokiri">
        	<img class="imglogo" src="images/demo/logohotelgrahamini.png" alt="" />
        </div>
        <div id="logokanan">
        	<h1><a href="index.html">Hotel Graha</a></h1>
      		<p>The Best Luxury Hotel in Bandung</p>
        </div>
    </div>

    <br class="clear" />
  </div>
</div>

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
                out.println("<h2 class='title'>" + ((Venue) cur).getVenueName() + "</h2>");
            } else {
                if (type.equals("1")) {
                        out.println("<h2 class='title'>" + ((Venue) cur).getVenueName() + "</h2>");
                } else {
                        out.println("<h2 class='title'>" + ((Hall) cur).getProductType() + "</h2>");
                }
            }

            if (type.equals("1")) {
                out.println("<br /><p><img src='" + ((Venue) cur).getImage() + "' />" + ((Venue) cur).getDescription() + "</p>");

            } else {
                out.println("<br />");
                if (!isLogin) {
                    out.println("<a class='book' href='index.jsp'>Add to Cart</a>");
                } else {
                    if (ctrl.c.isOnCart((Object) cur)) {
                        out.println("<a class='book' href='cart.jsp?add=2&type=2&id=" + ((Hall) cur).getProductId() + "'>Remove from Cart</a>");
                    } else {
                        out.println("<a class='book' href='cart.jsp?add=1&type=2&id=" + ((Hall) cur).getProductId() + "'>Add to Cart</a>");
                        System.out.println(ctrl.c.count());
                    }
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
<div class="wrapper col5">
  <div id="footer">
    <div id="newsletter">
      <%if ((session.getAttribute("name")) == null) {%>
          <h2>Don't Have an Account?</h2>
          <p>Join us to be the first to know about our special promotion !</p>
          <p><br/></p>
          <p>Sign Up <a href="register.jsp">Here &raquo;</a>!</p>
      <%} else {%>
        <h2>Something Change?</h2>
        <p>Please, let us know!</p>
        <p>Edit Your Profile <a href="myprofile.jsp">Here &raquo;</a>!</p>
      <%}%>
    </div>
    <div class="footbox">
      <h2>Contact Us</h2>
         <p>Hotel Graha Bandung</p>
          <p>Jalan Pahlawan 123, Bandung, </p>
          <p>West Java, Indonesia</p>
          <p>ZIP code : 40123</p>
          <p>Phone : +6222 254123 | Fax : +6222 254124</p>
    </div>
    	<br class="clear" />
  	</div>
</div>
<div class="wrapper col6">
  <div id="copyright">
    <p class="fl_left">Avatar &copy; 2010 - All Rights Reserved - <a href="www.hakunamatata.com"> Hakuna Matata </a></p>
    <br class="clear" />
  </div>
</div>
</body>
</html>
