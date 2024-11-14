package bibliotecaapi.bibliotecaapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bibliotecaapi.bibliotecaapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    public Optional<Customer> findByName(String name);

    public Optional<Customer> findByBirthDate(LocalDate birthDate);
}
