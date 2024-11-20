package bibliotecaapi.bibliotecaapi.controller;


import bibliotecaapi.bibliotecaapi.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import bibliotecaapi.bibliotecaapi.dto.LoanDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
