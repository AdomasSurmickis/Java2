<%-- 
    Document   : index
    Created on : Jul 11, 2019, 6:14:11 PM
    Author     : Dedelis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%=session.getAttribute("userName")%> !</h1>
        <a href="logout">Logout</a>
        <a href="admin.jsp">Admin</a>
    </body>
</html>

<!--labas pov mygtukas logout;
forma pasakytu kas as esu ir mano pswd butu-->
