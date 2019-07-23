/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bandomasisegzaminas0723;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dedelis
 */
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Integer> grades;//pridedi <Grades>
    
    public Student(String firstName, String lastName, String email){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.grades = new ArrayList();
    }
    
    public Student(){
        this.grades = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }
    
    
    
}
