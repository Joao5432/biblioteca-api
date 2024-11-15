package bibliotecaapi.bibliotecaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bibliotecaapi.bibliotecaapi.dto.CustomerDTO;
import bibliotecaapi.bibliotecaapi.services.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController{

    @Autowired CustomerService service;

    @PostMapping
	public ResponseEntity<CustomerDTO> post(@RequestBody CustomerDTO customer) {
        try {
            return ResponseEntity.ok().body(service.create(customer)) ;
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
