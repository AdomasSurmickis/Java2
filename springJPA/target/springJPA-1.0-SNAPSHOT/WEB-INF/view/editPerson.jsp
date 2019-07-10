<%-- 
    Document   : editPerson
    Created on : Jul 5, 2019, 7:06:38 PM
    Author     : Dedelis
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.springjpa.db.Persons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="save">
            <%
            Persons p = (Persons)request.getAttribute("person");
            %>
            <% if (p!=null)
            {%><input name='idx' type='hidden' value='<%=p.getId()%>'><%}%>
            First name: <input name="fn" value="<%=(p==null)?"":p.getFirstName()%>">
            Last name: <input name="ln" value="<%=(p==null)?"":p.getLastName()%>"> 
            Birth name: <input name="bd" placeholder="YYYY-MM-DD" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                               value="<%=(p==null)?"":p.getBirthDate()%>"> 
            Salary: <input name="slr" value="<%=(p==null)?"":p.getSalary()%>"> 
            <input type="submit" value="Save">
        </form>
        <a href="./">Cancel<a/>
    </body>
</html>
