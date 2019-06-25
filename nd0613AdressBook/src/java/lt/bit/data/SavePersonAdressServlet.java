/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.io.IOException;
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
@WebServlet(name = "SavePersonAdressServlet", urlPatterns = {"/savePersonAdressServlet"})
public class SavePersonAdressServlet extends HttpServlet {

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

        String idxsA = request.getParameter("idxA");
        String idxs = request.getParameter("idx");
        int idx=-1;
            try {
                idx = Integer.parseInt(idxs);

            } catch (Exception ex) {}
        if (idxsA == null) {
            try {
                String ad = request.getParameter("ad");
                String ct = request.getParameter("ct");
                String pc = request.getParameter("pc");

                if (ad != null && ct != null && pc != null
                        && !"".equals(ad) && !"".equals(ct) && !"".equals(pc)) {

                    Address address = new Address(ad, ct, pc);
                    
                    
//                    DB.list.getAddressById(idx).add(address);
//                    DB.getById(idx).getAddresses().add(address);
                      DB.getPersonAddresses(idx).add(address);
                }
            } catch (Exception ex) {};
            response.sendRedirect("personAddressList.jsp?idx="+idx);
            
            
        } 
        else {
            int idxA = -1;
            try {
                idxA = Integer.parseInt(idxsA);

            } catch (Exception ex) {
            }

            if (idxA < 0 || idxA > DB.getAddressById(idxA).getNextId()) {// buvo >=DB.list.size()
                idxA = -1;
            }
            if (idxA >= 0) {
                try {
                    String ad = request.getParameter("ad");
                    String ct = request.getParameter("ct");
                    String pc = request.getParameter("pc");

                    if (ad != null && ct != null && pc != null
                        && !"".equals(ad) && !"".equals(ct) && !"".equals(pc)) {
                        DB.getAddressById(idxA).setAddress(ad);
                        DB.getAddressById(idxA).setCity(ct);
                        DB.getAddressById(idxA).setPostalCode(pc);
                        DB.getAddressById(idxA).setId(idxA);//KODEL SITO REIKIA?
                    }
                } catch (Exception ex) {
                };
            }
            response.sendRedirect("personAddressList.jsp?idx="+idx);

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
    