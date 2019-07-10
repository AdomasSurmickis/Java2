<%-- 
    Document   : contacts
    Created on : Jul 5, 2019, 6:51:01 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.springjpa.db.Contacts"%>
<%@page import="java.util.List"%>
<%@page import="lt.bit.springjpa.db.Persons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Persons p = (Persons)request.getAttribute("person");
            List<Contacts> l = p.getContactsList();
        %>
        <h1>Person's <%=p.getFirstName()%> <%=p.getLastName()%> address list</h1>
        <ul>
        <%          
            for (Contacts c : l) {
        %>
            <li>
            Contact Id: <%= c.getId()%> Type: <%=c.getType()%> Contact <%=c.getContact()%>
            <!--visi ko norim get-->
            <a href="contacts/edit?idx=<%=p.getId()%>&idxC=<%=c.getId()%>"
               >edit</a>
            <a href="contacts/delete?idx=<%=p.getId()%>&idxC=<%=c.getId()%>"
               >delete</a>
            </li>
        <%}
        %>
        </ul>
        <!--same su delete-->
        <a href="contacts/edit?idx=<%=p.getId()%>">New</a>
        <a href="./">Back</a>
    </body>
</html>
