package bibliotecaapi.bibliotecaapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bibliotecaapi.bibliotecaapi.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByCustomer_Id(Long customerId);
    List<Loan> findAllByCustomer_Name(Long customerName);

    List<Loan> findByPublishedDate(LocalDate publishedDate);
}
