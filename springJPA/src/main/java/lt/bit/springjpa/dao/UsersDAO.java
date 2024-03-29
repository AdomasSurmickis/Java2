/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.springjpa.dao;

import java.util.List;
import lt.bit.springjpa.db.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Dedelis
 */
public interface UsersDAO extends JpaRepository<Users, Integer> {

    @Query(name = "Users.findByName")
    public List<Users> findByName(@Param("name") String name);

}
