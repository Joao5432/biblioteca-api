package bibliotecaapi.bibliotecaapi.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.BookDTO;
import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.model.Status;
import bibliotecaapi.bibliotecaapi.repository.BookRepository;

@Service
public class BookService {

    @Autowired private BookRepository repository;

    public BookDTO create(BookDTO dto){
        Book model = new Book();
        BeanUtils.copyProperties(dto, model, "status");
        model.setStatus(Status.AVAILABLE);
        Book savedBook = repository.save(model);

        BookDTO newDto = new BookDTO();
        BeanUtils.copyProperties(savedBook, newDto);
        return newDto;
    }   
}
