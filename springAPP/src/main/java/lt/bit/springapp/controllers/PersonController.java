/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springapp.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import lt.bit.springapp.db.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dedelis
 */
@Controller
@RequestMapping("/")
public class PersonController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        EntityManager em = (EntityManager) request.getAttribute("em");
//        Query q = em.createNamedQuery("Persons.findAll");
        Query q = em.createQuery("select p from Persons p order by p.firstName");
        List<Persons> list = q.getResultList();
        System.out.println(list);
        ModelAndView mav = new ModelAndView("personList");
        mav.addObject("personsList", list);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "delete")
    public String delete(@RequestParam Integer idx) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        em.getTransaction().begin();
        Persons p = em.find(Persons.class, idx);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
        return "redirect:/";

    }

    @RequestMapping(method = RequestMethod.GET, path = "edit")
    public ModelAndView edit(@RequestParam(required = false) Integer idx) {
        ModelAndView maw = new ModelAndView("editPerson");
        if (idx != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            Persons p = em.find(Persons.class, idx);
            maw.addObject("person", p);
        }
        return maw;
    }

    @RequestMapping(method = RequestMethod.POST, path = "save")
    public String save(@RequestParam(required = false) Integer idx, @RequestParam String fn, @RequestParam String ln,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date bd, @RequestParam BigDecimal slr) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        em.getTransaction().begin();
        Persons p = null;
        if (idx != null) {
            p = em.find(Persons.class, idx);
            if (p == null) {
                return "redirect:/";
            }
        } else {
            p = new Persons();
        }
        p.setFirstName(fn);
        p.setLastName(ln);
        p.setBirthDate(bd);
        p.setSalary(slr);
        em.persist(p);
        em.getTransaction().commit();
        return "redirect:/";
    }

}
