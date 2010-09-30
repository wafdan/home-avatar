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
                        <div class="post">
                            <div class="box">
                                <h1>tambah staff</h1>
                                <form action="TambahStaf" method="get">
                                    <label> <span>Full name</span>
                                        <input class="input_text" name="name" id="name" type="text" />
                                    </label>
                                    <label> <span>Email</span>
                                        <input class="input_text" name="email" id="email" type="text" />
                                    </label>
                                    <label> <span>Employment ID</span>
                                        <input class="input_text" name="subject" id="subject" type="text" />
                                    </label>
                                    <label> <span>Position</span>
                                        <select name="position" id="position">
                                            <option value="0">Administrator</option>
                                            <option value="1">Receptionis</option>
                                            <option value="2">Manager</option>
                                        </select>
                                    </label>
                                    <label>
                                        <input class="button" value="Add" type="submit" />
                                    </label>
                                </form>
                            </div>
                            <h2 class="title">&nbsp;</h2>
                            <div class="post">
                                <%
                                            try {
                                                if (request == null) {
                                                } else {
                                                    String param = request.getParameter("status");
                                                    if (param.equals("success")) {
                                                        out.write("Add staff success");
                                                    } else if (param.equals("alreadyexist")) {
                                                        out.write("Staff with that username and 4-first-digit Employment ID is already exist");
                                                    } else if (param.equals("unexpectedfailure")) {
                                                        out.write("Unexpected Failure Happened");
                                                    }
                                                }
                                            } catch (NullPointerException ex) {
                                            }

                                %>
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
