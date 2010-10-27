<%-- 
    Document   : TambahCustomer
    Created on : 29 Sep 10, 19:47:02
    Author     : zulfikar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Profile" %>
<%@page import="AvatarEntity.ProfileJpaController" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Udah ditambah validasi pake javascript-->

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
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
        <script language="javascript" type="text/javascript" >
            var validateHotelName=function()
            {
                var textfieldlength=document.getElementById("hotelname").value.length;
                if(textfieldlength==0 || textfieldlength>50){
                    document.getElementById("lhotelname").innerHTML='<em>Must be between 1 and 50 characters</em>';
                    return false;
                }
                else{
                    document.getElementById("lhotelname").innerHTML='';
                    return true;
                }
                

            }

            var validateHotelAddress=function(idx)
            {
                var textfieldlength=document.getElementById("address").value.length;
                var textfieldlength2=document.getElementById("address2").value.length;
                if(textfieldlength>=1 && textfieldlength<=75 && textfieldlength2>=1 && textfieldlength2<=75){
                    document.getElementById("laddress").innerHTML='';
                    return true;
                }
                else{
                    if(idx==1)
                        document.getElementById("laddress").innerHTML='<em>Address line 1 must be between 1 and 75 characters</em>';
                    else
                        document.getElementById("laddress2").innerHTML='<em>Address line 2 must be between 1 and 75 characters</em>';
                    return false;
                }

            }

            var validateCity=function()
            {
                var textfieldlength=document.getElementById("city").value.length;
                if(textfieldlength>=1 && textfieldlength<=25){
                    document.getElementById("lcity").innerHTML='';
                    return true;
                }
                else{
                    document.getElementById("lcity").innerHTML='<em>Must be between 1 and 25 characters</em>';
                    return false;
                }
            }

            var validateEmail=function()
            {
                target = document.getElementById("email").value;
                regex = /^[A-Za-z0-9._]+@[A-Za-z0-9._]+\.[A-Za-z]{2,4}$/;
                if(!regex.test(target)) {
                    document.getElementById("lemail").innerHTML='<em>Your email is not in valid format</em>';
                    return false;
                } else {
                    document.getElementById("lemail").innerHTML='';
                    return true;
                }

            }

            var validatePhoneNumber=function()
            {
                target=document.getElementById("phonenumber").value;
                regex = /^\+[0-9]{1,14}$/;
                if(!regex.test(target)){
                    document.getElementById("lphonenumber").innerHTML='<em>Must between 1 and 15 character and start with "+"</em>'
                    return false;
                }
                else {
                    document.getElementById("lphonenumber").innerHTML='';
                    return true;
                }
            }

        </script>
    </head>
    <body>
        <div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>
        <%
                    Profile p;
                    ProfileJpaController pjc = new ProfileJpaController();
                    p = pjc.findProfile(Boolean.TRUE);
        %>

        <!-- start header -->
        <div id="header">
            <%
                        if ((session.getAttribute("name")) != null) {
            %>
            <div id="loginstatus">Anda Login sebagai : <%=session.getAttribute("name")%>
                <a href="../Logout">Logout</a>
            </div>
            <%}%>
            <div id="menu">
                <ul>
                    <li class="current_page_item"><a href="#">Profile</a></li>
                    <li><a href="staff_manage.jsp">User</a></li>
                    <li><a href="fac_room_manage.jsp">Facilities</a></li>
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
                                <form method="get" action="EditProfilHotel">
                                    <label> <span>Hotel name</span>
                                    </label><span id="lhotelname"></span>
                                    <input onblur="validateHotelName();" class="input_text" name="hotelname" id="hotelname" type="text" value="<%= p.getHotelName()%>"/><br/>
                                    <label> <span>Hotel Address</span>
                                    </label> <span id="laddress"></span>
                                    <input onblur="validateHotelAddress(1);" class="input_text" name="address" id="address" type="text" value="<%=p.getHotelAddress1()%>"/>
                                    <br/>
                                    <label> <span>&nbsp;</span>
                                    </label> <span id="laddress2"></span>
                                    <input onblur="validateHotelAddress(2);" class="input_text" name="address2" id="address2" type="text" value="<%=p.getHotelAddress2()%>"/>
                                    <br/>
                                    <label>
                                        <span>City</span>
                                    </label> <span id="lcity"></span>
                                    <input onblur="validateCity();" class="input_text" name="city" id="city" type="text" value="<%=p.getHotelCity()%>"/>
                                    <br/>
                                    <label>
                                        <span>Phone Number </span>
                                    </label> <span id="lphonenumber"></span>
                                    <input onblur="validatePhoneNumber();" class="input_text" name="phonenumber" id="phonenumber" type="text" value="<%=p.getHotelPhone()%>"/>
                                    <br/>
                                    <label> <span>Email</span>
                                    </label> <span id="lemail"></span>
                                    <input onblur="validateEmail();" class="input_text" name="email" id="email" type="text" value="<%=p.getHotelEmail()%>"/>
                                    <br/>
                                    <label> <span>Country</span>
                                    </label>

                                    <input class="input_text" name="imagedirectory" id="imagedirectory" type="file" value=""/>
                                    <br/>
                                    <label> <span> Hotel Description</span>
                                    </label>
                                    <textarea class="message" name="hoteldesc" id="hoteldesc"><%=p.getHotelDescription()%></textarea><br/>
                                    <input class="button" value="Simpan" type="submit" />
                                    <br/>
                                </form>

                                <form action="CommonsFileUploadServlet" enctype="multipart/form-data" method="POST">
                                    <input type="file" name="file1"><br>
                                    <input type="Submit" value="Upload File"><br>
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
                                    <li><a href="profile_manage.jsp">Show Profile</a></li>
                                    <li><a href="profile_edit.jsp">Edit Profile</a></li>
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

