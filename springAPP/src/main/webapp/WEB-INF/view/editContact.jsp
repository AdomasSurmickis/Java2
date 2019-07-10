<%-- 
    Document   : editContact
    Created on : Jul 5, 2019, 8:41:37 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.springapp.db.Contacts"%>
<%@page import="lt.bit.springapp.db.Persons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="./save">
            <%
            Persons p = (Persons)request.getAttribute("person");
            Contacts c = (Contacts)request.getAttribute("contact");
            %>
            <% if (c != null) {%><input name='idxC' type='hidden' value='<%=c.getId()%>'>
            <%}%> 
            <input name='idx' type='hidden' value='<%=p.getId()%>'>
            <!--------------------------------------------->
            Type: <input name="tp" value="<%=(c == null) ? "" : c.getType()%>">
            Contact: <input name="cn" value="<%=(c == null) ? "" : c.getContact()%>"> 
            <input type="submit" value="Save">
        </form>
        <a href="../contacts?idx=<%=p.getId()%>">Cancel</a>
    </body>
</html>
