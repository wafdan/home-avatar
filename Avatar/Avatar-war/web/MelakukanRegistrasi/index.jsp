<%-- 
    Document   : index
    Created on : Sep 30, 2010, 9:21:05 AM
    Author     : dhana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Domesticated by Free CSS Templates</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="jquery/jquery.slidertron-0.1.js"></script>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
    @import "slidertron.css";
</style>
</head>
<body>
<!-- end #header-wrapper -->
<div id="header">
	<div id="logo">
		<h1><a href="#">Spons Hotel</a></h1>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="index.jsp">Overview</a></li>
                        <li><a href="reservation.jsp" class="first">Reservation</a></li>
			<li><a href="rooms.jsp">Rooms</a></li>
                        <li><a href="hall.jsp">Meeting & Events</a></li>
			<li><a href="services.jsp">Other Services</a></li>
                        <li><a href="contactus.jsp">Contact Us</a></li>
		</ul>
	</div>
	<!-- end #menu -->
</div>
<!-- end #header -->
<hr />
<div id="wrapper">
	<!-- end #logo -->
	<div id="two-columns">
		<div class="col1">
			<div id="foobar">
				<div class="navigation"> <a href="#" class="first">[ &lt;&lt; ]</a> &nbsp; <a href="#" class="previous">[ &lt; ]</a> &nbsp; <a href="#" class="next">[ &gt; ]</a> &nbsp; <a href="#" class="last">[ &gt;&gt; ]</a> </div>
				<div class="viewer">
					<div class="reel">
						<div class="slide"> <img src="images/1.jpg" alt=""> <span>Kamar Hotel</span> </div>
						<div class="slide"> <img src="images/2.jpg" alt=""> <span>Aula Hotel</span> </div>
						<div class="slide"> <img src="images/3.jpg" alt=""> <span>Fasilitas Kolam Renang Hotel</span> </div>
					</div>
				</div>
			</div>
			<script type="text/javascript">

						$('#foobar').slidertron({
							viewerSelector:			'.viewer',
							reelSelector:			'.viewer .reel',
							slidesSelector:			'.viewer .reel .slide',
							navPreviousSelector:	'.previous',
							navNextSelector:		'.next',
							navFirstSelector:		'.first',
							navLastSelector:		'.last'
						});

					</script>
		</div>
		<div class="col2">
			Login <br/>
            <form name="login" action="" method="get" style=" font-size:18px">
            	User name: <input size="10" type="text" name="username" /><br />
            	Password  :&nbsp; <input size="10" type="password" name="pass" /><br/>
                <input type="submit" value="Login" /><br/><br/>
                Belum terdaftar? <br/>Registrasi di &nbsp;&nbsp;
                <a href="register.html">SINI</a>

			</form>
         </div>
  </div>
</div>
<div id="page">
	<div id="page-bgtop">
		<div id="content">
			<div class="post">
		    <h2 class="title"><a href="#">Welcome to Spons Hotel</a></h2>
				<div class="entry">
					<p><strong>A warm welcome awaits you</strong> at the Spons Hotel. Located in the very heart of Bandung's central business district, our great location allows you to explore and connect to our wonderful city. You can stroll  out of our doors and enjoy the surrounding shopping area, stunning  views of Mt.Tangkuban Perahu, the City Hall, and the Old Town.</p>
                    <p>We look forward to welcoming you</p>
                </div>
			</div>
	  </div>
		<!-- end #content -->
		<div id="sidebar">
			<ul>
				<li>
					<h2>LINKS</h2>
					<ul>
					  <li><a href="#">Link 1</a></li>
					  <li><a href="#">Link 2</a></li>
					  <li><a href="#">Link 3</a></li>
					  <li><a href="#">Link 4</a></li>
					  <li><a href="#">Link 5</a></li>
					  <li><a href="#">Link 6</a></li>
				  </ul>
			  </li>
			</ul>
    </div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
	<p>Copyright (c) 2010 AVATAR. All rights reserved. Design by <a href="#/">Hakuna Matata</a>.</p>
</div>
<!-- end #footer -->
</body>
</html>
