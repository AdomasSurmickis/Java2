/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springjpa.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import lt.bit.springjpa.dao.PersonsDAO;
import lt.bit.springjpa.db.Persons;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dedelis
 */
@Controller
@RequestMapping("/")
public class PersonController {
    private static final Log log = LogFactory.getLog(PersonController.class);
    
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PersonsDAO personsDAO;
    @Autowired
    PasswordEncoder pe;    

    @RequestMapping(path = "pass", method = RequestMethod.GET)
    @ResponseBody
    public String pass(@RequestParam(name = "pass", required = false) String pass) {
        log.warn(pass);
        String p = pe.encode(pass);
        log.warn(p);
        return p;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("personList");
//        mav.addObject("personsList", personsDAO.filterByName("%Jon%"));
//        mav.addObject("personsList", personsDAO.findAllSortedByName());
        mav.addObject("personsList", personsDAO.findAll());
        return mav;
    }

    @Transactional //kad nereiktu atidaryt trandakciju (pries kvieciant metoda atidaro ttranzakcija, kai baigia — uzkomitina)
    @RequestMapping(method = RequestMethod.GET, path = "delete")
    public String delete(@RequestParam Integer idx) {
        personsDAO.deleteById(idx);
        return "redirect:/";

    }

    @RequestMapping(method = RequestMethod.GET, path = "edit")
    public ModelAndView edit(@RequestParam(required = false) Integer idx) {
        ModelAndView maw = new ModelAndView("editPerson");
        if (idx != null) {
            maw.addObject("person", personsDAO.getOne(idx));
        }
        return maw;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "save")
    public String save(@RequestParam(required = false) Integer idx, @RequestParam String fn, @RequestParam String ln,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date bd, @RequestParam BigDecimal slr) {
        Persons p = null;
        if (idx != null) {
            p = personsDAO.getOne(idx);
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
        personsDAO.save(p);
        return "redirect:/";
    }

}
