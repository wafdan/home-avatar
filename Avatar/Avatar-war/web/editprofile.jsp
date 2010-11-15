<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="Layanan.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%
    String username = session.getAttribute("username").toString();
    //TODO: ganti dengan session beneran
    CustomerJpaController control = new CustomerJpaController();
    Customer cust = control.findCustomer(username);
%>
<jsp:include page="title.jsp"/>- Edit Profile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta http-equiv="imagetoolbar" content="no" />
    <link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">

<jsp:include page="header.jsp"/>

<div class="wrapper col4">
  <div id="container">
    <div id="content">
        <h1>Edit Profile</h1>
        <form id="form_20674" class="appnitro"  method="post" action="MengubahInformasiPengguna/UpdateAkun">

        <ul id="list_field">
        <li id="li_1" >
            <label class="description" for="fullname">Full Name </label>
            <div>
                <input id="fullname" name="fullname" class="element text medium" type="text" maxlength="30" value="<%= cust.getName() %>"/>
                <% if (request.getParameter("name") != null) {
                    out.println("<div style=\"color:#ff00ff\">Invalid name format</div>");
                }%>
            </div>
        </li>

        <li id="li_2" >
            <label class="description" for="identity_type">ID Type </label>
            <div>
                <select id="identity_type" name="identity_type" class="element select medium">
                    <option value="Citizen ID"<%= cust.getIdentityType().equals("Citizen ID") ? " selected=\"selected\"" : "" %>>Citizen ID</option>
                    <option value="Passport"<%= cust.getIdentityType().equals("Passport") ? " selected=\"selected\"" : "" %>>Passport</option>
                </select>
            </div>
        </li>

        <li id="li_3" >
            <label class="description" for="identity_number">ID Number </label>
            <div>
                <input id="identity_number" name="identity_number" class="element text medium" type="text" maxlength="25" value="<%= cust.getIdentityNumber() %>"/>
            </div>
        </li>

        <li id="li_4" >
            <label class="description" for="telephone">Phone Number </label>
            <div>
                <input id="telephone" name="telephone" class="element text medium" type="text" maxlength="15" value="<%= cust.getTelephone() %>"/>
                <% if (request.getParameter("phone") != null) {
                    out.println("<div style=\"color:#ff00ff\">Invalid phone number format</div>");
                } %>
            </div>
        </li>

        <li id="li_5" >
            <label class="description" for="email">Email </label>
            <div>
                <input id="email" name="email" class="element text medium" type="text" maxlength="255" value="<%= cust.getEmail() %>"/>
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
                <input id="address1" name="address1" class="element text large" value="<%= cust.getAddress1() %>" type="text" />
                <label for="address1">Street Address - Line 1</label>
            </div>

            <div>
                <input id="address2" name="address2" class="element text large" value="<%= (cust.getAddress2() != null ? cust.getAddress2() : "") %>" type="text" />
                <label for="address2">Street Address - Line 2</label>
            </div>

            <div class="left">
                <input id="city" name="city" class="element text medium" value="<%= cust.getCity() %>" type="text" />
                <label for="city">City</label>
            </div>

            <div class="left">
                <select class="element text medium" id="country" name="country">
                    <option value="Afghanistan"<%= (cust.getCountry().equals("Afghanistan") ? " selected=\"selected\"" : "") %>>Afghanistan</option>
                    <option value="Albania"<%= (cust.getCountry().equals("Albania") ? " selected=\"selected\"" : "") %>>Albania</option>
                    <option value="Algeria"<%= (cust.getCountry().equals("Algeria") ? " selected=\"selected\"" : "") %>>Algeria</option>
                    <option value="Andorra"<%= (cust.getCountry().equals("Andorra") ? " selected=\"selected\"" : "") %>>Andorra</option>
                    <option value="Antigua and Barbuda"<%= (cust.getCountry().equals("Antigua and Barbuda") ? " selected=\"selected\"" : "") %>>Antigua and Barbuda</option>
                    <option value="Argentina"<%= (cust.getCountry().equals("Argentina") ? " selected=\"selected\"" : "") %>>Argentina</option>
                    <option value="Armenia"<%= (cust.getCountry().equals("Armenia") ? " selected=\"selected\"" : "") %>>Armenia</option>
                    <option value="Australia"<%= (cust.getCountry().equals("Australia") ? " selected=\"selected\"" : "") %>>Australia</option>
                    <option value="Austria"<%= (cust.getCountry().equals("Austria") ? " selected=\"selected\"" : "") %>>Austria</option>
                    <option value="Azerbaijan"<%= (cust.getCountry().equals("Azerbaijan") ? " selected=\"selected\"" : "") %>>Azerbaijan</option>
                    <option value="Bahamas"<%= (cust.getCountry().equals("Bahamas") ? " selected=\"selected\"" : "") %>>Bahamas</option>
                    <option value="Bahrain"<%= (cust.getCountry().equals("Bahrain") ? " selected=\"selected\"" : "") %>>Bahrain</option>
                    <option value="Bangladesh"<%= (cust.getCountry().equals("Bangladesh") ? " selected=\"selected\"" : "") %>>Bangladesh</option>
                    <option value="Barbados"<%= (cust.getCountry().equals("Barbados") ? " selected=\"selected\"" : "") %>>Barbados</option>
                    <option value="Belarus"<%= (cust.getCountry().equals("Belarus") ? " selected=\"selected\"" : "") %>>Belarus</option>
                    <option value="Belgium"<%= (cust.getCountry().equals("Belgium") ? " selected=\"selected\"" : "") %>>Belgium</option>
                    <option value="Belize"<%= (cust.getCountry().equals("Belize") ? " selected=\"selected\"" : "") %>>Belize</option>
                    <option value="Benin"<%= (cust.getCountry().equals("Benin") ? " selected=\"selected\"" : "") %>>Benin</option>
                    <option value="Bhutan"<%= (cust.getCountry().equals("Bhutan") ? " selected=\"selected\"" : "") %>>Bhutan</option>
                    <option value="Bolivia"<%= (cust.getCountry().equals("Bolivia") ? " selected=\"selected\"" : "") %>>Bolivia</option>
                    <option value="Bosnia and Herzegovina"<%= (cust.getCountry().equals("Bosnia and Herzegovina") ? " selected=\"selected\"" : "") %>>Bosnia and Herzegovina</option>
                    <option value="Botswana"<%= (cust.getCountry().equals("Botswana") ? " selected=\"selected\"" : "") %>>Botswana</option>
                    <option value="Brazil"<%= (cust.getCountry().equals("Brazil") ? " selected=\"selected\"" : "") %>>Brazil</option>
                    <option value="Brunei"<%= (cust.getCountry().equals("Brunei") ? " selected=\"selected\"" : "") %>>Brunei</option>
                    <option value="Bulgaria"<%= (cust.getCountry().equals("Bulgaria") ? " selected=\"selected\"" : "") %>>Bulgaria</option>
                    <option value="Burkina Faso"<%= (cust.getCountry().equals("Burkina Faso") ? " selected=\"selected\"" : "") %>>Burkina Faso</option>
                    <option value="Burundi"<%= (cust.getCountry().equals("Burundi") ? " selected=\"selected\"" : "") %>>Burundi</option>
                    <option value="Cambodia"<%= (cust.getCountry().equals("Cambodia") ? " selected=\"selected\"" : "") %>>Cambodia</option>
                    <option value="Cameroon"<%= (cust.getCountry().equals("Cameroon") ? " selected=\"selected\"" : "") %>>Cameroon</option>
                    <option value="Canada"<%= (cust.getCountry().equals("Canada") ? " selected=\"selected\"" : "") %>>Canada</option>
                    <option value="Cape Verde"<%= (cust.getCountry().equals("Cape Verde") ? " selected=\"selected\"" : "") %>>Cape Verde</option>
                    <option value="Central African Republic"<%= (cust.getCountry().equals("Central African Republic") ? " selected=\"selected\"" : "") %>>Central African Republic</option>
                    <option value="Chad"<%= (cust.getCountry().equals("Chad") ? " selected=\"selected\"" : "") %>>Chad</option>
                    <option value="Chile"<%= (cust.getCountry().equals("Chile") ? " selected=\"selected\"" : "") %>>Chile</option>
                    <option value="China"<%= (cust.getCountry().equals("China") ? " selected=\"selected\"" : "") %>>China</option>
                    <option value="Colombia"<%= (cust.getCountry().equals("Colombia") ? " selected=\"selected\"" : "") %>>Colombia</option>
                    <option value="Comoros"<%= (cust.getCountry().equals("Comoros") ? " selected=\"selected\"" : "") %>>Comoros</option>
                    <option value="Congo"<%= (cust.getCountry().equals("Congo") ? " selected=\"selected\"" : "") %>>Congo</option>
                    <option value="Congo DR"<%= (cust.getCountry().equals("Congo DR") ? " selected=\"selected\"" : "") %>>Congo, DR</option>
                    <option value="Costa Rica"<%= (cust.getCountry().equals("Costa Rica") ? " selected=\"selected\"" : "") %>>Costa Rica</option>
                    <option value="Côte d'Ivoire"<%= (cust.getCountry().equals("Côte d'Ivoire") ? " selected=\"selected\"" : "") %>>Côte d'Ivoire</option>
                    <option value="Croatia"<%= (cust.getCountry().equals("Croatia") ? " selected=\"selected\"" : "") %>>Croatia</option>
                    <option value="Cuba"<%= (cust.getCountry().equals("Cuba") ? " selected=\"selected\"" : "") %>>Cuba</option>
                    <option value="Cyprus"<%= (cust.getCountry().equals("Cyprus") ? " selected=\"selected\"" : "") %>>Cyprus</option>
                    <option value="Czech Republic"<%= (cust.getCountry().equals("Czech Republic") ? " selected=\"selected\"" : "") %>>Czech Republic</option>
                    <option value="Denmark"<%= (cust.getCountry().equals("Denmark") ? " selected=\"selected\"" : "") %>>Denmark</option>
                    <option value="Djibouti"<%= (cust.getCountry().equals("Djibouti") ? " selected=\"selected\"" : "") %>>Djibouti</option>
                    <option value="Dominica"<%= (cust.getCountry().equals("Dominica") ? " selected=\"selected\"" : "") %>>Dominica</option>
                    <option value="Dominican Republic"<%= (cust.getCountry().equals("Dominican Republic") ? " selected=\"selected\"" : "") %>>Dominican Republic</option>
                    <option value="East Timor"<%= (cust.getCountry().equals("East Timor") ? " selected=\"selected\"" : "") %>>East Timor</option>
                    <option value="Ecuador"<%= (cust.getCountry().equals("Ecuador") ? " selected=\"selected\"" : "") %>>Ecuador</option>
                    <option value="Egypt"<%= (cust.getCountry().equals("Egypt") ? " selected=\"selected\"" : "") %>>Egypt</option>
                    <option value="El Salvador"<%= (cust.getCountry().equals("El Salvador") ? " selected=\"selected\"" : "") %>>El Salvador</option>
                    <option value="Equatorial Guinea"<%= (cust.getCountry().equals("Equatorial Guinea") ? " selected=\"selected\"" : "") %>>Equatorial Guinea</option>
                    <option value="Eritrea"<%= (cust.getCountry().equals("Eritrea") ? " selected=\"selected\"" : "") %>>Eritrea</option>
                    <option value="Estonia"<%= (cust.getCountry().equals("Estonia") ? " selected=\"selected\"" : "") %>>Estonia</option>
                    <option value="Ethiopia"<%= (cust.getCountry().equals("Ethiopia") ? " selected=\"selected\"" : "") %>>Ethiopia</option>
                    <option value="Fiji"<%= (cust.getCountry().equals("Fiji") ? " selected=\"selected\"" : "") %>>Fiji</option>
                    <option value="Finland"<%= (cust.getCountry().equals("Finland") ? " selected=\"selected\"" : "") %>>Finland</option>
                    <option value="France"<%= (cust.getCountry().equals("France") ? " selected=\"selected\"" : "") %>>France</option>
                    <option value="Gabon"<%= (cust.getCountry().equals("Gabon") ? " selected=\"selected\"" : "") %>>Gabon</option>
                    <option value="Gambia"<%= (cust.getCountry().equals("Gambia") ? " selected=\"selected\"" : "") %>>Gambia</option>
                    <option value="Georgia"<%= (cust.getCountry().equals("Georgia") ? " selected=\"selected\"" : "") %>>Georgia</option>
                    <option value="Germany"<%= (cust.getCountry().equals("Germany") ? " selected=\"selected\"" : "") %>>Germany</option>
                    <option value="Ghana"<%= (cust.getCountry().equals("Ghana") ? " selected=\"selected\"" : "") %>>Ghana</option>
                    <option value="Greece"<%= (cust.getCountry().equals("Greece") ? " selected=\"selected\"" : "") %>>Greece</option>
                    <option value="Grenada"<%= (cust.getCountry().equals("Grenada") ? " selected=\"selected\"" : "") %>>Grenada</option>
                    <option value="Guatemala"<%= (cust.getCountry().equals("Guatemala") ? " selected=\"selected\"" : "") %>>Guatemala</option>
                    <option value="Guinea"<%= (cust.getCountry().equals("Guinea") ? " selected=\"selected\"" : "") %>>Guinea</option>
                    <option value="Guinea-Bissau"<%= (cust.getCountry().equals("Guinea-Bissau") ? " selected=\"selected\"" : "") %>>Guinea-Bissau</option>
                    <option value="Guyana"<%= (cust.getCountry().equals("Guyana") ? " selected=\"selected\"" : "") %>>Guyana</option>
                    <option value="Haiti"<%= (cust.getCountry().equals("Haiti") ? " selected=\"selected\"" : "") %>>Haiti</option>
                    <option value="Honduras"<%= (cust.getCountry().equals("Honduras") ? " selected=\"selected\"" : "") %>>Honduras</option>
                    <option value="Hong Kong"<%= (cust.getCountry().equals("Hong Kong") ? " selected=\"selected\"" : "") %>>Hong Kong</option>
                    <option value="Hungary"<%= (cust.getCountry().equals("Hungary") ? " selected=\"selected\"" : "") %>>Hungary</option>
                    <option value="Iceland"<%= (cust.getCountry().equals("Iceland") ? " selected=\"selected\"" : "") %>>Iceland</option>
                    <option value="India"<%= (cust.getCountry().equals("India") ? " selected=\"selected\"" : "") %>>India</option>
                    <option value="Indonesia"<%= (cust.getCountry().equals("Indonesia") ? " selected=\"selected\"" : "") %>>Indonesia</option>
                    <option value="Iran"<%= (cust.getCountry().equals("Iran") ? " selected=\"selected\"" : "") %>>Iran</option>
                    <option value="Iraq"<%= (cust.getCountry().equals("Iraq") ? " selected=\"selected\"" : "") %>>Iraq</option>
                    <option value="Ireland"<%= (cust.getCountry().equals("Ireland") ? " selected=\"selected\"" : "") %>>Ireland</option>
                    <option value="Israel"<%= (cust.getCountry().equals("Israel") ? " selected=\"selected\"" : "") %>>Israel</option>
                    <option value="Italy"<%= (cust.getCountry().equals("Italy") ? " selected=\"selected\"" : "") %>>Italy</option>
                    <option value="Jamaica"<%= (cust.getCountry().equals("Jamaica") ? " selected=\"selected\"" : "") %>>Jamaica</option>
                    <option value="Japan"<%= (cust.getCountry().equals("Japan") ? " selected=\"selected\"" : "") %>>Japan</option>
                    <option value="Jordan"<%= (cust.getCountry().equals("Jordan") ? " selected=\"selected\"" : "") %>>Jordan</option>
                    <option value="Kazakhstan"<%= (cust.getCountry().equals("Kazakhstan") ? " selected=\"selected\"" : "") %>>Kazakhstan</option>
                    <option value="Kenya"<%= (cust.getCountry().equals("Kenya") ? " selected=\"selected\"" : "") %>>Kenya</option>
                    <option value="Kiribati"<%= (cust.getCountry().equals("Kiribati") ? " selected=\"selected\"" : "") %>>Kiribati</option>
                    <option value="North Korea"<%= (cust.getCountry().equals("North Korea") ? " selected=\"selected\"" : "") %>>North Korea</option>
                    <option value="South Korea"<%= (cust.getCountry().equals("South Korea") ? " selected=\"selected\"" : "") %>>South Korea</option>
                    <option value="Kuwait"<%= (cust.getCountry().equals("Kuwait") ? " selected=\"selected\"" : "") %>>Kuwait</option>
                    <option value="Kyrgyzstan"<%= (cust.getCountry().equals("Kyrgyzstan") ? " selected=\"selected\"" : "") %>>Kyrgyzstan</option>
                    <option value="Laos"<%= (cust.getCountry().equals("Laos") ? " selected=\"selected\"" : "") %>>Laos</option>
                    <option value="Latvia"<%= (cust.getCountry().equals("Latvia") ? " selected=\"selected\"" : "") %>>Latvia</option>
                    <option value="Lebanon"<%= (cust.getCountry().equals("Lebanon") ? " selected=\"selected\"" : "") %>>Lebanon</option>
                    <option value="Lesotho"<%= (cust.getCountry().equals("Lesotho") ? " selected=\"selected\"" : "") %>>Lesotho</option>
                    <option value="Liberia"<%= (cust.getCountry().equals("Liberia") ? " selected=\"selected\"" : "") %>>Liberia</option>
                    <option value="Libya"<%= (cust.getCountry().equals("Libya") ? " selected=\"selected\"" : "") %>>Libya</option>
                    <option value="Liechtenstein"<%= (cust.getCountry().equals("Liechtenstein") ? " selected=\"selected\"" : "") %>>Liechtenstein</option>
                    <option value="Lithuania"<%= (cust.getCountry().equals("Lithuania") ? " selected=\"selected\"" : "") %>>Lithuania</option>
                    <option value="Luxembourg"<%= (cust.getCountry().equals("Luxembourg") ? " selected=\"selected\"" : "") %>>Luxembourg</option>
                    <option value="Macedonia"<%= (cust.getCountry().equals("Macedonia") ? " selected=\"selected\"" : "") %>>Macedonia</option>
                    <option value="Madagascar"<%= (cust.getCountry().equals("Madagascar") ? " selected=\"selected\"" : "") %>>Madagascar</option>
                    <option value="Malawi"<%= (cust.getCountry().equals("Malawi") ? " selected=\"selected\"" : "") %>>Malawi</option>
                    <option value="Malaysia"<%= (cust.getCountry().equals("Malaysia") ? " selected=\"selected\"" : "") %>>Malaysia</option>
                    <option value="Maldives"<%= (cust.getCountry().equals("Maldives") ? " selected=\"selected\"" : "") %>>Maldives</option>
                    <option value="Mali"<%= (cust.getCountry().equals("Mali") ? " selected=\"selected\"" : "") %>>Mali</option>
                    <option value="Malta"<%= (cust.getCountry().equals("Malta") ? " selected=\"selected\"" : "") %>>Malta</option>
                    <option value="Marshall Islands"<%= (cust.getCountry().equals("Marshall Islands") ? " selected=\"selected\"" : "") %>>Marshall Islands</option>
                    <option value="Mauritania"<%= (cust.getCountry().equals("Mauritania") ? " selected=\"selected\"" : "") %>>Mauritania</option>
                    <option value="Mauritius"<%= (cust.getCountry().equals("Mauritius") ? " selected=\"selected\"" : "") %>>Mauritius</option>
                    <option value="Mexico"<%= (cust.getCountry().equals("Mexico") ? " selected=\"selected\"" : "") %>>Mexico</option>
                    <option value="Micronesia"<%= (cust.getCountry().equals("Micronesia") ? " selected=\"selected\"" : "") %>>Micronesia</option>
                    <option value="Moldova"<%= (cust.getCountry().equals("Moldova") ? " selected=\"selected\"" : "") %>>Moldova</option>
                    <option value="Monaco"<%= (cust.getCountry().equals("Monaco") ? " selected=\"selected\"" : "") %>>Monaco</option>
                    <option value="Mongolia"<%= (cust.getCountry().equals("Mongolia") ? " selected=\"selected\"" : "") %>>Mongolia</option>
                    <option value="Montenegro"<%= (cust.getCountry().equals("Montenegro") ? " selected=\"selected\"" : "") %>>Montenegro</option>
                    <option value="Morocco"<%= (cust.getCountry().equals("Morocco") ? " selected=\"selected\"" : "") %>>Morocco</option>
                    <option value="Mozambique"<%= (cust.getCountry().equals("Mozambique") ? " selected=\"selected\"" : "") %>>Mozambique</option>
                    <option value="Myanmar"<%= (cust.getCountry().equals("Myanmar") ? " selected=\"selected\"" : "") %>>Myanmar</option>
                    <option value="Namibia"<%= (cust.getCountry().equals("Namibia") ? " selected=\"selected\"" : "") %>>Namibia</option>
                    <option value="Nauru"<%= (cust.getCountry().equals("Nauru") ? " selected=\"selected\"" : "") %>>Nauru</option>
                    <option value="Nepal"<%= (cust.getCountry().equals("Nepal") ? " selected=\"selected\"" : "") %>>Nepal</option>
                    <option value="Netherlands"<%= (cust.getCountry().equals("Netherlands") ? " selected=\"selected\"" : "") %>>Netherlands</option>
                    <option value="New Zealand"<%= (cust.getCountry().equals("New Zealand") ? " selected=\"selected\"" : "") %>>New Zealand</option>
                    <option value="Nicaragua"<%= (cust.getCountry().equals("Nicaragua") ? " selected=\"selected\"" : "") %>>Nicaragua</option>
                    <option value="Niger"<%= (cust.getCountry().equals("Niger") ? " selected=\"selected\"" : "") %>>Niger</option>
                    <option value="Nigeria"<%= (cust.getCountry().equals("Nigeria") ? " selected=\"selected\"" : "") %>>Nigeria</option>
                    <option value="Norway"<%= (cust.getCountry().equals("Norway") ? " selected=\"selected\"" : "") %>>Norway</option>
                    <option value="Oman"<%= (cust.getCountry().equals("Oman") ? " selected=\"selected\"" : "") %>>Oman</option>
                    <option value="Pakistan"<%= (cust.getCountry().equals("Pakistan") ? " selected=\"selected\"" : "") %>>Pakistan</option>
                    <option value="Palau"<%= (cust.getCountry().equals("Palau") ? " selected=\"selected\"" : "") %>>Palau</option>
                    <option value="Panama"<%= (cust.getCountry().equals("Panama") ? " selected=\"selected\"" : "") %>>Panama</option>
                    <option value="Papua New Guinea"<%= (cust.getCountry().equals("Papua New Guinea") ? " selected=\"selected\"" : "") %>>Papua New Guinea</option>
                    <option value="Paraguay"<%= (cust.getCountry().equals("Paraguay") ? " selected=\"selected\"" : "") %>>Paraguay</option>
                    <option value="Peru"<%= (cust.getCountry().equals("Peru") ? " selected=\"selected\"" : "") %>>Peru</option>
                    <option value="Philippines"<%= (cust.getCountry().equals("Philippines") ? " selected=\"selected\"" : "") %>>Philippines</option>
                    <option value="Poland"<%= (cust.getCountry().equals("Poland") ? " selected=\"selected\"" : "") %>>Poland</option>
                    <option value="Portugal"<%= (cust.getCountry().equals("Portugal") ? " selected=\"selected\"" : "") %>>Portugal</option>
                    <option value="Puerto Rico"<%= (cust.getCountry().equals("Puerto Rico") ? " selected=\"selected\"" : "") %>>Puerto Rico</option>
                    <option value="Qatar"<%= (cust.getCountry().equals("Qatar") ? " selected=\"selected\"" : "") %>>Qatar</option>
                    <option value="Romania"<%= (cust.getCountry().equals("Romania") ? " selected=\"selected\"" : "") %>>Romania</option>
                    <option value="Russia"<%= (cust.getCountry().equals("Russia") ? " selected=\"selected\"" : "") %>>Russia</option>
                    <option value="Rwanda"<%= (cust.getCountry().equals("Rwanda") ? " selected=\"selected\"" : "") %>>Rwanda</option>
                    <option value="Saint Kitts and Nevis"<%= (cust.getCountry().equals("Saint Kitts and Nevis") ? " selected=\"selected\"" : "") %>>Saint Kitts and Nevis</option>
                    <option value="Saint Lucia"<%= (cust.getCountry().equals("Saint Lucia") ? " selected=\"selected\"" : "") %>>Saint Lucia</option>
                    <option value="Saint Vincent and the Grenadines"<%= (cust.getCountry().equals("Saint Vincent and the Grenadines") ? " selected=\"selected\"" : "") %>>Saint Vincent and the Grenadines</option>
                    <option value="Samoa"<%= (cust.getCountry().equals("Samoa") ? " selected=\"selected\"" : "") %>>Samoa</option>
                    <option value="San Marino"<%= (cust.getCountry().equals("San Marino") ? " selected=\"selected\"" : "") %>>San Marino</option>
                    <option value="Sao Tome and Principe"<%= (cust.getCountry().equals("Sao Tome and Principe") ? " selected=\"selected\"" : "") %>>Sao Tome and Principe</option>
                    <option value="Saudi Arabia"<%= (cust.getCountry().equals("Saudi Arabia") ? " selected=\"selected\"" : "") %>>Saudi Arabia</option>
                    <option value="Senegal"<%= (cust.getCountry().equals("Senegal") ? " selected=\"selected\"" : "") %>>Senegal</option>
                    <option value="Serbia and Montenegro"<%= (cust.getCountry().equals("Serbia and Montenegro") ? " selected=\"selected\"" : "") %>>Serbia and Montenegro</option>
                    <option value="Seychelles"<%= (cust.getCountry().equals("Seychelles") ? " selected=\"selected\"" : "") %>>Seychelles</option>
                    <option value="Sierra Leone"<%= (cust.getCountry().equals("Sierra Leone") ? " selected=\"selected\"" : "") %>>Sierra Leone</option>
                    <option value="Singapore"<%= (cust.getCountry().equals("Singapore") ? " selected=\"selected\"" : "") %>>Singapore</option>
                    <option value="Slovakia"<%= (cust.getCountry().equals("Slovakia") ? " selected=\"selected\"" : "") %>>Slovakia</option>
                    <option value="Slovenia"<%= (cust.getCountry().equals("Slovenia") ? " selected=\"selected\"" : "") %>>Slovenia</option>
                    <option value="Solomon Islands"<%= (cust.getCountry().equals("Solomon Islands") ? " selected=\"selected\"" : "") %>>Solomon Islands</option>
                    <option value="Somalia"<%= (cust.getCountry().equals("Somalia") ? " selected=\"selected\"" : "") %>>Somalia</option>
                    <option value="South Africa"<%= (cust.getCountry().equals("South Africa") ? " selected=\"selected\"" : "") %>>South Africa</option>
                    <option value="Spain"<%= (cust.getCountry().equals("Spain") ? " selected=\"selected\"" : "") %>>Spain</option>
                    <option value="Sri Lanka"<%= (cust.getCountry().equals("Sri Lanka") ? " selected=\"selected\"" : "") %>>Sri Lanka</option>
                    <option value="Sudan"<%= (cust.getCountry().equals("Sudan") ? " selected=\"selected\"" : "") %>>Sudan</option>
                    <option value="Suriname"<%= (cust.getCountry().equals("Suriname") ? " selected=\"selected\"" : "") %>>Suriname</option>
                    <option value="Swaziland"<%= (cust.getCountry().equals("Swaziland") ? " selected=\"selected\"" : "") %>>Swaziland</option>
                    <option value="Sweden"<%= (cust.getCountry().equals("Sweden") ? " selected=\"selected\"" : "") %>>Sweden</option>
                    <option value="Switzerland"<%= (cust.getCountry().equals("Switzerland") ? " selected=\"selected\"" : "") %>>Switzerland</option>
                    <option value="Syria"<%= (cust.getCountry().equals("Syria") ? " selected=\"selected\"" : "") %>>Syria</option>
                    <option value="Taiwan"<%= (cust.getCountry().equals("Taiwan") ? " selected=\"selected\"" : "") %>>Taiwan</option>
                    <option value="Tajikistan"<%= (cust.getCountry().equals("Tajikistan") ? " selected=\"selected\"" : "") %>>Tajikistan</option>
                    <option value="Tanzania"<%= (cust.getCountry().equals("Tanzania") ? " selected=\"selected\"" : "") %>>Tanzania</option>
                    <option value="Thailand"<%= (cust.getCountry().equals("Thailand") ? " selected=\"selected\"" : "") %>>Thailand</option>
                    <option value="Togo"<%= (cust.getCountry().equals("Togo") ? " selected=\"selected\"" : "") %>>Togo</option>
                    <option value="Tonga"<%= (cust.getCountry().equals("Tonga") ? " selected=\"selected\"" : "") %>>Tonga</option>
                    <option value="Trinidad and Tobago"<%= (cust.getCountry().equals("Trinidad and Tobago") ? " selected=\"selected\"" : "") %>>Trinidad and Tobago</option>
                    <option value="Tunisia"<%= (cust.getCountry().equals("Tunisia") ? " selected=\"selected\"" : "") %>>Tunisia</option>
                    <option value="Turkey"<%= (cust.getCountry().equals("Turkey") ? " selected=\"selected\"" : "") %>>Turkey</option>
                    <option value="Turkmenistan"<%= (cust.getCountry().equals("Turkmenistan") ? " selected=\"selected\"" : "") %>>Turkmenistan</option>
                    <option value="Tuvalu"<%= (cust.getCountry().equals("Tuvalu") ? " selected=\"selected\"" : "") %>>Tuvalu</option>
                    <option value="Uganda"<%= (cust.getCountry().equals("Uganda") ? " selected=\"selected\"" : "") %>>Uganda</option>
                    <option value="Ukraine"<%= (cust.getCountry().equals("Ukraine") ? " selected=\"selected\"" : "") %>>Ukraine</option>
                    <option value="United Arab Emirates"<%= (cust.getCountry().equals("United Arab Emirates") ? " selected=\"selected\"" : "") %>>United Arab Emirates</option>
                    <option value="United Kingdom"<%= (cust.getCountry().equals("United Kingdom") ? " selected=\"selected\"" : "") %>>United Kingdom</option>
                    <option value="United States"<%= (cust.getCountry().equals("United States") ? " selected=\"selected\"" : "") %>>United States</option>
                    <option value="Uruguay"<%= (cust.getCountry().equals("Uruguay") ? " selected=\"selected\"" : "") %>>Uruguay</option>
                    <option value="Uzbekistan"<%= (cust.getCountry().equals("Uzbekistan") ? " selected=\"selected\"" : "") %>>Uzbekistan</option>
                    <option value="Vanuatu"<%= (cust.getCountry().equals("Vanuatu") ? " selected=\"selected\"" : "") %>>Vanuatu</option>
                    <option value="Vatican City"<%= (cust.getCountry().equals("Vatican City") ? " selected=\"selected\"" : "") %>>Vatican City</option>
                    <option value="Venezuela"<%= (cust.getCountry().equals("Venezuela") ? " selected=\"selected\"" : "") %>>Venezuela</option>
                    <option value="Vietnam"<%= (cust.getCountry().equals("Vietnam") ? " selected=\"selected\"" : "") %>>Vietnam</option>
                    <option value="Yemen"<%= (cust.getCountry().equals("Yemen") ? " selected=\"selected\"" : "") %>>Yemen</option>
                    <option value="Zambia"<%= (cust.getCountry().equals("Zambia") ? " selected=\"selected\"" : "") %>>Zambia</option>
                    <option value="Zimbabwe"<%= (cust.getCountry().equals("Zimbabwe") ? " selected=\"selected\"" : "") %>>Zimbabwe</option>
                </select>
                <label for="element_6_6">Country</label>
            </div>
        </li>
        <li id="li_7" >
            <label class="description" for="element_7">Change Password (fill only to change) </label>

            <div>
                <input id="opass" name="opass" class="element text medium" value="" type="password" />
                <label for="opass">Old Password</label>
            </div>

            <div>
                <input id="npass1" name="npass1" class="element text medium" value="" type="password" />
                <label for="npass1">New Password</label>
            </div>

            <div>
                <input id="npass2" name="npass2" class="element text medium" value="" type="password" />
                <label for="npass2">Retype New Password</label>
            </div>
            <% int pstat = (request.getParameter("pass") != null ? Integer.parseInt(request.getParameter("pass")) : 0);
            if (pstat == 1) {
                out.println("<div style=\"color:#ff00ff\">Password entries do not match</div>");
            } else if (pstat == 2) {
                out.println("<div style=\"color:#ff00ff\">Email address already in use</div>");
            } %>
        </li>
        </ul>
            <input name="submit" type="submit" id="submit" value="Submit" />
       </form>
     </div>


    <div id="column">

      <div class="subnav">
          <%if ((session.getAttribute("name")) == null) {%>
            <h2 class="title">  Become our member!</h2>
            <p>Discover just how rewarding membership can be.</p>
            <p>Join our membership now to access exclusive benefits, flexible reward options, fast online booking and the finest hotels and resorts in the world. </p>
            <p>Enrollment is quick, easy and free.</p>
          <%}/*else {*/%>
      </div>

    </div>
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
