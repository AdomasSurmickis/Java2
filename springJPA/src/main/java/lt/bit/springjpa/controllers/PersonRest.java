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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/{idx}")
    public Persons getPerson(@PathVariable("idx") Integer idx){
        return personsDAO.getOne(idx);
    }

}
