<%-- 
    Document   : venue_edit
    Created on : 26 Okt 10, 20:12:34
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Layout" %>
<%@page import="AvatarEntity.LayoutJpaController" %>
<%@page import="AvatarEntity.Venue" %>
<%@page import="AvatarEntity.VenueLayout" %>
<%@page import="AvatarEntity.VenueLayoutJpaController" %>
<%@page import="java.util.List" %>
<%
            LayoutJpaController ljpa = new LayoutJpaController();
            Venue toEdit = (Venue) request.getAttribute("toEdit");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Backend Avatar</title>
        <link href="../styles/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
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
        <%
                    if ((session.getAttribute("name")) != null) {
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
                        <h1 class="title">Edit Venue</h1>
                        <form method="post" name="editVenue" id="editVenue" action="venue_edit">
                            <table border="0">
                                <tr>
                                    <th>Venue No</th>
                                    <th>Venue Name</th>
                                    <th>Description</th>
                                </tr>
                                <tr>
                                    <td><input type="text" name="venueNo" id="venueNo" value="<%= toEdit.getVenueNo()%>" size="4" maxlength="6" /></td>
                                    <td><input type="text" name="venueName" id="venueName" value="<%= toEdit.getVenueName()%>" size="15" maxlength="25"></td>
                                    <td><textarea name="description" id="description" value="<%= (toEdit.getDescription() != null ? toEdit.getDescription() : "")%>" cols="25" rows="2"></textarea></td>
                                </tr>
                            </table>
                            <h2>Layout Manager</h2>
                            <table border="0">
                                <% VenueLayoutJpaController vljpa = new VenueLayoutJpaController();
                                                        VenueLayout vl = null;
                                                        for (Layout lay : ljpa.findLayoutEntities()) {
                                                            vl = vljpa.findVenueLayout(toEdit.getVenueNo(), lay.getLayoutNo());
                                %>
                                <tr>
                                    <td><%= lay.getLayoutName()%></td><td>:</td>
                                    <td>
                                        <input type="text" name="layout_<%= lay.getLayoutNo()%>"
                                               id="layout_<%= lay.getLayoutNo()%>"
                                               value="<%= (vl != null ? "" + vl.getCapacity() : "")%>" size="3" maxlength="4" />
                                    </td>
                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="3" align="left">
                                        <input type="submit" name="update" id="update" value="Update" />
                                    </td>
                                </tr>
                            </table>
                        </form>

                        <br /> <hr /> <br />

                        <form name="logouploader" action="UploadVenueServlet?kodefasilitas=<%=request.getParameter("venueNo")%>" enctype="multipart/form-data" method="POST">
                            <div class="required">
                                <label for="file1">Change Venue Picture: </label>
                                <input type="file" name="picture" class="input_text" onchange="validateLogoExtension();"/>
                                <span id="uploadererror">
                                </span><br/>
                                <input name="logosubmit" type="Submit" value="Upload File" disabled="true"><br/>
                            </div>
                        </form>
                    </div>
                    <!-- end content -->
                    <!-- start sidebar -->
                    <jsp:include page="fac_sidebar.jsp" />
                    <!-- end sidebar -->
                    <div style="clear:both;">&nbsp;</div>
                </div>
            </div>
        </div>
        <!-- end page -->
        <%}%>
    </body>
</html>
