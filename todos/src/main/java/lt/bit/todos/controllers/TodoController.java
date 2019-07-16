/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.todos.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lt.bit.todos.dao.TodosDAO;
import lt.bit.todos.dao.UsersDAO;
import lt.bit.todos.db.Todos;
import lt.bit.todos.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dedelis
 */
@Controller
@RequestMapping("/")
public class TodoController {

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private TodosDAO todosDAO;

    @GetMapping
    @PostMapping
    public ModelAndView index(Principal principal) {
        ModelAndView mav = new ModelAndView("index");
        List<Users> users = usersDAO.findByName(principal.getName());
        if (users.size() > 0) {
            Users user = users.get(0);
            mav.addObject("todos", user.getTodosList());
        } else {
            mav.addObject("todos", new ArrayList());
        }
        return mav;
    }

    @Transactional
    @PostMapping("savetodo")
    public String savetodo(Principal principal,
            @RequestParam("todoText") String todoText) {
        List<Users> users = usersDAO.findByName(principal.getName());
        Users user = users.get(0);
        Todos todo = new Todos();
        todo.setUserId(user);
        todo.setTodoText(todoText);
        todo.setCreatedDate(new Date());
        todosDAO.save(todo);
        return "redirect:/";
    }

    @Transactional
    @GetMapping("deletetodo")
    public String deletedo(Principal principal,
            @RequestParam("id") Integer id) {
        List<Users> users = usersDAO.findByName(principal.getName());
        Users user = users.get(0);
        try {
            Todos todo = todosDAO.getOne(id);
            if (user.getId().equals(todo.getUserId().getId())) {
                todosDAO.delete(todo);
            }
        } catch (Exception ex) {
        }

        return "redirect:/";
    }

}
