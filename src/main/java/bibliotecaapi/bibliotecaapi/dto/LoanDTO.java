package bibliotecaapi.bibliotecaapi.dto;

import java.time.LocalDate;
import java.util.List;

import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Status;

public class LoanDTO {

    private Customer customer;

    private List<Book> books;

    private String author;

    private String isbn;

    private LocalDate publishedDate;

    private Status status;

}
