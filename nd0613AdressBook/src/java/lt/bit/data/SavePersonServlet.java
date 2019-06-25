/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "SavePersonServlet", urlPatterns = {"/savePersonServlet"})
public class SavePersonServlet extends HttpServlet {

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
            throws ServletException, IOException {//throws ServletException, IOException (pridejau Exception del datos parse

        String idxs = request.getParameter("idx");
        if (idxs == null) {
            try {
                String fn = request.getParameter("fn");
                String ln = request.getParameter("ln");
                String bd = request.getParameter("bd");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bd);
                String slr = request.getParameter("slr");
                BigDecimal salary = new BigDecimal(slr);

                if (fn != null && ln != null && bd != null && slr != null
                        && !"".equals(fn) && !"".equals(ln) && !"".equals(bd) && !"".equals(slr)) {

                    Person person = new Person(fn, ln, date, salary);
                    DB.getAll().add(person);
                }
            } catch (Exception ex) {};
            response.sendRedirect("index.jsp");
        }else{
            int idx = -1;
            try {
                idx = Integer.parseInt(idxs);

            } catch (Exception ex) {}

            if (idx < 0 || idx > DB.getById(idx).getNextId()) {// buvo >=DB.list.size()
                idx = -1;
            }
            if (idx >= 0 ) {
                try{
                String fn = request.getParameter("fn");
                String ln = request.getParameter("ln");
                String bd = request.getParameter("bd");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bd);
                String slr = request.getParameter("slr");
                BigDecimal salary = new BigDecimal(slr);
                
                if (fn != null && ln != null && bd != null && slr != null
                        && !"".equals(fn) && !"".equals(ln) && !"".equals(bd) && !"".equals(slr)) {
                    DB.getById(idx).setFirstName(fn);
                    DB.getById(idx).setLastName(ln);
                    DB.getById(idx).setBirthDate(date);
                    DB.getById(idx).setSalary(salary);
                    DB.getById(idx).setId(idx);//KODEL SITO REIKIA?
                }
                }catch (Exception ex){};
            }
            response.sendRedirect("index.jsp");
            
        
        }

//        try {
//            String fn = request.getParameter("fn");
//            String ln = request.getParameter("ln");
//            String bd = request.getParameter("bd");
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bd);
//            String slr = request.getParameter("slr");
//            BigDecimal salary = new BigDecimal(slr);
//
//            Person person = new Person(fn, ln, date, salary);
//            DB.list.add(person);
//        } catch (Exception ex) {
//        };

//        response.sendRedirect("index.jsp");

//        try{
//        String fn = request.getParameter("fn");
//        String ln = request.getParameter("ln");
//        String bd = request.getParameter("bd");
//        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(bd);
//        String slr = request.getParameter("slr");
//        BigDecimal salary= new BigDecimal(slr);    
//        
//        Person person = new Person(fn,ln,date,salary);
//        DB.list.add(person);
//        }catch (Exception ex){};
//        
//        
//        response.sendRedirect("index.jsp");
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
