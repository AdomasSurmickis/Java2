package lt.bit.springjpa.dao;

import java.util.List;
import lt.bit.springjpa.db.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Dedelis
 */
public interface PersonsDAO extends JpaRepository<Persons, Integer>{
    @Query ("select p from Persons p order by p.firstName")
    public List<Persons> findAllSortedByName();
    
    @Query (name = "Persons.findByFirstName")
    public List<Persons> filterByName(@Param ("firstName") String firstName);//cia jei norm pagal uzklausa
}
