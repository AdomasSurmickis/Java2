/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.pamoka0711;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dedelis
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/index.jsp", "/admin.jsp"})
public class LoginFilter implements Filter  {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        if (session == null || session.getAttribute("userName") == null){
            if (session != null){
                session.setAttribute("next", ((HttpServletRequest)request).getRequestURI());
            }
            RequestDispatcher rd = ((HttpServletRequest)request).getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
    
    
}
