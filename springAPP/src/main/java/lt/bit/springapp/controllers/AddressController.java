/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springapp.controllers;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.springapp.db.Address;
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
@RequestMapping("addresses")
public class AddressController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Integer idx) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Persons p = em.find(Persons.class, idx);
        if (p == null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("addresses");
        mav.addObject("person", p);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "delete")
    public String delete(@RequestParam Integer idx, @RequestParam Integer idxA) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        em.getTransaction().begin();
        Address a = em.find(Address.class, idxA);
        if (a != null) {
            em.remove(a);
        }
        em.getTransaction().commit();
        return "redirect:/addresses?idx=" + idx;
    }

    @RequestMapping(method = RequestMethod.GET, path = "edit")
    public ModelAndView edit(@RequestParam Integer idx,
            @RequestParam(required = false) Integer idxA) {
        ModelAndView maw = new ModelAndView("editAddress");
        EntityManager em = (EntityManager) request.getAttribute("em");
        Persons p = em.find(Persons.class, idx);
        if (p == null) {
            return new ModelAndView("redirect:/");
        }
        if (idxA != null) {
            Address a = em.find(Address.class, idxA);
            if (a == null) {
                return new ModelAndView("redirect:/addresses?idx=" + idx);
            }
            maw.addObject("address", a);
        }
        maw.addObject("person", p);
        return maw;
    }

    @RequestMapping(method = RequestMethod.POST, path = "save")
    public String save(@RequestParam Integer idx, @RequestParam(required = false) Integer idxA,
            @RequestParam String ct, @RequestParam String ad, Date bd, @RequestParam String pc) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        em.getTransaction().begin();
        Persons p = em.find(Persons.class, idx);
        if (p == null) {
            return "redirect:/";
        }
        Address a = null;
        if (idxA != null) {
            a = em.find(Address.class, idxA);
            if (a == null) {
                return "redirect:/addresses?idx=" + idx;
            }
        } else {
            a = new Address();
        }
        a.setCity(ct);
        a.setAddress(ad);
        a.setPostalCode(pc);
        a.setPerson(p);
        p.getAddressList().add(a);
        em.persist(a);
        em.getTransaction().commit();
        return "redirect:/addresses?idx=" + idx;
    }
}
