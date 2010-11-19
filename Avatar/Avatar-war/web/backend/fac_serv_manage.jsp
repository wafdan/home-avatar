
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page language="java" import="java.sql.*" %>
<%@page import="AvatarEntity.Accomodation" %>
<%@page import="AvatarEntity.AccomodationJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
<%@page import="AvatarEntity.OtherServices" %>
<%@page import="AvatarEntity.OtherServicesJpaController" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            Locale locale = Locale.getDefault();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>BackEnd Avatar</title>
        <link href="../styles/backend_facilities.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function confirmAction()
            {return confirm("Do you really want to delete?")}

            var validateLogoExtension=function(){
                var target=document.logouploader.picture.value;
                if(target!=""){
                    var regex=/[.jpg|.jpeg|.bmp|.png]$/;
                    if(!regex.test(target)){
                        document.getElementById("uploadererror").innerHTML="<em> &nbsp; &nbsp;&nbsp;&nbsp; Logo only .jpg, jpeg, .bmp, or .png</em>";
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
        </script>
    </head>
    <body>
        <!-- start header -->
        <jsp:include page="bheader.jsp"/>
        <!-- end header -->
        <!-- start page -->
        <div id="wrapper">
            <div id="wrapper-btm">
                <div id="page">
                    <!-- start content -->
                    <div id="content">
                        <h1 class="title">Services List</h1>
                        <ul id="fmenu">
                            <li id="fmenu-item1"><a href="fac_room_manage.jsp">Rooms</a></li>
                            <li id="fmenu-item2"><a href="fac_hall_manage.jsp">Meetings & Events</a></li>
                            <li id="fmenu-item3"><a href="#">Other Services</a></li>
                        </ul>
                        <div class="post">
                            <div class="fac1">
                                <table align = "center" border = 1 width = "100%" cellpadding = "3" cellspacing = "0">
                                    <%
                                                int editIndex = 0;
                                                String Fac = request.getParameter("Fac");
                                                try {
                                                    String Index = request.getParameter("edit");
                                                    editIndex = Integer.parseInt(Index);
                                                } catch (NullPointerException ex) {
                                                    editIndex = -1;
                                                } catch (NumberFormatException ex) {
                                                    editIndex = -1;
                                                }

                                                int index = 0;
                                                OtherServicesJpaController jpah = new OtherServicesJpaController();
                                                List<OtherServices> hList = jpah.findOtherServicesEntities();
                                                if (editIndex == -1) {
                                    %>
                                    <tr>
                                        <th bgcolor="#262626" width="20">No.</th>
                                        <th bgcolor="#262626" width="50">Product Id</th>
                                        <th bgcolor="#262626" width="89">Product Type</th>
                                        <th bgcolor="#262626" width="100">Description</th>
                                        <th bgcolor="#262626" width="96">Image</th>
                                        <th bgcolor="#262626">Pricing Unit</th>
                                        <th bgcolor="#262626">Unit Price</th>
                                        <th bgcolor="#262626">Published</th>
                                        <th colspan="2" bgcolor="#262626"></th>
                                    </tr>
                                    <%for (Iterator<OtherServices> i = hList.iterator(); i.hasNext();) {
                                                                                            OtherServices temp = i.next();
                                    %>
                                    <tr>
                                            <td style="vertical-align: middle"><%index++;
                                            out.write(Integer.toString(index));%></td>
                                        <td style="vertical-align: middle"><% out.write(temp.getProductId());%></td>
                                        <td style="vertical-align: middle"><% out.write(temp.getProductType());%></td>
                                        <td style="vertical-align: middle"><% out.write(temp.getDescription());%></td>
                                        <td style="vertical-align: middle"><img src="../<%=temp.getImage()%>" alt="logo <%=temp.getProductType()%>" width="125" /></td>
                                        <td style="vertical-align: middle"><% out.write(temp.getPricingUnit());%></td>
                                        <td style="vertical-align: middle"><% out.write(currencyFormat.format(temp.getUnitPrice()));%></td>
                                        <td style="vertical-align: middle"><% out.write(String.valueOf(temp.getPublished()));%></td>
                                        <td style="vertical-align: middle;width: 20px;" align="center"><div style="overflow:auto"><a href="fac_serv_manage.jsp?edit=<%out.write(Integer.toString(index));%>">edit</a></div></td>
                                        <td style="vertical-align: middle;width: 20px;" align="center"><div style="overflow:auto"><a onclick="return confirmAction()" href="HapusServ?delete=<% out.write(temp.getProductId());%>">delete</a></div></td>
                                    </tr>
                                    <%}
                                                                                    } else {
                                                                                        int iterator = 0;
                                                                                        for (Iterator<OtherServices> i = hList.iterator(); i.hasNext();) {
                                                                                            OtherServices temp = i.next();
                                                                                            iterator++;
                                                                                            index++;
                                                                                            if (iterator == editIndex) {%>
                                    <form name="logouploader" action="UploadServiceServlet?kodefasilitas=<%=temp.getProductId()%>" enctype="multipart/form-data" method="POST">
                                        <div class="required">
                                            <label for="file1">Change Picture: </label>
                                            <input type="file" name="picture" class="input_text" onchange="validateLogoExtension();"/>
                                            <span id="uploadererror">
                                            </span><br/>
                                            <input name="logosubmit" type="Submit" value="Upload File" disabled="true"><br/>
                                        </div>

                                    </form>
                                    <br /> <hr /> <br />
                                    <form action="EditServ" method="post">
                                        <tr>
                                            <th bgcolor="#262626" width="25%">No</th>
                                            <th bgcolor="#262626" width="75%"><%out.write(Integer.toString(index));%></th>
                                        </tr>
                                        <tr>
                                            <td>Product Id   :</td>
                                            <td><input type="text" value= "<%= temp.getProductId()%>" id="pid" name="pid" disabled="true" maxlength="6"/></td>
                                        </tr>
                                        <tr>
                                            <td>Product Type :</td>
                                            <td><input type="text" value= "<%= temp.getProductType()%>" id="type" name="type" maxlength="25"/></td>
                                        </tr>
                                        <tr>
                                            <td>Description  :</td>
                                            <td><textarea id="desc" name="desc" cols=75% rows=3><%= temp.getDescription()%></textarea></td>
                                        </tr>
                                        <tr>
                                            <td>Image  :</td>
                                            <td><input type="text" value= "<%= temp.getImage()%>" id="img" name="img" /></td>
                                        </tr>
                                        <tr>
                                            <td>Pricing Unit :</td>
                                            <td><input type="text" value= "<%= temp.getPricingUnit()%>" id="prcu" name="prcu" maxlength="10"/></td>
                                        </tr>
                                        <tr>
                                            <td>Unit Price :</td>
                                            <td><input type="text" value= "<%= temp.getUnitPrice()%>" id="uprc" name="uprc" /></td>
                                        </tr>
                                        <tr>
                                            <td>Published :</td>
                                            <td>
                                                <% if (temp.getPublished()) {%>
                                                <input style="width:20px" type="radio" name="pub" id="pub" value="true" checked="checked" />Yes
                                                <input style="width:20px" type="radio" name="pub" id="pub" value="false" />No
                                                <%} else {%>
                                                <input style="width:20px" type="radio" name="pub" id="pub" value="true" />Yes
                                                <input style="width:20px" type="radio" name="pub" id="pub" value="false" checked="checked"/>No
                                                <%}%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            </td>
                                            <td>
                                                <input type="submit" value="save" onclick="this.form.pid.disabled=false;" />
                                                <a onclick="return confirmAction()" href="HapusServ?delete=%3C%%20out.write(temp.getProductId());%%3E">
                                                    delete</a>
                                                <a href="fac_serv_manage.jsp">
                                                    cancel</a>
                                            </td>
                                        </tr>
                                    </form>

                                    <%}
                                                    }
                                                }%>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="fac_sidebar.jsp" />
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
