<%-- 
    Document   : editPersonAddress
    Created on : Jun 17, 2019, 10:39:30 AM
    Author     : Dedelis
--%>

<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Address edit</title>
    </head>
    <body>
        <h1>Person Address edit</h1>

        <form method="POST" action="savePersonAdressServlet">
            
            <%
                String idxs = request.getParameter("idx");
                int idx;
//                try {
                    idx = Integer.parseInt(idxs);
//                } catch (Exception ex) {
//                }
//                if (idx < 0 || idx > DB.getById(idx).getNextId()) {
//                    idx = -1;
//                }
//                if (idxs != null && idx == -1) {
//                    response.sendRedirect("index.jsp");
//                    return;
//                }
            %>

            <%
                String idxsA = request.getParameter("idxA");//cia jau adreso ID 
                
                //reikia itraukti person id
                int idxA = -1;
                try {
                    idxA = Integer.parseInt(idxsA);
                } catch (Exception ex) {} //tikriausiai galima tik exeption?
                
                if (idxA < 0 || idxA >= DB.getAddressById(idxA).getNextId()) {//DB.getAddressById(idx).getNextId() kaip padaryti dinamiskai?
                    idxA = -1;
                }
                if (idxsA != null && idxA == -1) {
                    response.sendRedirect("index.jsp");//manau cia reiks pakeisti
                    return;
                }
            %>
            
            
            <% if (idxsA != null) {%><input name='idxA' type='hidden' value='<%=idxA%>'>
            <%}%> 
            
                       
            <input name='idx' type='hidden' value='<%=idx%>'>
            <!--------------------------------------------->

            Address: <input name="ad" value="<%=(idxsA == null) ? "" : DB.getAddressById(idxA).getAddress()%>">
            City: <input name="ct" value="<%=(idxsA == null) ? "" : DB.getAddressById(idxA).getCity()%>"> 
            Postal code: <input name="pc" value="<%=(idxsA == null) ? "" : DB.getAddressById(idxA).getPostalCode()%>"> 

            <input type="submit" value="Save">
        </form>
        
          
        <a href="personAddressList.jsp?idx=<%=idx%>"><%=idxs%><%=idxsA%>Cancel</a>
        
        


    </body>
</html>
