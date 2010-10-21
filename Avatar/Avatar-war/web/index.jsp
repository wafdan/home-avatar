<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Hotel Graha Bandung - The Best Luxury Hotel in Bandung</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">
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
        <li class="active"><a href="index.html">Home</a><span></span></li>
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
  <div id="intro">
    <ul>
      <li><img src="images/demo/steak.png" alt="" /> <a href="services.jsp">Five Star Restaurant &raquo;</a></li>
      <li><img src="images/demo/pool.png" alt="" /> <a href="services.jsp">Sport and Leisure &raquo;</a></li>
      <li><img src="images/demo/meeting.png" alt="" /> <a href="hall.jsp">Conference and Meeting &raquo;</a></li>
      <li class="last"><img src="images/demo/room.png" alt="" /> <a href="rooms.jsp">Rooms and Suites &raquo;</a></li>
    </ul>
    <br class="clear" />
  </div>
</div>
<div class="wrapper col4">
  <div id="container">
    <div id="content">
      <h2>About Us</h2>
      <img class="imgl" src="images/demo/logohotelgraha.png" alt="" width="125" height="125" />
      <p><strong>Hotel Graha Bandung</strong></p>
      <p>Welcome to Hotel Graha Bandung, a five star luxury hotel in Bandung, West Java. Located in the heart of Bandung, we provide a superior level of service to satisfy the needs of our global guests. As a member of the "Hotel Graha" group, the Hotel Graha Bandung offers an extraordinary level of service and coordinated interior designs. Restored to its original opulence, Hotel Graha Bandung sets the standard for elegance. From the magnificent columned lobby with its marble floors and stained-glass dome to the classic décor of the 250 guestrooms and suites, we ensures that every stay is a memorable one.</p>
  	</div>
    <div id="column">
      <div class="holder">
        <h2>Promotion</h2>
        <ul id="latestnews">
          <li><img class="imgl" src="images/demo/dinner.png" alt="" />
            <p><strong>Celebration Package</strong></p>
            <p>Celebrate with us! With all ingredients to a lifelong memorable celebration.</p>
            <p class="readmore"><a href="services.jsp">Continue Reading &raquo;</a></p>
          </li>
          <li class="last"><img class="imgl" src="images/demo/newyear.png" alt="" />
            <p><strong>Special Offer</strong></p>
            <p>Make your new year's party unforgetable with our special package.</p>
            <p class="readmore"><a href="hall.jsp">Continue Reading &raquo;</a></p>
          </li>
        </ul>
      </div>
    </div>
    <br class="clear" />
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
