<%-- 
    Document   : editAddress
    Created on : Jul 5, 2019, 7:23:14 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.springjpa.db.Address"%>
<%@page import="lt.bit.springjpa.db.Persons"%>
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
            Address a = (Address)request.getAttribute("address");
            %>
            <% if (a != null) {%><input name='idxA' type='hidden' value='<%=a.getId()%>'>
            <%}%> 
            <input name='idx' type='hidden' value='<%=p.getId()%>'>
            <!--------------------------------------------->
            Address: <input name="ad" value="<%=(a == null) ? "" : a.getAddress()%>">
            City: <input name="ct" value="<%=(a == null) ? "" : a.getCity()%>"> 
            Postal code: <input name="pc" value="<%=(a == null) ? "" : a.getPostalCode()%>"> 
            <input type="submit" value="Save">
        </form>
        <a href="../addresses?idx=<%=p.getId()%>">Cancel</a>
    </body>
</html>
