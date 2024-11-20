package bibliotecaapi.bibliotecaapi.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDTO {

    @NotNull (message = "Cliente requirido")
    private Customer customer;

    @NotNull (message = "Livros requiridos")
    private List<Book> books;

    @NotBlank (message = "Autor requirido")
    private String author;

    @NotBlank (message = "ISBN requirido")
    private String isbn;

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDate publishedDate;

    @NotNull (message = "Situação requirida")
    private Status status;

}
