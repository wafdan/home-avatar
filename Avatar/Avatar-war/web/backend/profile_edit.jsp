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
<%
if(Integer.parseInt(session.getAttribute("position").toString()) == 0){
%>
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

        /*var validatePhoneNumber=function()
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
            }*/

        var validateLogoExtension=function(){
            var target=document.logouploader.file1.value;
            if(target!=""){
                var regex=/[.jpg|.jpeg|.bmp|.png]$/;
                if(!regex.test(target)){
                    document.getElementById("uploadererror").innerHTML="<em> &nbsp; &nbsp;&nbsp;&nbsp; Logo only .jpg, .jpeg, .bmp, or .png</em>";
                    document.logouploader.logosubmit.disabled=true;
                }
                else{
                    document.getElementById("uploadererror").innerHTML="";
                    document.logouploader.logosubmit.disabled=false;
                }
            }else{
                document.logouploader.logosubmit.disabled=true;
            }
        }

        var validateHotelPhoneFax=function(inputan){
            //kalo inputan 1 phone kalo 2 fax
            var nilaiobj;
            var errorobj;
            var apaini;
            if(inputan==1){
                nilaiobj=document.editprofil.hotel_phone;
                errorobj="phoneerror";
                apaini="phone";
            }else {
                nilaiobj=document.editprofil.hotel_fax;
                errorobj="faxerror";
                apaini="fax";
            }

            if(nilaiobj.value.length==1 || nilaiobj.value.length==0 || nilaiobj.value==""){
                nilaiobj.value="+";
                document.getElementById(errorobj).innerHTML="Please start with \"+\"";
            }else if(nilaiobj.value.length>15){
                nilaiobj.value=nilaiobj.value.substring(0, 15);
                document.getElementById(errorobj).innerHTML="Your "+apaini+" number can not exceed 15 characters";
            }
            else{
                var characterterakhir=nilaiobj.value.charAt(nilaiobj.value.length-1);
                if(isNaN(characterterakhir)){
                    nilaiobj.value=nilaiobj.value.substring(0, nilaiobj.value.length-1);
                    document.getElementById(errorobj).innerHTML=apaini+" number can only contain number 0 - 9";
                }
                else{
                    document.getElementById(errorobj).innerHTML="";
                }
            }


        }

        var validateAccountNumber=function(idx){
            var nilaiobj;
            if(idx==1)
                nilaiobj=document.editprofil.account_no_1;
            else
                nilaiobj=document.editprofil.account_no_2;

            if(nilaiobj.value.length==0 && idx==1){
                document.getElementById("account_no_"+idx+"_err").innerHTML="Account Number "+idx+" can not left blank";
                return false;
            } else if(nilaiobj.value.length>25){
                document.getElementById("account_no_"+idx+"_err").innerHTML="Account Number limited to 25 characters";
                return false;
            }
            else {
                document.getElementById("account_no_"+idx+"_err").innerHTML="";
                return true;
            }
        }


        var validateAccountName=function(idx){
            var nilaiobj;
            if(idx==1)
                nilaiobj=document.editprofil.account_name_1;
            else
                nilaiobj=document.editprofil.account_name_2;

            if(nilaiobj.value.length==0 && idx==1){
                document.getElementById("account_name_"+idx+"_err").innerHTML="Account name 1 can not left blank";
                return false;
            }else if(nilaiobj.value.length>50){
                document.getElementById("account_name_"+idx+"_err").innerHTML="Account name is limited to 50 characters";
                return false;
            }else {
                document.getElementById("account_name_"+idx+"_err").innerHTML="";
                return true;
            }
        }

        var validateBankName=function(idx){
            var nilaiobj;
            if(idx==1){
                nilaiobj=document.editprofil.bank_name_1;
            }
            else{
                nilaiobj=document.editprofil.bank_name_2;
            }

            if(nilaiobj.value.length==0 && idx==1){
                document.getElementById("bank_name_"+idx+"_err").innerHTML="Bank name 1 can not left blank";
                return false;
            }else if(nilaiobj.value.length>50){
                document.getElementById("bank_name_"+idx+"_err").innerHTML="Bank name limited to 50 characters";
                return false;
            }else {
                document.getElementById("account_name_"+idx+"_err").innerHTML="";
                return true;
            }
        }

        var validateAll=function(){
            if(validateHotelName() && validateHotelAddress() && validateCity() && validateEmail() && validateAccountNumber (1)
                && validateAccountName(1) && validateBankName(1) && validateAccountNumber(2) && validateAccountName(2) &&
                validateBankName(2)){}
            else{
                alert("There is error(s) on your inputted data. Please check and try again ");
                return false;
            }
        }

        </script>
    </head>
    <body>

        <%
                    Profile p;
                    ProfileJpaController pjc = new ProfileJpaController();
                    p = pjc.findProfile(Boolean.TRUE);
        %>

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
                            <h1>Edit Hotel's Profile</h1>

                            <form method="POST" action="EditProfilHotel" name="editprofil">
                                <div class="required">
                                    <label> <span>Hotel name :</span></label>
                                    <span id="lhotelname"></span>
                                    <input onblur="validateHotelName();" class="input_text" name="hotelname" id="hotelname" type="text" value="<%= p.getHotelName()%>"/>
                                </div>
                                <br/>
                                <div class="required">
                                    <label> <span>Hotel Address :</span></label>
                                    <span id="laddress"></span>
                                    <textarea class="message" name="address" id="address"><%=p.getHotelAddress1()%></textarea>
                                    <%--<input onblur="validateHotelAddress(1);" class="input_text" name="address" id="address" type="text" value="<%=p.getHotelAddress1()%>"/>--%>
                                </div>
                                <br/>
                                <div class="required">
                                    <label for="hotel_phone">Hotel Phone Number</label><input name="hotel_phone" type="text" value="<%= p.getHotelPhone() %>" onkeyup="validateHotelPhoneFax(1);"/><div id="phoneerror" style="color: red"></div><br />
                                    <label for="hotel_fax">Hotel Fax Number</label> <input name="hotel_fax" type="text" value="<%= p.getHotelFax() %>" onkeyup="validateHotelPhoneFax(2);" /><div id="faxerror" style="color: red" ></div>
                                </div>
                                <br/>
                                <div class="required">
                                    <label><span>City :</span></label>
                                    <span id="lcity"></span>
                                    <input onblur="validateCity();" class="input_text" name="city" id="city" type="text" value="<%=p.getHotelCity()%>"/>
                                </div>
                                <br/>
                                <div class="required">
                                    <label><span>Email :</span></label>
                                    <span id="lemail"></span>
                                    <input onblur="validateEmail();" class="input_text" name="email" id="email" type="text" value="<%=p.getHotelEmail()%>"/>
                                </div>
                                <br/>
                                <div class="required">
                                    <label><span>Country :</span></label>
                                    <select class="element select medium" id="country" name="country">
                                        <option value="" selected="selected">Choose one...</option>
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
                                        <option value="Indonesia">Indonesia</option>
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
                                </div>
                                <br/>
                                <div class="required">
                                    <label><span>Hotel Description :</span></label>
                                    <textarea class="message" name="hoteldesc" id="hoteldesc"><%=p.getHotelDescription()%></textarea>
                                </div>
                                <br/>

                                <br/>


                                <label for="account_number_1">Account Number 1</label> <input name="account_no_1" type="text" value="<%=p.getAccountNumber1() %>" onblur="validateAccountNumber(1);"/> <div id="account_no_1_err"></div> <br /><br />
                                <label for="bank_name_1">Bank Name 1</label> <input name="bank_name_1" type="text" value="<%=p.getBankName1() %>" onblur="validateBankName(1);"/> <div id="bank_name_1_err"></div><br /><br />
                                <label for="account_name_1">Account Name 1</label><input name="account_name_1" type="text" value="<%=p.getAccountName1() %>" onblur="validateAccountName(1);"/> <div id="account_name_1_err"></div><br /><br />

                                <label for="account_number_2">Account Number 2</label> <input name="account_no_2" value="<%=p.getAccountNumber2() %>" type="text" onblur="validateAccountNumber(2);"/><div id="account_no_2_err"></div> <br /><br />
                                <label for="bank_name_2">Bank Name 2</label> <input name="bank_name_2" type="text" value="<%=p.getBankName2() %>" onblur="validateBankName(2);"/> <div id="bank_name_2_err"></div><br /><br />
                                <label for="account_name_2">Account Name 2</label><input name="account_name_2" value="<%=p.getAccountName2() %>" type="text" onblur="validateAccountName(2);"/> <div id="account_name_2_err"></div><br /><br />

                                <div style="text-align: left;">
                                    <label style="visibility: hidden;">dummy</label>
                                    <input class="button" value="Save" type="submit" onclick="return validateAll();"/>
                                </div>


                            </form>
                            <hr/>
                            <form name="logouploader" action="CommonsFileUploadServlet" enctype="multipart/form-data" method="POST">
                                <div class="required">
                                    <label for="file1">Hotel Logo :</label>
                                    <input type="file" name="file1" class="input_text" onchange="validateLogoExtension();"/>
                                    <span id="uploadererror">
                                    </span><br/>
                                    <input name="logosubmit" type="Submit" value="Upload File" disabled="true"><br/>
                                </div>
                            </form>

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
<%
}else{
    out.println(session.getAttribute("position"));
    response.sendRedirect(request.getContextPath() +"/backend/");
    }
%>
