<%-- 
    Document   : Edit
    Created on : 01 Okt 10, 13:42:46
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "java.util.List,java.util.Iterator,AvatarEntity.Customer,AvatarEntity.CustomerJpaController,AvatarEntity.Country,AvatarEntity.CountryJpaController" %>
<%
    //TODO: ganti dengan session beneran
    String username = String.valueOf(session.getAttribute("username"));
    CustomerJpaController control = new CustomerJpaController();
    Customer cust = control.findCustomer(username);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Update Pengguna</title>
        <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
        <link rel="stylesheet" type="text/css" href="view.css" media="all" />
        <style type="text/css">
        @import "slidertron.css";
        </style>
    </head>
    <body>
        <div id="header">
                    <div id="logo">
                        <h1><a href="#">Spons hotel</a></h1>
                    </div>
                    <div id="menu">
                            <ul>
                                    <li><a href="index.html">Overview</a></li>
                                    <li><a href="reservation.html" class="first">Reservation</a></li>
                                    <li><a href="rooms.html">Rooms</a></li>
                                    <li><a href="services.html">Services</a></li>
                                    <li><a href="resto.html">Restaurant & Meeting Room</a></li>
                                    <li><a href="contactus.html">Contact Us</a></li>
                            </ul>
                    </div>
                    <!-- end #menu -->
            </div>
            <!-- end #header -->
            <hr />
            <div id="page">
              <div id="page-bgtop">
                <div id="centerside">
                  <div id="wrap">
                    <form id="form_20674" class="appnitro"  method="post" action="UpdateAkun">

                            <div class="form_description">
                                    <h2>Edit User Account Form</h2>
                            </div>

                            <ul id="list_field">
                            <li id="li_1" >
                                    <label class="description" for="fullname">Full Name </label>
                                    <div>
                                            <input id="fullname" name="fullname" class="element text medium" type="text" maxlength="30" value="<%= cust.getName() %>"/>
                                            <%= request.getParameter("name") == null ? "" : "<div style=\"color:#ff00ff\">Invalid name format</div>" %>
                                    </div>
                            </li>
                            <li id="li_1" >
                                    <label class="description" for="npass">New Password <small>(leave blank if unneccessary to change)</small></label>
                                    <div>
                                            <input id="npass1" name="npass1" class="element text large" value="" type="password" />
                                            <label for="npass1">Type new password here</label>
                                    </div>

                                    <div>
                                            <input id="npass2" name="npass2" class="element text large" value="" type="password" />
                                            <label for="npass2">Retype new password here</label>
                                            <%= request.getParameter("pass") == null ? "" : "<div style=\"color:#ff00ff\">Unmatched passwords</div>" %>
                                    </div>
                            </li>

                            <li id="li_2" >
                                    <label class="description" for="identity_type">ID Type </label>
                                    <div>
                                            <select id="identity_type" name="identity_type" class="element select medium">
                                                <option value="Citizen ID" <%= cust.getIdentityType().equals("Citizen ID") ? "selected=\"selected\"" : "" %> >Citizen ID</option>
                                                <option value="Passport" <%= cust.getIdentityType().equals("Passport") ? "selected=\"selected\"" : "" %> >Passport</option>
                                            </select>
                                    </div>
                            </li>

                            <li id="li_3" >
                                    <label class="description" for="identity_number">ID Number </label>
                                    <div>
                                        <input id="identity_number" name="identity_number" class="element text medium" type="text" maxlength="50" value="<%= cust.getIdentityNumber() %>"/>
                                    </div>
                            </li>

                            <li id="li_4" >
                                    <label class="description" for="telephone">Phone Number <small>(+&lt;country_code&gt;&lt;number&gt;)</small> </label>
                                    <div>
                                            <input id="telephone" name="telephone" class="element text medium" type="text" maxlength="15" value="<%= cust.getTelephone()!=null ? cust.getTelephone() : "" %>"/>
                                            <%= request.getParameter("phone") == null ? "" : "<div style=\"color:#ff00ff\">Invalid telephone number format</div>" %>
                                    </div>
                            </li>

                            <li id="li_5" >
                            <label class="description" for="email">Email </label>
                            <div>
                                <input id="email" name="email" class="element text medium" type="text" maxlength="255" value="<%= cust.getEmail() %>"/>
                                <%= request.getParameter("email") == null ? "" : "<div style=\"color:#ff00ff\">Invalid email format</div>" %>
                            </div>
                            </li>

                            <li id="li_6" >
                            <label class="description" for="element_6">Address </label>

                            <div>
                                    <input id="address1" name="address1" class="element text large" value="<%= cust.getAddress1() %>" type="text" />
                                    <label for="address1">Street Address</label>
                            </div>

                            <div>
                                    <input id="address2" name="address2" class="element text large" value="<%= cust.getAddress2()!=null ? cust.getAddress2() : "" %>"
                                           type="text" />
                                    <label for="address2">Address Line 2</label>
                            </div>

                            <div class="left">
                                <input id="city" name="city" class="element text medium" value="<%= cust.getCity() %>" type="text" />
                                    <label for="city">City</label>
                            </div>

                            <div class="right">
                                <select class="element select medium" id="country" name="country">
<%
    CountryJpaController countryJC = new CountryJpaController();
    List<Country> listCountry = countryJC.findCountryEntities();
    Iterator<Country> iter = listCountry.iterator();
    Country country;
    while (iter.hasNext()) {
        country = iter.next();
        out.print("\t\t\t\t\t\t\t\t\t<option value=\"" + country.getCountryName() + "\"");
        if (country.getCountryName().equals(cust.getCountry())) out.print(" selected=\"selected\"");
        out.println(">" + country.getCountryName() + "</option>");
    }
%>
                                </select>
                                <label for="element_6_6">Country</label>
                            </div>
                            </li>

                                                    <li class="buttons">
                                        <input type="hidden" name="form_id" value="20674" />

                                            <input id="saveForm" class="button_text" type="submit" name="submit" value="Submit" />
                            </li>
                                    </ul>
                            </form>
                    </div>
                    <!--END wrap register-->
                  </div>
                  <!--END wrap-->
                </div>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>
                  <!-- end #content -->
                  <!-- end #sidebar -->
                </p>
                <div style="clear: both;">&nbsp;</div>
              </div>
                    <!-- end #page -->
            <div id="footer">
                    <p>Copyright (c) 2008 Sitename.com. All rights reserved. Design by <a href="http://www.freecsstemplates.org/">CSS Templates</a>.</p>
            </div>
            <!-- end #footer -->
    </body>
</html>
