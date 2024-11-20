
package bibliotecaapi.bibliotecaapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bibliotecaapi.bibliotecaapi.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findById(Long id);
    
    List<Customer> findByName(String name);
    
    List<Customer> findByBirthDate(LocalDate dataNascimento);

}
