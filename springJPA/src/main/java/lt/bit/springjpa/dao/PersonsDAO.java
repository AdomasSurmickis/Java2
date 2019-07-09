package lt.bit.springjpa.dao;

import lt.bit.springjpa.db.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Dedelis
 */
public interface PersonsDAO extends JpaRepository<Persons, Integer>{
    
}
