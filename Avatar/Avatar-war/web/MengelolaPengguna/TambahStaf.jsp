<%-- 
    Document   : newjsp
    Created on : 29 Sep 10, 17:33:58
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

    </head>
    <body>
        <script type="text/javascript">
            var CekInput=function()
            {
                //alert('tes');
                var usernameText=document.getElementById('fullname').value;
                var employmentIDText=document.getElementById('employmentID').value;
                var emailText=document.getElementById('email').value;


                //alert(employmentIDText.length);
                if(usernameText.length==0 || employmentIDText.length==0 || emailText.length==0){
                    alert('Required Field Can Not Be Left Blank');
                    return false;
                }
            }
        </script>
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
                    <li><a href="#">Profile</a></li>
                    <li class="current_page_item"><a href="#">User</a></li>
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
                        <!--<h1 class="title">Add New Facilities</h1>

                        <div class="post">
                            <h2 class="title">Lorem Ipsum Dolor Volutpat</h2>
                            <div class="post">
                                <p>Curabitur tellus. Phasellus tellus turpis, iaculis in, faucibus lobortis, posuere in, lorem. Donec a ante. Donec neque purus, adipiscing id, eleifend a, cursus vel, odio. Vivamus varius justo sit amet leo. Morbi sed libero. Vestibulum blandit augue at mi. Praesent fermentum lectus eget diam. Nam cursus, orci sit amet porttitor iaculis, ipsum massa aliquet nulla, non elementum mi elit a mauris. In hac habitasse platea.</p>
                                <p>Vestibulum blandit augue at mi. Praesent fermentum lectus eget diam. Nam cursus, orci sit amet porttitor iaculis, ipsum massa aliquet nulla, non elementum mi elit a mauris. In hac habitasse platea.</p>
                            </div>
                        </div> -->

                        <form action="TambahStaf" method="get">
                            <ul>
                                <li><label for="fullname">Full Name: </label><input type="text" name="fullname" id="fullname"></li>
                                <li>
                                  <label for="email">EmailL </label>
                                  <input type="text" name="email" id="email">
                                </li>
                                <li><label for="employmentID">Employment ID: </label><input type="text" name="employmentID" id="employmentID"></li>
                                
                                <li>
                                    <label for="position">position</label>
                                    <select name="position" id="position">
                                        <option value="0">Administrator</option>
                                        <option value="1">Receptionis</option>
                                        <option value="2">Manager</option>
                                    </select>
                                </li>
                                <input type="submit" value="Submit" onclick="return CekInput();">
                            </ul>
                        </form>

                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <div id="sidebar">
                      <ul>
                      <li>
                                <div id="sidebar-title">
                                    <h2>User Management</h2>
                                </div>
                                <ul>
                                    <li><a href="#">Add New Staff</a></li>
                                    <li><a href="#">Add New Customer</a></li>
                                    <li><a href="#">Manage Staff</a></li>
                                    <li><a href="#">Manage Customer</a></li>
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
