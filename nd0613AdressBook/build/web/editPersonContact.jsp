<%-- 
    Document   : editPersonContact
    Created on : Jun 18, 2019, 2:05:01 PM
    Author     : Dedelis
--%>

<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Contact edit</title>
    </head>
    <body>
        <h1>Person Contact edit</h1>
        <form method="POST" action="savePersonContactServlet">            
            <%
                String idxs = request.getParameter("idx");
                int idx;
                idx = Integer.parseInt(idxs);
                String idxsC = request.getParameter("idxC");//cia jau adreso ID 
                int idxC = -1;
                try {
                    idxC = Integer.parseInt(idxsC);
                } catch (Exception ex) {} 
                if (idxC < 0 || idxC >= DB.getContactById(idxC).getNextId()) {//DB.getAddressById(idx).getNextId() kaip padaryti dinamiskai?
                    idxC = -1;
                }
                if (idxsC != null && idxC == -1) {
                    response.sendRedirect("index.jsp");//manau cia reiks pakeisti
                    return;
                }
            %>           
            <% if (idxsC != null) {%><input name='idxC' type='hidden' value='<%=idxC%>'>
            <%}%> 
            <input name='idx' type='hidden' value='<%=idx%>'>
            <!--------------------------------------------->
            Type: <input name="tp" value="<%=(idxsC == null) ? "" : DB.getContactById(idxC).getType()%>">
            Contact: <input name="cn" value="<%=(idxsC == null) ? "" : DB.getContactById(idxC).getContact()%>"> 
            <input type="submit" value="Save">
        </form>
        <a href="personContactList.jsp?idx=<%=idx%>">Cancel</a>
    </body>
</html>
