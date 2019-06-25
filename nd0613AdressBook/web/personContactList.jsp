<%-- 
    Document   : personContactList
    Created on : Jun 18, 2019, 1:49:03 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Contact list</title>
    </head>
    <body>
        <%
            String idxs = request.getParameter("idx"); //idx nebus null nes jau sukurtam objekte esam
            int idx;
            idx = Integer.parseInt(idxs);
        %>
        <h1>Person <%=DB.getById(idx).getFirstName()%> <%=DB.getById(idx).getLastName()%> Contact List</h1>
        <ul>
            <% for (int i = 0; i < DB.getById(idx).getContacts().size(); i++) {
            %>
            <li>Type: <%=DB.getById(idx).getContacts().get(i).getType()%>
                Contact: <%=DB.getById(idx).getContacts().get(i).getContact()%> 
                Contact id: <%=DB.getById(idx).getContacts().get(i).getId()%>
                <a href="editPersonContact.jsp?idx=<%=idx%>&idxC=<%=DB.getById(idx).getContacts().get(i).getId()%>" >
                    Edit</a>
                <a href="deletePersonContactServlet?idx=<%=idx%>&idxC=<%=DB.getById(idx).getContacts().get(i).getId()%>">
                    Delete</a>
            </li>
            <%
                }
            %>
        </ul>
        <a href="editPersonContact.jsp?idx=<%=idx%>">Add<a/>
        <a href="index.jsp">Back<a/>
     </body>
 </html>
