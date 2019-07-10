<%-- 
    Document   : addresses
    Created on : Jul 1, 2019, 5:27:09 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.springjpa.db.Address"%>
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
            List<Address> l = p.getAddressList();
        %>
        <h1>Person's <%=p.getFirstName()%> <%=p.getLastName()%> address list</h1>
        <ul>
        <%          
            for (Address a : l) {
        %>
            <li>
            Adress Id: <%= a.getId()%> Address: <%=a.getAddress()%> City: <%=a.getCity()%>
            Postal code: <%=a.getPostalCode()%>
            <!--visi ko norim get-->
            <a href="addresses/edit?idx=<%=p.getId()%>&idxA=<%=a.getId()%>"
               >edit</a>
            <a href="addresses/delete?idx=<%=p.getId()%>&idxA=<%=a.getId()%>"
               >delete</a>
            </li>
        <%}
        %>
        </ul>
        <!--same su delete-->
        <a href="addresses/edit?idx=<%=p.getId()%>">New</a>
        <% //if(a.getId()!=null)
            %>
        <a href="./">Back</a>
        <!--          dar 3 jsp page
                  dar servletus kurie trina
                  padarys save, edit servletus (principas kaip darem, tik pritaikyti sitam daiktui-->
    </body>
</html>
