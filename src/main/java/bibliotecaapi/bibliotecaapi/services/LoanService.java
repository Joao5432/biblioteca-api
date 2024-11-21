package bibliotecaapi.bibliotecaapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.LoanDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanDatePatchDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanStatusPatchDTO;
import bibliotecaapi.bibliotecaapi.model.Loan;
import bibliotecaapi.bibliotecaapi.model.Status;
import bibliotecaapi.bibliotecaapi.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanService {

    @Autowired private LoanRepository repository;

    public LoanDTO create(LoanDTO dto){
        if (!repository.findAllByCustomer_Id(dto.getCustomer().getId()).isEmpty()) {
            Loan loan = new Loan();
            BeanUtils.copyProperties(dto, loan); 
            loan.setStatus(Status.BORROWED);
            Loan savedLoan = repository.save(loan);
            BeanUtils.copyProperties(savedLoan, dto);
            return dto;
        }      
        else {
            return null;
        }
    }

    public Optional<LoanDTO> findById(Long id) {
        return repository.findById(id)
            .map(loan -> {
                LoanDTO dto = new LoanDTO();
                BeanUtils.copyProperties(loan, dto, "id", "status");
                return dto;
            });
    }

    public List<LoanDTO> findByDate(LocalDate publishedDate) {
        List<Loan> loans = repository.findByPublishedDate(publishedDate);
        List<LoanDTO> dtos = new ArrayList<>();
        for (Loan Loan : loans) {
            LoanDTO dto = new LoanDTO();
            BeanUtils.copyProperties(Loan, dto, "id", "status");
            dtos.add(dto);
        }
        return dtos;
    }
    
    public LoanDTO updateStatus(Long id, LoanStatusPatchDTO dtoPatch) {
        Optional<Loan> optionalModel = repository.findById(id);
        
        if (optionalModel.isPresent() && dtoPatch.getStatus() == Status.AVAILABLE) {
            Loan model = optionalModel.get();
            model.setStatus(dtoPatch.getStatus());
            repository.save(model);

            LoanDTO dto = new LoanDTO();
            BeanUtils.copyProperties(model, dto);
            return dto;
        }
        throw new EntityNotFoundException("Empréstimo não encontrado");
    }

    public LoanDTO updateDate(Long id, LoanDatePatchDTO dtoPatch) {
        Optional<Loan> optionalModel = repository.findById(id);
        
        if (optionalModel.isPresent()) {
            Loan model = optionalModel.get();
            model.setPublishedDate(dtoPatch.getPublishedDate());
            repository.save(model);

            LoanDTO dto = new LoanDTO();
            BeanUtils.copyProperties(model, dto);
            return dto;
        }
        throw new EntityNotFoundException("Empréstimo não encontrado");
    }

}

    

