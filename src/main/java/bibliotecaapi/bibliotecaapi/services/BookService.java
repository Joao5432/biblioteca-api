package bibliotecaapi.bibliotecaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.BookDTO;
import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.micrometer.common.lang.NonNull;

@Service
public class BookService {

    @Autowired private BookRepository repository;

    public BookDTO create(BookDTO dto){
        Book model = new Book();
        model.setTitle(dto.getTitle());
        model.setAuthor(dto.getAuthor());
        model.setIsbn(dto.getIsbn());
        model.setPublishedDate(dto.getPublishedDate());
        repository.save(model);

        return dto;
    }   
}
