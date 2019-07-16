<%-- 
    Document   : index
    Created on : Jul 16, 2019, 6:15:08 PM
    Author     : Dedelis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="savetodo">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input name="todoText">
            <input type="submit" value="add">
        </form>
        <ul>
            <c:forEach items="${todos}" var="todo">
                <li>
                    ${todo.todoText}
                    <a href="deletetodo?id=${todo.id}">Delete</a>
                    <!--prideti patikrinima ar todo pabaigta (finished date NEnull), jei null, tada atsiranda mygtukas--> 
                    <!--complete;-->
                    <!--papildomai reik task'u sarasas, jos turi turet delete/ complete/ , prie to do pridetas taskas-->
                    
                </li>
            </c:forEach>
        </ul>
        <a href="logout">Logout</a>
    </body>
</html>
