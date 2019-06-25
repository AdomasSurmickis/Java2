<%-- 
    Document   : editPerson
    Created on : Jun 16, 2019, 5:56:36 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person edit</title>
    </head>
    <body>
        <h1>Person edit</h1>
        <form method="POST" action="savePersonServlet">
            
            
            <%
                String idxs = request.getParameter("idx");
                int idx = -1;
                try {
                    idx = Integer.parseInt(idxs);
                } catch (Exception ex) {
                }
                if (idx < 0 || idx > DB.getById(idx).getNextId()) {
                    idx = -1;
                }
                if (idxs != null && idx == -1) {
                    response.sendRedirect("index.jsp");
                    return;
                }
            %>
            <% if (idxs!=null){%><input name='idx' type='hidden' value='<%=idx%>'><%}%>        
             
            First name: <input name="fn" value="<%=(idxs==null)?"":DB.getById(idx).getFirstName() %>">
            Last name: <input name="ln" value="<%=(idxs==null)?"":DB.getById(idx).getLastName() %>"> 
            Birth name: <input name="bd" placeholder="YYYY-MM-DD" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                               value="<%=(idxs==null)?"":DB.getById(idx).getBirthDate() %>"> 
            Salary: <input name="slr" value="<%=(idxs==null)?"":DB.getById(idx).getSalary() %>"> 
            
            <input type="submit" value="Save">
            
            
        
        </form>
        <a href="index.jsp">Cancel<a/>
    </body>
</html>
