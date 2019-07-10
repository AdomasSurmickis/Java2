/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springjpa.dao;

import lt.bit.springjpa.db.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Dedelis
 */
public interface ContactsDAO extends JpaRepository<Contacts, Integer>{
    
}
