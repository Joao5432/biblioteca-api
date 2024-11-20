package bibliotecaapi.bibliotecaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.LoanDTO;
import bibliotecaapi.bibliotecaapi.model.Loan;
import bibliotecaapi.bibliotecaapi.repository.LoanRepository;
import lombok.NonNull;

@Service
public class LoanService {

    @Autowired private LoanRepository repository;

    public LoanDTO create(LoanDTO dto){
        Loan model = new Loan();
        model.setCustomer(dto.getCustomer());
        model.setBooks(dto.getBooks());
        model.setAuthor(dto.getAuthor());
        model.setIsbn(dto.getIsbn());
        model.setPublishedDate(dto.getPublishedDate());
        repository.save(model);

        return dto;
    }   
}

    

