package bibliotecaapi.bibliotecaapi.controller;

import java.time.LocalDate;
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

import bibliotecaapi.bibliotecaapi.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController{

	@GetMapping(produces = "application/json")
	/*  @ApiResponses(value = {
			@ApiResponse(responseCode = "200"
					   , description = "Resultado com sucesso"
			//		   , content = {@Content(mediaType = "application/json")}
			),
			@ApiResponse(responseCode = "500"
			           , description = "Erro interno do servidor"
			//           , content = {@Content(mediaType = "application/json")}
			)
	})*/ //comentado pois não está funcionando

    @Operation(summary = "Retorna a lista de clientes")
    public ResponseEntity<List<Customer>> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Customer> get(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
 
    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<Customer> getByName(@PathVariable("name") String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @GetMapping(value = "/{birthDate}", produces = "application/json")
    public ResponseEntity<Customer> getByDate(@PathVariable("birthDate") LocalDate birthDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDate'");
    }

    @PostMapping
	@Operation(summary = "Cria um cliente")
	public ResponseEntity<Customer> post(@RequestBody Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }

	@PutMapping
	@Operation(summary = "Atualiza um cliente")
	public ResponseEntity<Customer> put(@RequestBody Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

	@PatchMapping
	@Operation(summary = "Atualiza um cliente") 
    public ResponseEntity<Customer> patch(@RequestBody Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Exclui um cliente")
	public ResponseEntity<Customer> delete(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
