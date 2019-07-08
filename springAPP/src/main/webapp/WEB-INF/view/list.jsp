<%-- 
    Document   : list
    Created on : Jul 4, 2019, 8:16:38 PM
    Author     : Dedelis
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!--iraukta jstl (supaparstina jsp rasyma)-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            List l = (List) request.getAttribute("Listas");

        %>
        <%=l.toString()%>

        ${Listas}
    </body>
</html>
