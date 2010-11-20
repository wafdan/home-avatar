<%-- 
    Document   : bheader
    Created on : Oct 27, 2010, 8:43:46 AM
    Author     : dhana
    Modified by: Wafdan
    Modified   : Nov 4, 2010
--%>
        <!-- start header -->
        <div id="logo-wrap">
            <div id="logo">
                <img src="../images/avatarlogo.png" alt="logo avatar"/>
            </div>
        </div>
        
        <div id="header">
            <%
            if ((session.getAttribute("name")) != null) {
            %>
            <div id="loginstatus" style="text-align: right">Yore're login as : <%=session.getAttribute("name")%>
                <a href="../Logout">Logout</a>
            </div>
            <%}%>
            <%
            int position = Integer.parseInt(session.getAttribute("position").toString());
            if ( position == 0) {
            %>
            <div id="menu">
                <ul>
                    <li><a id="bhm_prof" onclick="return switchid('bhm_prof')" href="profile_manage.jsp">Profile</a></li>
                    <li><a id="bhm_user" onclick="return switchid('bhm_user')" href="staff_manage.jsp">User</a></li>
                    <li style="visibility: hidden"><a id="bhm_facs" href="fac_room_manage.jsp">Facilities</a></li>
                    <li style="visibility: hidden"><a id="bhm_stat" href="statistic">Statistic</a></li>
                    <li style="visibility: hidden"><a id="bhm_resv" href="reservation_manage.jsp">Reservation</a></li>
                    <li style="visibility: hidden"><a id="bhm_pymt" href="payment_manage">Payment</a></li>
                </ul>
            </div>
            <%}else if (position==1){%>
            <div id="menu">
                <ul>
                    <li style="visibility: hidden"><a id="bhm_prof" onclick="return switchid('bhm_prof')" href="profile_manage.jsp">Profile</a></li>
                    <li style="visibility: hidden"><a id="bhm_user" onclick="return switchid('bhm_user')" href="staff_manage.jsp">User</a></li>
                    <li style="visibility: hidden"><a id="bhm_facs" href="fac_room_manage.jsp">Facilities</a></li>
                    <li style="visibility: hidden"><a id="bhm_stat" href="statistic">Statistic</a></li>
                    <li><a id="bhm_resv" href="reservation_manage.jsp">Reservation</a></li>
                    <li><a id="bhm_pymt" href="payment_manage">Payment</a></li>
                </ul>
            </div>
            <%} else if (position == 2) {%>
            <div id="menu">
                <ul>
                    <li style="visibility: hidden"><a id="bhm_prof" onclick="return switchid('bhm_prof')" href="profile_manage.jsp">Profile</a></li>
                    <li style="visibility: hidden"><a id="bhm_user" onclick="return switchid('bhm_user')" href="staff_manage.jsp">User</a></li>
                    <li><a id="bhm_facs" href="fac_room_manage.jsp">Facilities</a></li>
                    <li><a id="bhm_stat" href="statistic">Statistic</a></li>
                    <li style="visibility: hidden"><a id="bhm_resv" href="reservation_manage.jsp">Reservation</a></li>
                    <li style="visibility: hidden"><a id="bhm_pymt" href="payment_manage">Payment</a></li>
                </ul>
            </div>
            <%}%>


            
        </div>
        <!-- end header -->