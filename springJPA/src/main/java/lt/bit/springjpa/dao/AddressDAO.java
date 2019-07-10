/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springjpa.dao;

import lt.bit.springjpa.db.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Dedelis
 */
public interface AddressDAO extends JpaRepository<Address, Integer>{
    
}
