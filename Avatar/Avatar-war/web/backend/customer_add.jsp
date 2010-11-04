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
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>

        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->

        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <div class="post">
                            <div class="box">
                                <h1>Tambah Customer</h1>
                                <form method="post" action="TambahCustomer">
                                    <div class="required">
                                        <label><span>Name :</span></label>
                                        <input class="input_text" name="name" id="name" type="text" />
                                    </div><br/>
                                    <div class="required">
                                        <label><span>Email :</span></label>
                                        <input class="input_text" name="email" id="email" type="text" />
                                    </div><br/>
                                    <div class="required">
                                        <label><span>ID Type :</span></label>
                                        <select name="idtype" id="idtype">
                                            <option value="KTP">KTP</option>
                                            <option value="SIM">SIM</option>
                                            <option value="Passport">Passport</option>
                                        </select>
                                    </div><br/>
                                    <div class="required">
                                        <label><span>ID Number :</span></label>
                                        <input class="input_text" name="idnumber" id="idnumber" type="text" />
                                    </div><br/>
                                    <div class="required">
                                        <label><span>Address 1 :</span></label>
                                        <textarea class="message" name="address1" id="address1"></textarea>
                                        <%--<input class="input_text" name="address1" id="address1" type="text" />--%>
                                    </div><br/>
                                    <div class="required">
                                        <label><span>Address 2 :</span></label>
                                        <textarea class="message" name="address2" id="address2"></textarea>
                                        <%--<input class="input_text" name="address2" id="address2" type="text" />--%>
                                    </div><br/>
                                    <div class="required">
                                        <label><span>City :</span></label>
                                        <input class="input_text" name="city" id="city" type="text" />
                                    </div><br/>
                                    <div class="required">
                                        <label><span>Country :</span></label>
                                        <input class="input_text" name="country" id="country" type="text" />
                                    </div><br/>
                                    <div class="required">
                                        <label><span>Telephone</span></label>
                                        <input class="input_text" name="phone" id="phone" type="text" />
                                    </div><br/>
                                    <div class="required">
                                        <label></label>
                                        <input class="button" value="Add" type="submit" />
                                    </div><br/>
                                </form>
                            </div>
                            <h2 class="title">&nbsp;</h2>
                            <div class="post"></div>
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
                                <hr />
                                <ul>
                                    <li>STAFF
                                        <ul>
                                            <li><a href="staff_add.jsp">Add New Staff</a></li>
                                            <li><a href="staff_manage.jsp">Manage Staff</a></li>
                                        </ul>
                                    </li>
                                    <hr />
                                    <li>CUSTOMER
                                        <ul>
                                            <li><a href="customer_add.jsp">Add New Customer</a></li>
                                            <li><a href="customer_manage.jsp">Manage Customer</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                <hr />
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

