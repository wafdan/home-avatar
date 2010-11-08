<%-- 
    Document   : bheader
    Created on : Oct 27, 2010, 8:43:46 AM
    Author     : dhana
    Modified by: Wafdan
    Modified   : Nov 4, 2010
--%>
        <!-- start header -->
        <div id="header">
            <%
            if ((session.getAttribute("name")) != null) {
            %>
            <div id="loginstatus" style="text-align: right">Anda Login sebagai : <%=session.getAttribute("name")%>
                <a href="../Logout">Logout</a>
            </div>
            <%}%>
            <div id="menu">
                <ul>
                    <li><a id="bhm_prof" onclick="return switchid('bhm_prof')" href="profile_manage.jsp">Profile</a></li>
                    <li><a id="bhm_user" onclick="return switchid('bhm_user')" href="staff_manage.jsp">User</a></li>
                    <li><a id="bhm_facs" href="fac_room_manage.jsp">Facilities</a></li>
                    <li><a id="bhm_stat" href="statistic">Statistic</a></li>
                    <li><a id="bhm_resv" href="reservation_manage.jsp">Reservation</a></li>
                    <li><a id="bhm_pymt" href="payment_manage">Payment</a></li>
                </ul>
            </div>
        </div>
        <!-- end header -->