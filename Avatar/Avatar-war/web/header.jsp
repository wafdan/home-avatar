<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<div class="wrapper col1">
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
        <li class="last"><a href="reservation.jsp">Reservation</a><span>make an order</span></li>
        <li><a href="services.jsp">Services</a><span>Our best services</span></li>
        <li><a href="hall.jsp">Events</a><span>Meeting and Conference</span></li>
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