<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="Layanan.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
    <title>Hotel Graha Bandung - Registration</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta http-equiv="imagetoolbar" content="no" />
    <link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">

  <div class="wrapper col1">
  <div id="topbar">
    <%if ((session.getAttribute("name")) == null) {%>
    
    <%} else {%>
        <ul>
            <li>Welcome, <a href="myprofile.jsp"><%= session.getAttribute("name")%></a></li>
            <li class="last"><a href="Logout">Logout</a></li>
    	</ul>
    <%}%>
    <br class="clear" />
  </div>
</div>

<div class="wrapper col2">
  <div id="header">

    <div id="topnav">
      <ul>
        <li class="last"><a href="reservations.html">Reservation</a><span>make an order</span></li>
        <li><a href="services.jsp">Services</a><span>Our best services</span></li>
        <li><a href="hall.jsp">Events</a><span>Meeting and Conference</span></li>
        <li><a href="rooms.jsp">Rooms</a><span>Rooms and Facilities</span></li>
        <li><a href="index.jsp">Home</a><span></span></li>
      </ul>
    </div>

    <div id="logo">
    	<div id="logokiri">
        	<img class="imglogo" src="images/demo/logohotelgrahamini.png" alt="" />
        </div>
        <div id="logokanan">
        	<h1><a href="index.html">Hotel Graha</a></h1>
      		<p>The Best Luxury Hotel in Bandung</p>
        </div>
    </div>

    <br class="clear" />
  </div>
</div>

<div class="wrapper col3">
  <div id="breadcrumb">
    <h1>Rooms and Facilities</h1>
  </div>
</div>

<div class="wrapper col4">
  <div id="container">
    <div id="content">
        <h1>Registration Form</h1>
        <form id="formregistrasi" class="appnitro"  method="post" action="MelakukanRegistrasi/TambahAkun">

        <ul id="list_field">
        <li id="li_1" >
            <label class="description" for="fullname">Full Name </label>
            <div>
                <input id="fullname" name="fullname" class="element text medium" type="text" maxlength="30" value=""/>
                <% if (request.getParameter("name") != null) {
                    out.println("<div style=\"color:#ff00ff\">Invalid name format</div>");
                }%>
            </div>
        </li>

        <li id="li_2" >
            <label class="description" for="identity_type">ID Type </label>
            <div>
                <select id="identity_type" name="identity_type" class="element select medium">
                    <option value="Citizen ID" >Citizen ID</option>
                    <option value="Passport" >Passport</option>
                </select>
            </div>
        </li>

        <li id="li_3" >
            <label class="description" for="identity_number">ID Number </label>
            <div>
                <input id="identity_number" name="identity_number" class="element text medium" type="text" maxlength="25" value=""/>
            </div>
        </li>

        <li id="li_4" >
            <label class="description" for="telephone">Phone Number </label>
            <div>
                <input id="telephone" name="telephone" class="element text medium" type="text" maxlength="15" value=""/>
                <% if (request.getParameter("phone") != null) {
                    out.println("<div style=\"color:#ff00ff\">Invalid phone number format</div>");
                }%>
            </div>
        </li>

        <li id="li_5" >
            <label class="description" for="email">Email </label>
            <div>
                <input id="email" name="email" class="element text medium" type="text" maxlength="255" value=""/>
                <% if (request.getParameter("email") != null) {
                        out.println("<div style=\"color:#ff00ff\">Invalid email format</div>");
                    }%>
                <% if (request.getParameter("duplicate") != null) {
                        out.println("<div style=\"color:#ff00ff\">Email address already in use</div>");
                    }%>
            </div>
        </li>

        <li id="li_6" >
            <label class="description" for="element_6">Address </label>

            <div>
                <input id="address1" name="address1" class="element text large" value="" type="text" />
                <label for="address1">Street Address - Line 1</label>
            </div>

            <div>
                <input id="address2" name="address2" class="element text large" value="" type="text" />
                <label for="address2">Street Address - Line 2</label>
            </div>

            <div class="left">
                <input id="city" name="city" class="element text medium" value="" type="text" />
                <label for="city">City</label>
            </div>

        <div class="right">
            <select class="element text medium" id="country" name="country">
                <option value="" selected="selected"></option>
                <option value="Afghanistan" >Afghanistan</option>
                <option value="Albania" >Albania</option>
                <option value="Algeria" >Algeria</option>
                <option value="Andorra" >Andorra</option>
                <option value="Antigua and Barbuda" >Antigua and Barbuda</option>
                <option value="Argentina" >Argentina</option>
                <option value="Armenia" >Armenia</option>
                <option value="Australia" >Australia</option>
                <option value="Austria" >Austria</option>
                <option value="Azerbaijan" >Azerbaijan</option>
                <option value="Bahamas" >Bahamas</option>
                <option value="Bahrain" >Bahrain</option>
                <option value="Bangladesh" >Bangladesh</option>
                <option value="Barbados" >Barbados</option>
                <option value="Belarus" >Belarus</option>
                <option value="Belgium" >Belgium</option>
                <option value="Belize" >Belize</option>
                <option value="Benin" >Benin</option>
                <option value="Bhutan" >Bhutan</option>
                <option value="Bolivia" >Bolivia</option>
                <option value="Bosnia and Herzegovina" >Bosnia and Herzegovina</option>
                <option value="Botswana" >Botswana</option>
                <option value="Brazil" >Brazil</option>
                <option value="Brunei" >Brunei</option>
                <option value="Bulgaria" >Bulgaria</option>
                <option value="Burkina Faso" >Burkina Faso</option>
                <option value="Burundi" >Burundi</option>
                <option value="Cambodia" >Cambodia</option>
                <option value="Cameroon" >Cameroon</option>
                <option value="Canada" >Canada</option>
                <option value="Cape Verde" >Cape Verde</option>
                <option value="Central African Republic" >Central African Republic</option>
                <option value="Chad" >Chad</option>
                <option value="Chile" >Chile</option>
                <option value="China" >China</option>
                <option value="Colombia" >Colombia</option>
                <option value="Comoros" >Comoros</option>
                <option value="Congo" >Congo</option>
                <option value="Congo DR" >Congo, DR</option>
                <option value="Costa Rica" >Costa Rica</option>
                <option value="Côte d'Ivoire" >Côte d'Ivoire</option>
                <option value="Croatia" >Croatia</option>
                <option value="Cuba" >Cuba</option>
                <option value="Cyprus" >Cyprus</option>
                <option value="Czech Republic" >Czech Republic</option>
                <option value="Denmark" >Denmark</option>
                <option value="Djibouti" >Djibouti</option>
                <option value="Dominica" >Dominica</option>
                <option value="Dominican Republic" >Dominican Republic</option>
                <option value="East Timor" >East Timor</option>
                <option value="Ecuador" >Ecuador</option>
                <option value="Egypt" >Egypt</option>
                <option value="El Salvador" >El Salvador</option>
                <option value="Equatorial Guinea" >Equatorial Guinea</option>
                <option value="Eritrea" >Eritrea</option>
                <option value="Estonia" >Estonia</option>
                <option value="Ethiopia" >Ethiopia</option>
                <option value="Fiji" >Fiji</option>
                <option value="Finland" >Finland</option>
                <option value="France" >France</option>
                <option value="Gabon" >Gabon</option>
                <option value="Gambia" >Gambia</option>
                <option value="Georgia" >Georgia</option>
                <option value="Germany" >Germany</option>
                <option value="Ghana" >Ghana</option>
                <option value="Greece" >Greece</option>
                <option value="Grenada" >Grenada</option>
                <option value="Guatemala" >Guatemala</option>
                <option value="Guinea" >Guinea</option>
                <option value="Guinea-Bissau" >Guinea-Bissau</option>
                <option value="Guyana" >Guyana</option>
                <option value="Haiti" >Haiti</option>
                <option value="Honduras" >Honduras</option>
                <option value="Hong Kong" >Hong Kong</option>
                <option value="Hungary" >Hungary</option>
                <option value="Iceland" >Iceland</option>
                <option value="India" >India</option>
                <option value="Indonesia" >Indonesia</option>
                <option value="Iran" >Iran</option>
                <option value="Iraq" >Iraq</option>
                <option value="Ireland" >Ireland</option>
                <option value="Israel" >Israel</option>
                <option value="Italy" >Italy</option>
                <option value="Jamaica" >Jamaica</option>
                <option value="Japan" >Japan</option>
                <option value="Jordan" >Jordan</option>
                <option value="Kazakhstan" >Kazakhstan</option>
                <option value="Kenya" >Kenya</option>
                <option value="Kiribati" >Kiribati</option>
                <option value="North Korea" >North Korea</option>
                <option value="South Korea" >South Korea</option>
                <option value="Kuwait" >Kuwait</option>
                <option value="Kyrgyzstan" >Kyrgyzstan</option>
                <option value="Laos" >Laos</option>
                <option value="Latvia" >Latvia</option>
                <option value="Lebanon" >Lebanon</option>
                <option value="Lesotho" >Lesotho</option>
                <option value="Liberia" >Liberia</option>
                <option value="Libya" >Libya</option>
                <option value="Liechtenstein" >Liechtenstein</option>
                <option value="Lithuania" >Lithuania</option>
                <option value="Luxembourg" >Luxembourg</option>
                <option value="Macedonia" >Macedonia</option>
                <option value="Madagascar" >Madagascar</option>
                <option value="Malawi" >Malawi</option>
                <option value="Malaysia" >Malaysia</option>
                <option value="Maldives" >Maldives</option>
                <option value="Mali" >Mali</option>
                <option value="Malta" >Malta</option>
                <option value="Marshall Islands" >Marshall Islands</option>
                <option value="Mauritania" >Mauritania</option>
                <option value="Mauritius" >Mauritius</option>
                <option value="Mexico" >Mexico</option>
                <option value="Micronesia" >Micronesia</option>
                <option value="Moldova" >Moldova</option>
                <option value="Monaco" >Monaco</option>
                <option value="Mongolia" >Mongolia</option>
                <option value="Montenegro" >Montenegro</option>
                <option value="Morocco" >Morocco</option>
                <option value="Mozambique" >Mozambique</option>
                <option value="Myanmar" >Myanmar</option>
                <option value="Namibia" >Namibia</option>
                <option value="Nauru" >Nauru</option>
                <option value="Nepal" >Nepal</option>
                <option value="Netherlands" >Netherlands</option>
                <option value="New Zealand" >New Zealand</option>
                <option value="Nicaragua" >Nicaragua</option>
                <option value="Niger" >Niger</option>
                <option value="Nigeria" >Nigeria</option>
                <option value="Norway" >Norway</option>
                <option value="Oman" >Oman</option>
                <option value="Pakistan" >Pakistan</option>
                <option value="Palau" >Palau</option>
                <option value="Panama" >Panama</option>
                <option value="Papua New Guinea" >Papua New Guinea</option>
                <option value="Paraguay" >Paraguay</option>
                <option value="Peru" >Peru</option>
                <option value="Philippines" >Philippines</option>
                <option value="Poland" >Poland</option>
                <option value="Portugal" >Portugal</option>
                <option value="Puerto Rico" >Puerto Rico</option>
                <option value="Qatar" >Qatar</option>
                <option value="Romania" >Romania</option>
                <option value="Russia" >Russia</option>
                <option value="Rwanda" >Rwanda</option>
                <option value="Saint Kitts and Nevis" >Saint Kitts and Nevis</option>
                <option value="Saint Lucia" >Saint Lucia</option>
                <option value="Saint Vincent and the Grenadines" >Saint Vincent and the Grenadines</option>
                <option value="Samoa" >Samoa</option>
                <option value="San Marino" >San Marino</option>
                <option value="Sao Tome and Principe" >Sao Tome and Principe</option>
                <option value="Saudi Arabia" >Saudi Arabia</option>
                <option value="Senegal" >Senegal</option>
                <option value="Serbia and Montenegro" >Serbia and Montenegro</option>
                <option value="Seychelles" >Seychelles</option>
                <option value="Sierra Leone" >Sierra Leone</option>
                <option value="Singapore" >Singapore</option>
                <option value="Slovakia" >Slovakia</option>
                <option value="Slovenia" >Slovenia</option>
                <option value="Solomon Islands" >Solomon Islands</option>
                <option value="Somalia" >Somalia</option>
                <option value="South Africa" >South Africa</option>
                <option value="Spain" >Spain</option>
                <option value="Sri Lanka" >Sri Lanka</option>
                <option value="Sudan" >Sudan</option>
                <option value="Suriname" >Suriname</option>
                <option value="Swaziland" >Swaziland</option>
                <option value="Sweden" >Sweden</option>
                <option value="Switzerland" >Switzerland</option>
                <option value="Syria" >Syria</option>
                <option value="Taiwan" >Taiwan</option>
                <option value="Tajikistan" >Tajikistan</option>
                <option value="Tanzania" >Tanzania</option>
                <option value="Thailand" >Thailand</option>
                <option value="Togo" >Togo</option>
                <option value="Tonga" >Tonga</option>
                <option value="Trinidad and Tobago" >Trinidad and Tobago</option>
                <option value="Tunisia" >Tunisia</option>
                <option value="Turkey" >Turkey</option>
                <option value="Turkmenistan" >Turkmenistan</option>
                <option value="Tuvalu" >Tuvalu</option>
                <option value="Uganda" >Uganda</option>
                <option value="Ukraine" >Ukraine</option>
                <option value="United Arab Emirates" >United Arab Emirates</option>
                <option value="United Kingdom" >United Kingdom</option>
                <option value="United States" >United States</option>
                <option value="Uruguay" >Uruguay</option>
                <option value="Uzbekistan" >Uzbekistan</option>
                <option value="Vanuatu" >Vanuatu</option>
                <option value="Vatican City" >Vatican City</option>
                <option value="Venezuela" >Venezuela</option>
                <option value="Vietnam" >Vietnam</option>
                <option value="Yemen" >Yemen</option>
                <option value="Zambia" >Zambia</option>
                <option value="Zimbabwe" >Zimbabwe</option>
            </select>
            <label for="element_6_6">Country</label>
        </div>
    </li>

        </ul>
            <input name="submit" type="submit" id="submit" value="Register" />
       </form>

     </div>


    <div id="column">
    	<div class="holder">
            <%if ((session.getAttribute("name")) == null) {%>
                <h2 class="title">  Become our member!</h2>
            <%}/*else {*/%>
        </div>

      <div class="subnav">
        <h2>Room's Type</h2>
        <ul>
            
        </ul>
      </div>

    </div>
    <div class="clear"></div>
  </div>
</div>

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
</body>
</html>
