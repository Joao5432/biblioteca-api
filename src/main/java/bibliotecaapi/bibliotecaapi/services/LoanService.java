package bibliotecaapi.bibliotecaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import bibliotecaapi.bibliotecaapi.model.Loan;
import bibliotecaapi.bibliotecaapi.repository.LoanRepository;
import lombok.NonNull;

public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanService(){

    }

    public Optional<Loan> find(Long id){
        return loanRepository.findById(id.longValue());
    }

    public Optional<List<Loan>> findByPublishedDate(String publishedDate) {
        return loanRepository.findByPublishedDate(publishedDate);
    }

    public void create(@NonNull Loan loan) {
        loanRepository.save(loan);
    }

    
}
