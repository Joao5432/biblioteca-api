package bibliotecaapi.bibliotecaapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bibliotecaapi.bibliotecaapi.dto.CustomerDTO;
import bibliotecaapi.bibliotecaapi.dto.CustomerPatchDTO;
import bibliotecaapi.bibliotecaapi.dto.LoanDTO;
import bibliotecaapi.bibliotecaapi.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController{

    @Autowired CustomerService service;

    @PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customer) {
        try {
            return ResponseEntity.ok().body(service.create(customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findCustomer(  @RequestParam(required = false) Long id,
                                            @RequestParam(required = false) String nome,
                                            @RequestParam(required = false) LocalDate dataNascimento) {    
        if (id != null) {
            return ResponseEntity.ok().body(service.findById(id));
        } else if (nome != null) {
            List<CustomerDTO> customers = service.findByNome(nome);
            if (!customers.isEmpty()) {
                return ResponseEntity.ok().body(customers);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else if (dataNascimento != null) {
            List<CustomerDTO> customers = service.findByDataNascimento(dataNascimento);
            if (!customers.isEmpty()) {
                return ResponseEntity.ok().body(customers);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok().body(service.FindAll(0, 10));
    }

    @GetMapping("{id}/loans")
    public ResponseEntity<List<LoanDTO>> findLoans(@PathVariable("id") Long id){ 
        // Recupera os empréstimos do cliente
    List<LoanDTO> loans = service.findLoansByCustomer(id);
    
    // Se houver empréstimos, retorna a lista com sucesso
    if (!loans.isEmpty()) {
        return ResponseEntity.ok().body(loans);
    }
    
    // Caso contrário, retorna uma resposta 404
    return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
}

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") Long id, @RequestBody CustomerDTO dto){
        try {
           return ResponseEntity.ok().body(service.update(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> updateStatus(@PathVariable("id") Long id, @RequestBody CustomerPatchDTO dto){
        try {
            return ResponseEntity.ok().body(service.updateStatus(id, dto));
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
