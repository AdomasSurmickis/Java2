<%-- 
    Document   : personAddressList
    Created on : Jun 16, 2019, 11:50:37 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Address List</title>
    </head>
    <body>

        <%
            String idxs = request.getParameter("idx"); //idx nebus null nes jau sukurtam objekte esam
            int idx;
            idx = Integer.parseInt(idxs);
        %>
        <h1>Person <%=DB.getById(idx).getFirstName()%> <%=DB.getById(idx).getLastName()%> Address List</h1>
        <ul>
            <% for (int i = 0; i < DB.getById(idx).getAddresses().size(); i++) {
            %>
            <li>Address: <%=DB.getById(idx).getAddresses().get(i).getAddress()%>
                City: <%=DB.getById(idx).getAddresses().get(i).getCity()%> 
                Postal code: <%=DB.getById(idx).getAddresses().get(i).getPostalCode()%> 
                Address id: <%=DB.getById(idx).getAddresses().get(i).getId()%>
                <a href="editPersonAddress.jsp?idx=<%=idx%>&idxA=<%=DB.getById(idx).getAddresses().get(i).getId()%>" >
                    <%--<a href="editPersonAddress.jsp?idx=<%=idx%>" type="hidden">--%> 
                    <%--  <input name='idx' type='hidden' value='<%=idx%>'> --%>
                    Edit</a>
                <a href="deletePersonAdressServlet?idx=<%=idx%>&idxA=<%=DB.getById(idx).getAddresses().get(i).getId()%>">
                    Delete</a>
                <!--cia ivedam adrreso ID, pridedam hidden idx, kad perduoti adresa-->
            </li>
            <%
                }
            %>
        </ul>
        <a href="editPersonAddress.jsp?idx=<%=idx%>"><%=idx%>Add<a/> <%--<%=DB.getById(idx).getId()%>--%>
            <!--cia reik prisiskirti person ID (as perduodu tik adress ID)-->
        <a href="index.jsp">Back<a/>
                </body>
                </html>
