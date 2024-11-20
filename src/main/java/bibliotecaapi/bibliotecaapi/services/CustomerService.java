package bibliotecaapi.bibliotecaapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.CustomerDTO;
import bibliotecaapi.bibliotecaapi.dto.CustomerPatchDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanDTO;
import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Loan;
import bibliotecaapi.bibliotecaapi.model.Status;
import bibliotecaapi.bibliotecaapi.repository.CustomerRepository;
import bibliotecaapi.bibliotecaapi.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    @Autowired private CustomerRepository repository;
    @Autowired private LoanRepository loanRepository;

    public CustomerDTO create(CustomerDTO dto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer); 
        customer.setStatus(Status.ACTIVE);
        Customer savedCustomer = repository.save(customer);
        BeanUtils.copyProperties(savedCustomer, dto);
        return dto;
    }

    public Page<CustomerDTO> FindAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = repository.findAll(pageable);

        return customers.map(customer -> {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(customer, dto);
            return dto;
        });
    }

    public Optional<CustomerDTO> findById(Long id) {
        return repository.findById(id)
            .map(customer -> {
                CustomerDTO dto = new CustomerDTO();
                BeanUtils.copyProperties(customer, dto, "id", "status");
                return dto;
            });
    }

    public List<CustomerDTO> findByNome(String nome) {
        List<Customer> customers = repository.findByName(nome);
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(customer, dto, "id", "status");
            dtos.add(dto);
        }
        return dtos;
    }

    public List<CustomerDTO> findByDataNascimento(LocalDate dataNascimento) {
        List<Customer> customers = repository.findByBirthDate(dataNascimento);
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(customer, dto, "id", "status");
            dtos.add(dto);
        }
        return dtos;
    }

    public List<LoanDTO> findLoansByCustomer(Long id) {
        // Recupera a lista de empréstimos do cliente
    List<Loan> loans = loanRepository.findAllByCustomer_Id(id);
    List<LoanDTO> dtos = new ArrayList<>();

    // Para cada empréstimo, criamos o DTO
    for (Loan loan : loans) {
        LoanDTO dto = new LoanDTO();
        
        // Copia as propriedades de Loan para o DTO
        BeanUtils.copyProperties(loan, dto, "books", "customer"); // Não copia books e customer diretamente
        
        // Agora preenche manualmente as propriedades aninhadas
        dto.setCustomer(loan.getCustomer()); // Preenche o cliente
        dto.setBooks(loan.getBooks()); // Preenche os livros
        
        // Adiciona o DTO à lista
        dtos.add(dto);
    }
    
    return dtos;
    }

    public void delete(Long id) {
        Optional<CustomerDTO> customer = findById(id);
        List<LoanDTO> loans = findLoansByCustomer(id);
        
        if (customer.isPresent()) {
            if (loans.isEmpty()) {
                repository.deleteById(id);
            } else{
                throw new IllegalStateException("Cliente possui empréstimos");
            }  
        } else{
            throw new EntityNotFoundException("Cliente não encontrado");
        }
    }   
    
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            BeanUtils.copyProperties(dto, customer);
            repository.save(customer);
            BeanUtils.copyProperties(customer, dto);
            return dto;
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com ID: " + id);
        }
    }

    public CustomerDTO updateStatus(Long id, CustomerPatchDTO dtoPatch) {
        Optional<Customer> optionalModel = repository.findById(id);
        
        if (optionalModel.isPresent()) {
            Customer model = optionalModel.get();
            model.setStatus(dtoPatch.getStatus());
            repository.save(model);

            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(model, dto);
            return dto;
        }
        throw new EntityNotFoundException("Cliente não encontrado");
    }


}

