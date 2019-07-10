/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springjpa.controllers;

import java.util.List;
import lt.bit.springjpa.dao.PersonsDAO;
import lt.bit.springjpa.db.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dedelis
 */
@RestController
@RequestMapping("/rest/person")
public class PersonRest {

    @Autowired
    private PersonsDAO personsDAO;

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Persons> list() {
        return personsDAO.findAll();
    }

    @GetMapping("{idx}")//sklaustuose kintamieji
    public Persons getPerson(@PathVariable("idx") Integer idx) {
//        Persons p = null;
//        try {
//            p = personsDAO.findById(idx).get(); //findById visad eina i DB
//        } catch (Exception ex) {
//            // ignored
//        }
//        return p;
        Persons p = personsDAO.getOne(idx);
        try {
//            System.out.println(p);
            p.getFirstName();//kreipimasis trigerina DB, kad ieskot p;
        } catch (Exception ex) {
            return null;
        }
        return p;
    }

    @Transactional
    @DeleteMapping("{idx}")//jei norim nueit iki adreso "{idx}/{idxA}"
    public void delete(@PathVariable("idx") Integer idx) {// adresui reik prideti  po idx ,required = 
        personsDAO.deleteById(idx);
    }

    @Transactional
    @PostMapping
    public Persons create(@RequestBody Persons p) {//requestbody tam kad suparsintu
        p.setId(null);
        personsDAO.save(p);
        return p;
    }

    @Transactional
    @PutMapping
    public Persons update(@RequestBody Persons p) {

        try {
            Persons u = personsDAO.getOne(p.getId());
            u.setLastName(p.getLastName());
            u.setFirstName(p.getFirstName());
            u.setBirthDate(p.getBirthDate());
            u.setSalary(p.getSalary());
            return u;
        } catch (Exception ex) {
            return null;
        }
    }

}
