package bibliotecaapi.bibliotecaapi.controller;


import bibliotecaapi.bibliotecaapi.services.LoanService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import bibliotecaapi.bibliotecaapi.dto.LoanDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanDatePatchDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanStatusPatchDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/loans")
public class LoanController{

    @Autowired LoanService service;

    @PostMapping
	public ResponseEntity<LoanDTO> post(@RequestBody @Valid LoanDTO loan) {
        try {
            return ResponseEntity.ok().body(service.create(loan)) ;
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<?> findCustomer(  @RequestParam(required = false) Long id,
                                            @RequestParam(required = false) LocalDate publishedDate) {    
        if (id != null) {
            return service.findById(id)
                    .map(dto -> ResponseEntity.ok().body(dto))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (publishedDate != null) {
            List<LoanDTO> loans = service.findByDate(publishedDate);
            if (!loans.isEmpty()) {
                return ResponseEntity.ok().body(loans);
            } else {
                return ResponseEntity.noContent().build();
            }
        } 
        return ResponseEntity.badRequest().body("Informe pelo menos um parâmetro de busca.");
    }

    @PatchMapping("{id}")
    public ResponseEntity<LoanDTO> updateStatus(@PathVariable("id") Long id, @RequestBody LoanDatePatchDTO dto){
        try {
            return ResponseEntity.ok().body(service.updateDate(id, dto));
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<LoanDTO> updateStatus(@PathVariable("id") Long id, @RequestBody LoanStatusPatchDTO dto){
        try {
            return ResponseEntity.ok().body(service.updateStatus(id, dto));
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
