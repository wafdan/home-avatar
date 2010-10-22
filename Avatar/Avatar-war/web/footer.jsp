<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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