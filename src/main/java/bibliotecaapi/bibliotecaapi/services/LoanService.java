package bibliotecaapi.bibliotecaapi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bibliotecaapi.bibliotecaapi.dto.LoanDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanDatePatchDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanRequestDto;
import bibliotecaapi.bibliotecaapi.dto.LoanStatusPatchDTO;
import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Loan;
import bibliotecaapi.bibliotecaapi.model.Status;
import bibliotecaapi.bibliotecaapi.repository.BookRepository;
import bibliotecaapi.bibliotecaapi.repository.CustomerRepository;
import bibliotecaapi.bibliotecaapi.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanService {

    @Autowired private LoanRepository repository;
    @Autowired private BookRepository bookRepository;
    @Autowired private CustomerRepository customerRepository;

    @Transactional
    public LoanDTO create(LoanRequestDto request) {
        // Verifica se o número de livros é válido
    List<Long> idBooks = request.getIdBooks();
    if (idBooks.size() > 2) {
        throw new EntityNotFoundException("Só pode emprestar até 2 livros por vez");
    }

    // Recupera os livros solicitados pelo ID
    List<Book> books = bookRepository.findAllById(idBooks);
    if (books.isEmpty()) {
        throw new EntityNotFoundException("Livros não encontrados");
    }

    // Verifica se o cliente existe e se não tem empréstimos ativos
    Optional<Customer> optionalCustomer = customerRepository.findById(request.getIdCustumer());
    if (!optionalCustomer.isPresent()) {
        throw new EntityNotFoundException("Cliente não encontrado");
    }

    Customer customer = optionalCustomer.get();

    // Verifica se o cliente já possui um empréstimo ativo
    Optional<Loan> activeLoan = repository.findByCustomerAndStatus(customer, Status.BORROWED);
    if (activeLoan.isPresent()) {
        throw new IllegalStateException("O cliente já possui um empréstimo ativo.");
    }

    // Cria o novo empréstimo
    Loan loan = new Loan();
    loan.setBooks(books);
    loan.setCustomer(customer);
    loan.setLoanDate(LocalDate.now()); // Agora inclui a data e hora atual
    loan.setStatus(Status.BORROWED);

    // Atualiza o status dos livros para 'BORROWED'
    books.forEach(book -> book.setStatus(Status.BORROWED));
    bookRepository.saveAll(books);  // Salva os livros com o status atualizado

    // Salva o empréstimo no repositório
    Loan savedLoan = repository.save(loan);

    // Cria e retorna o DTO de empréstimo
    LoanDTO dto = new LoanDTO();
    BeanUtils.copyProperties(savedLoan, dto, "id");
    
    // Agora o DTO de Loan inclui dados reais do Customer e dos Books
    return dto;
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
        List<Loan> loans = repository.findByLoanDate(publishedDate);
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
            model.setLoanDate(dtoPatch.getPublishedDate());
            repository.save(model);

            LoanDTO dto = new LoanDTO();
            BeanUtils.copyProperties(model, dto);
            return dto;
        }
        throw new EntityNotFoundException("Empréstimo não encontrado");
    }

}

    
