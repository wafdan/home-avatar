<%-- 
    Document   : bheader
    Created on : Oct 27, 2010, 8:43:46 AM
    Author     : dhana
--%>

<div id="logo-wrap">
            <div id="logo">
                <h1><a href="#">AVATAR</a></h1>
                <h2> Back End Management</h2>
            </div>
        </div>

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
                    <li><a href="profile_manage.jsp">Profile</a></li>
                    <li class="current_page_item"><a href="#">User</a></li>
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