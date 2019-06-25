<%-- 
    Document   : index
    Created on : Jun 16, 2019, 5:33:08 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person list</title>
    </head>
    <body>
        <h1 id="CRUD"><style> #CRUD {color:rgb(217, 157, 182)}</style>CRUD (Create Read Update Delete)</h1>
        <h1>Person list</h1>

        <ul>
            <% for (int i = 0; i < DB.getAll().size(); i++) {
            %>
            <li>First name: <%=DB.getAll().get(i).getFirstName()%> Last name: <%=DB.getAll().get(i).getLastName()%> 
                Date of birth: <%=DB.getAll().get(i).getBirthDate()%> Salary: <%=DB.getAll().get(i).getSalary()%>
                Id: <%=DB.getAll().get(i).getId()%>
                <a href="editPerson.jsp?idx=<%=DB.getAll().get(i).getId()%>">Edit</a>
                <a href="deletePerson?idx=<%=DB.getAll().get(i).getId()%>">Delete</a>
                <a href="personAddressList.jsp?idx=<%=DB.getAll().get(i).getId()%>">Address</a>                    
                <a href="personContactList.jsp?idx=<%=DB.getAll().get(i).getId()%>">Contact</a>                
            </li>
            <%}%>            
        </ul>
        <a href="editPerson.jsp">Add<a/>        
    </body>
</html>

<!--nepanaudojau visu metodu: hash code, toString-->