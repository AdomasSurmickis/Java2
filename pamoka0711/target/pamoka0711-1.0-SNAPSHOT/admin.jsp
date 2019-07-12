<%-- 
    Document   : admin
    Created on : Jul 11, 2019, 6:52:07 PM
    Author     : Dedelis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("userName") == null){
    response.sendRedirect("login.jsp");
    return;
}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h1>Hello Admin!</h1>
    </body>
</html>
