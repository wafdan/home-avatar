<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AvatarEntity.Profile"%>
<%@page import="AvatarEntity.ProfileJpaController"%>
<%@page import="AvatarEntity.*,java.sql.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
    <head profile="http://gmpg.org/xfn/11">
        <%
                    ProfileJpaController pjc=new ProfileJpaController();
                    Profile p=pjc.findProfile(Boolean.TRUE);
        %>
        <title><%=p.getHotelName() %> 