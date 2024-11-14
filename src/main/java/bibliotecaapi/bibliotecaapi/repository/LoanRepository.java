package bibliotecaapi.bibliotecaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bibliotecaapi.bibliotecaapi.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    public Optional<List<Loan>> findByPublishedDate(String publishedDate);
}
