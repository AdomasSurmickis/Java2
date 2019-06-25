/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.db.DB;

/**
 *
 * @author Dedelis
 */
@WebServlet(name = "SavePersonContactServlet", urlPatterns = {"/savePersonContactServlet"})
public class SavePersonContactServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idxsC = request.getParameter("idxC");
        String idxs = request.getParameter("idx");
        int idx;
        idx = Integer.parseInt(idxs);
        if (idxsC == null) {
            try {
                String tp = request.getParameter("tp");
                String cn = request.getParameter("cn");

                if (tp != null && cn != null && !"".equals(tp) && !"".equals(cn)) {
                    Contact contact = new Contact(tp, cn);
                    DB.getPersonContacts(idx).add(contact);
                }
            } catch (Exception ex) {
            };
            response.sendRedirect("personContactList.jsp?idx=" + idx);
        } else {
            int idxC = -1;
            try {
                idxC = Integer.parseInt(idxsC);
            } catch (Exception ex) {
            }
            if (idxC < 0 || idxC > DB.getContactById(idxC).getNextId()) {
                idxC = -1;
            }
            if (idxC >= 0) {
                try {
                    String tp = request.getParameter("tp");
                    String cn = request.getParameter("cn");
                    if (tp != null && cn != null && !"".equals(tp) && !"".equals(cn)) {
                        DB.getContactById(idxC).setType(tp);
                        DB.getContactById(idxC).setContact(cn);
                        DB.getAddressById(idxC).setId(idxC);//KODEL SITO REIKIA?
                    }
                } catch (Exception ex) {
                };
            }
            response.sendRedirect("personContactList.jsp?idx=" + idx);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
