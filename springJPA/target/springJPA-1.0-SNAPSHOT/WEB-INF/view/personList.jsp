<%-- 
    Document   : index
    Created on : Jun 27, 2019, 5:40:10 PM
    Author     : Dedelis
--%>

<%@page import="java.util.List"%>
<%@page import="lt.bit.springjpa.db.Persons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JPA Spring</title>
    </head>
    <body>
        <h1>Peson list</h1>
        <ul> 
            <%
                List<Persons> list = (List<Persons>)request.getAttribute("personsList");
                for (Persons p : list) {
            %>
            <li>
                Person ID: <%=p.getId()%> First name: <%=p.getFirstName()%> Last name: <%=p.getLastName()%> 
                Birth date: <%=p.getBirthDate()%> Salary: <%=p.getSalary()%>
                <a href="addresses?idx=<%=p.getId()%>">ADDRESSES</a>
                <a href="contacts?idx=<%=p.getId()%>">CONTACTS</a>
                <a href="edit?idx=<%=p.getId()%>">EDIT</a>
                <a href="delete?idx=<%=p.getId()%>">Delete</a>
                <br>
            </li>
            <%}
%>
        </ul>
        <a href="edit">New</a>
    </body>
</html>
