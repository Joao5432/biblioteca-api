package bibliotecaapi.bibliotecaapi.controller;

import bibliotecaapi.bibliotecaapi.services.BookService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import bibliotecaapi.bibliotecaapi.dto.BookDTO;
import bibliotecaapi.bibliotecaapi.dto.BookPatchDTO;

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

@RestController
@RequestMapping("/books")
public class BookController{

    @Autowired BookService service;

    @PostMapping
	public ResponseEntity<BookDTO> post(@RequestBody BookDTO book) {
        try {
            return ResponseEntity.ok().body(service.create(book)) ;
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> findyById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok().body(service.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Page<BookDTO>> findAll( @RequestParam(defaultValue = "0") int page, 
                                                  @RequestParam(defaultValue = "10") int size){
        Page<BookDTO> dtos = service.findAll(page,size);
        if (!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        }  
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> inactiveBook(@PathVariable("id") Long id){
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO dto){
        try {
            return ResponseEntity.ok().body(service.update(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<BookDTO> updateStatus(@PathVariable("id") Long id, @RequestBody BookPatchDTO dto){
        try {
            return ResponseEntity.ok().body(service.updateStatus(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    } 

}
