package bibliotecaapi.bibliotecaapi.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Loan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/loans")
public class LoanController{


    @GetMapping(value = "/{customer}", produces = "application/json")
    public ResponseEntity<Loan> getByCustomer(@PathVariable("customer") Customer customer) {
        throw new UnsupportedOperationException("Unimplemented method 'getByCustomer'");
    }

  
    @GetMapping(value = "/{publishedDate}", produces = "application/json")
    public ResponseEntity<Loan> getByDate(@PathVariable("publishedDate") LocalDate publishedDate) {
        throw new UnsupportedOperationException("Unimplemented method 'getByDate'");
    }

    @PostMapping
	@Operation(summary = "Cria um empréstimo")
    public ResponseEntity<Loan> post(Loan loan) {
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }

    @PatchMapping
	@Operation(summary = "Atualiza um empréstimo") 
    public ResponseEntity<Loan> patch(@RequestBody Loan loan) {
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }

    
}
