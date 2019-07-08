/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springapp.web;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Dedelis
 */
@WebListener
public class EMFListtener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {//sita iikvieciamas kai appsa startuoa
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        sce.getServletContext().setAttribute("emf", emf); //objektas sitame saugo visa info apie appsa tomkate
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {//cia iskvieciamas kai sunaikinamas 
      EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
      if (emf != null){
      emf.close();
      }
    }
}
