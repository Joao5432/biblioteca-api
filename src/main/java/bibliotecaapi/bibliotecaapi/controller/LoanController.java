package bibliotecaapi.bibliotecaapi.controller;

import java.time.LocalDate;
import bibliotecaapi.bibliotecaapi.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import bibliotecaapi.bibliotecaapi.dto.LoanDTO;

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

    @Autowired LoanService service;

    @PostMapping
	public ResponseEntity<LoanDTO> post(@RequestBody LoanDTO loan) {
        try {
            return ResponseEntity.ok().body(service.create(loan)) ;
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
