package bibliotecaapi.bibliotecaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.micrometer.common.lang.NonNull;

public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    public BookService(){

    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> find(Long id){
        return bookRepository.findById(id.longValue());
    }

    public void create(@NonNull Book book) {
        bookRepository.save(book);
    }

    public Boolean delete(@NonNull Long id){
        if (bookRepository.existsById(id)){
            return true;
        }
        return false;
    }

    public Boolean update(@NonNull Book book){
        if (bookRepository.existsById(book.getId().longValue())) {
            bookRepository.save(book);
            return true;
        }
        return false;
    }
}
