package bibliotecaapi.bibliotecaapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bibliotecaapi.bibliotecaapi.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
