package bibliotecaapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bibliotecaapi.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/books")
public class BookController implements IBookController<Book>{

    @Override
	@GetMapping(produces = "application/json")
    @Operation(summary = "Retorna a lista de livros")
    public ResponseEntity<List<Book>> getAll() {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Book> get(@PathVariable("id") Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    @PostMapping
	@Operation(summary = "Cria um livro")
	public ResponseEntity<Book> post(@RequestBody Book book) {
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }

    @Override
    @PutMapping
	@Operation(summary = "Atualiza um livro")
    public ResponseEntity<Book> put(@RequestBody Book book) {
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    @PatchMapping
	@Operation(summary = "Atualiza um livro")
    public ResponseEntity<Book> patch(@RequestBody Book book) {
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }

    @Override
    @DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui um livro")
    public ResponseEntity<Book> delete(@PathVariable("id") Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
