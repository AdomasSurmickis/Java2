/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springapp.controllers;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.springapp.db.Contacts;
import lt.bit.springapp.db.Persons;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Integer idx) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Persons p = em.find(Persons.class, idx);
        if (p == null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("contacts");
        mav.addObject("person", p);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "delete")
    public String delete(@RequestParam Integer idx, @RequestParam Integer idxC) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        em.getTransaction().begin();
        Contacts c = em.find(Contacts.class, idxC);
        if (c != null) {
            em.remove(c);
        }
        em.getTransaction().commit();
        return "redirect:/contacts?idx=" + idx;
    }

    @RequestMapping(method = RequestMethod.GET, path = "edit")
    public ModelAndView edit(@RequestParam Integer idx,
            @RequestParam(required = false) Integer idxC) {
        ModelAndView maw = new ModelAndView("editContact");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Persons p = em.find(Persons.class, idx);
        if (p == null) {
            return new ModelAndView("redirect:/");
        }
        if (idxC != null) {
            Contacts c = em.find(Contacts.class, idxC);
            if (c == null) {
                return new ModelAndView("redirect:/contacts?idx=" + idx);
            }
            maw.addObject("contact", c);
        }
        maw.addObject("person", p);
        return maw;
    }

    @RequestMapping(method = RequestMethod.POST, path = "save")
    public String save(@RequestParam Integer idx, @RequestParam(required = false) Integer idxC,
            @RequestParam String tp, @RequestParam String cn) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        em.getTransaction().begin();
        Persons p = em.find(Persons.class, idx);
        if (p == null) {
            return "redirect:/";
        }
        Contacts c = null;
        if (idxC != null) {
            c = em.find(Contacts.class, idxC);
            if (c == null) {
                return "redirect:/contacts?idx=" + idx;
            }
        } else {
            c = new Contacts();
        }
        c.setType(tp);
        c.setContact(cn);
        c.setPerson(p);
        p.getContactsList().add(c);
        em.persist(c);
        em.getTransaction().commit();
        return "redirect:/contacts?idx=" + idx;
    }

}
