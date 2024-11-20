package bibliotecaapi.bibliotecaapi.controller;

import bibliotecaapi.bibliotecaapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import bibliotecaapi.bibliotecaapi.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
