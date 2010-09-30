<%-- 
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../default.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
<!--
div.box1 {margin:0 auto;
width:500px;
background:#222;
position:relative;
top:50px;
border:1px solid #262626;
}
-->
    </style>
    </head>
<body>

        <div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>

        <!-- start header -->
        <div id="header">
            <div id="menu">
                <ul>
                    <li class="current_page_item"><a href="#">Profile</a></li>
                    <li><a href="../MengelolaPengguna/ManageStaff.jsp">User</a></li>
                    <li><a href="#">Facilities</a></li>
                    <li><a href="#">Statistic</a></li>
                    <li><a href="#">Post</a></li>
                    <li><a href="#">Repository</a></li>
                    <li><a href="#">Reservation</a></li>
                    <li><a href="#">Payment</a></li>
                </ul>
            </div>
        </div>
        <!-- end header -->

        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                      <div class="post">
                        <div class="box">
                          <h1>edit profil hotel</h1>
                          <form method="post"><label> <span>Nama Hotel</span>
                            <input class="input_text" name="hotelname" id="hotelname" type="text" />
                          </label>
                          <label> <span>Alamat Hotel 1</span>
                            <input class="input_text" name="hoteladdress1" id="hoteladdress1" type="text" />
                          </label>
                          <label> <span>Alamat Hotel 2</span>
                            <input class="input_text" name="hoteladdress2" id="hoteladdress2" type="text" />
                          </label>
                          <label> <span>Email</span>
                            <input class="input_text" name="hotelemail" id="hotelemail" type="text" />
                          </label>
                          <label> <span>Logo Hotel</span>
                            <input class="input_text" name="hotellogo" id="hotellogo" type="file" />
                            <!--input class="button" type="file" name="browse" value="browse..." style="width:75px" /-->
                          </label>
                          <label> <span>Deskripsi Hotel</span>
                            <textarea class="message" name="hoteldeskripsi" id="hoteldeskripsi"></textarea>
                            <input class="button" value="Simpan" type="submit" />
                          </label>
                          </form>
                        </div>
                        <h2 class="title">&nbsp;</h2>
                        <div class="post">
                          <p>&nbsp;</p>
                        </div>
                      </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
<div id="sidebar">
                        <ul>
                            <li>
                                <div id="sidebar-title">
                                    <h2>Hotel Profile</h2>
                                </div>
                                <ul>
                                    <li><a href="Profil.jsp">Show Profile</a></li>
                                    <li><a href="EditProfil.jsp">Edit Profile</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- end sidebar -->
                    <div style="clear: both;">&nbsp;</div>
                </div>
                <!-- end page -->
            </div>
        </div>
        <!-- start footer -->
        <div id="footer">
            <div id="footer-wrap">
                <p id="legal">(c)2010 AVATAR. Design by <a href="http://www.freecsstemplates.org/">Hakuna Matata</a>.</p>
            </div>
        </div>
        <!-- end footer -->
    </body>
</html>

