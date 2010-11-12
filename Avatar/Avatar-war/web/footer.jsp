<%@page import="AvatarEntity.Profile" %>
<%@page import="AvatarEntity.ProfileJpaController" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    ProfileJpaController pc = new ProfileJpaController();
    Profile p = pc.findProfile(true);
%>
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
      <p><% out.write(p.getHotelName()); %></p>
          <p><% out.write(p.getHotelAddress1()+", "+ p.getHotelCity()); %></p>
          <p><% out.write(p.getHotelCountry()); %></p>
          <p><% out.write("Phone : "+p.getHotelPhone()); %></p>
          <p><% out.write("Email : "+p.getHotelEmail()); %></p>
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