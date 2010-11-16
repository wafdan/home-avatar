<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="AvatarEntity.OtherServicesJpaController"%>
<%@ page import="AvatarEntity.OtherServices"%>
<%@ page import="Layanan.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<jsp:include page="title.jsp"/>- Services</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>

<body id="top">
    <%
        MelihatLayananController ctrl = new MelihatLayananController();
        List<OtherServices> services = ctrl.getPublishedOtherServicesList();
        OtherServices cur;
        String pid = request.getParameter("id");
        if (pid == null) {
            cur = services.get(0);
        } else {
            cur = ctrl.getOtherServices(pid);
        }

        Boolean isLogin = false;
        if (session.getAttribute("name") != null) {
            isLogin = true;
        }
    %>
    
<jsp:include page="header.jsp"/>

<div class="wrapper col3">
  <div id="breadcrumb">
    <h1>Services</h1>
  </div>
</div>
<jsp:include page="cart.jsp"/>
<div class="wrapper col4">
  <div id="container">
    <div id="content">
      <%
        if (services == null) {
            out.println("<div class='entry'>");
            out.println("<p>No Service Availaible</p>");
            out.println("</div>");
        } else {
            if (services.size() < 1) {
                out.println("<div class='entry'>");
                out.println("<p>No Service Availaible</p>");
                out.println("</div>");
            } else {
                out.println("<h2 class='title'>" + cur.getProductType() + "</h2>");
                out.println("<br />");
                /*if (!isLogin) {
                out.println("<a class='book' href='index.jsp'>Add to Cart</a>");
                } else {
                out.println("<a class='book' href='#'>Add to Cart</a>");
                }*/
                out.println("<div class='entry'>");
                out.println("<p>" + cur.getDescription() + "</p><img src='" + cur.getImage() + "' />");
                out.println("</div>");
            }
        }
      %>
    </div>

    <div id="column">
    	<div class="holder">
            <%if ((session.getAttribute("name")) != null) {%>
                <h2 class="title"><img src="images/demo/cart.png" alt="" /><% out.print(session.getAttribute("cartSize")); %> Item(s) in Your Cart</h2>
            <%}/*else {*/%>
        </div>

      <div class="subnav">
        <h2>Service Type</h2>
        <ul>
           <%
                Iterator Iter = services.iterator();
                while (Iter.hasNext()) {
                    OtherServices srv = (OtherServices) Iter.next();
                    out.println("<li><a href='services.jsp?id=" + srv.getProductId() + "'>" + srv.getProductType() + "</a></li>");
                }
            %>
        </ul>
      </div>

    </div>
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
