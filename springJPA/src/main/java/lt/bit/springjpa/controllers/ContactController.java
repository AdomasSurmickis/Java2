/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springjpa.controllers;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.springjpa.dao.ContactsDAO;
import lt.bit.springjpa.dao.PersonsDAO;
import lt.bit.springjpa.db.Contacts;
import lt.bit.springjpa.db.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private ContactsDAO contactsDAO;
    @Autowired
    private PersonsDAO personsDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Integer idx) {
        Persons p = personsDAO.getOne(idx);
        if (p == null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("contacts");
        mav.addObject("person", p);
        return mav;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, path = "delete")
    public String delete(@RequestParam Integer idx, @RequestParam Integer idxC) {
        Contacts c = contactsDAO.getOne(idxC);
        if (c != null) {
            contactsDAO.deleteById(idxC);
        }
        return "redirect:/contacts?idx=" + idx;
    }

    @RequestMapping(method = RequestMethod.GET, path = "edit")
    public ModelAndView edit(@RequestParam Integer idx,
            @RequestParam(required = false) Integer idxC) {
        ModelAndView maw = new ModelAndView("editContact");
        Persons p = personsDAO.getOne(idx);
        if (p == null) {
            return new ModelAndView("redirect:/");
        }
        if (idxC != null) {
            Contacts c = contactsDAO.getOne(idxC);
            if (c == null) {
                return new ModelAndView("redirect:/contacts?idx=" + idx);
            }
            maw.addObject("contact", c);
        }
        maw.addObject("person", p);
        return maw;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "save")
    public String save(@RequestParam Integer idx, @RequestParam(required = false) Integer idxC,
            @RequestParam String tp, @RequestParam String cn) {
        Persons p = personsDAO.getOne(idx);
        if (p == null) {
            return "redirect:/";
        }
        Contacts c = null;
        if (idxC != null) {
            c = contactsDAO.getOne(idxC);
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
        contactsDAO.save(c);
        return "redirect:/contacts?idx=" + idx;
    }

}
