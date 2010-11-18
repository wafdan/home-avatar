<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.*,java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page import = "java.util.List,java.util.Iterator,AvatarEntity.Customer,AvatarEntity.CustomerJpaController" %>
<%
            String username = session.getAttribute("username").toString();
            //TODO: ganti dengan session beneran
            CustomerJpaController control = new CustomerJpaController();
            Customer cust = control.findCustomer(username);
%>

<jsp:include page="title.jsp"/>- My Profile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta http-equiv="imagetoolbar" content="no" />
    <link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">

<jsp:include page="header.jsp"/>

<div class="wrapper col4">
  <div id="container">
    <div id="content">
        <h1>My Profile</h1>
            <form id="accountform" method="post" action="TambahAkun">

                            <h2>User Account Information</h2><br />
                            <label for="username">Username : </label> <%= cust.getUsername()%><br /><br />
                            <label for="name">Name : </label> <%= cust.getName()%><br /><br />
                            <label for="identity_type">Identity Type : </label><%= cust.getIdentityType()%><br /><br />
                            <label for="identity_number">Identity Number : </label> <%= cust.getIdentityNumber()%><br /><br />
                            <label for="telephone">Telephone : </label> <%= cust.getTelephone()%><br /><br />
                            <label for="email">Email : </label> <%= cust.getEmail()%><br /><br />
                            <label for="address1">Address : </label> <%= cust.getAddress1()%><br /><br />
                            <% if (cust.getAddress2() != null) {
                                            out.println(cust.getAddress2() + "<br />");
                                        } else {
                                            out.println("");
                                        }%>
                            <%= cust.getCity()%>, <%= cust.getCountry()%>

                            <br /><a href="editprofile.jsp">Edit</a>
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
