<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>

<jsp:include page="title.jsp"/>- The Best Luxury Hotel in Bandung</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>
<%
    ProfileJpaController pjc=new ProfileJpaController();
    Profile p=pjc.findProfile(Boolean.TRUE);
%>
<body id="top">
<jsp:include page="header.jsp"/>

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
      <img id="logoabout" class="imgl" src="images/logohotel.png" alt="" width="125" height="125" />
      <p><strong><% out.write(p.getHotelName()+" "+p.getHotelCity()); %></strong></p>
      <p><% out.write(p.getHotelDescription()); %></p>
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

<jsp:include page="footer.jsp"/>

</body>
</html>
