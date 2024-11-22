package bibliotecaapi.bibliotecaapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Loan;
import bibliotecaapi.bibliotecaapi.model.Status;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByCustomer_Id(Long customerId);

    List<Loan> findAllByCustomer_Name(String customerName);

    List<Loan> findByLoanDate(LocalDate publishedDate);

    Optional<Loan> findByCustomerAndStatus(Customer customer, Status borrowed);
}

