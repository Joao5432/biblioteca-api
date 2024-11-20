
package bibliotecaapi.bibliotecaapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.BookDTO;
import bibliotecaapi.bibliotecaapi.dto.BookPatchDTO;
import bibliotecaapi.bibliotecaapi.model.Book;
import bibliotecaapi.bibliotecaapi.model.Status;
import bibliotecaapi.bibliotecaapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

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

    public BookDTO findById(Long id) {
        Optional<Book> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            Book model = optionalModel.get();
            BookDTO dto = new BookDTO();
            BeanUtils.copyProperties(model, dto,"status");
            return dto;
        }
        
        throw new EntityNotFoundException("Livro não encontrado");
    }

    public Page<BookDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = repository.findAll(pageable);
        return books.map(book -> {
            BookDTO dto = new BookDTO();
            BeanUtils.copyProperties(book, dto);
            return dto;
        });
    }

    public void delete(Long id) {
        Optional<Book> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            Book model = optionalModel.get();
            model.setStatus(Status.INACTIVE);
            repository.save(model);
        } else{
            throw new EntityNotFoundException("Livro não encontrado");
        }
    }

    public BookDTO update(Long id, BookDTO dto) {
        Optional<Book> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            Book model = optionalModel.get();
            BeanUtils.copyProperties(dto, model, "id", "status");
            Book updatedModel = repository.save(model);
            BookDTO updatedDto = new BookDTO();
            BeanUtils.copyProperties(updatedModel, updatedDto);
            return updatedDto;
        }
        throw new EntityNotFoundException("Livro não encontrado");
    }

    public BookDTO updateStatus(Long id, BookPatchDTO dto) {
        Optional<Book> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            Book model = optionalModel.get();
            model.setStatus(dto.getStatus());
            repository.save(model);

            BookDTO dtoToReturn = new BookDTO();
            BeanUtils.copyProperties(model, dtoToReturn, "status");
            return dtoToReturn;
        }
        throw new EntityNotFoundException("Livro não encontrado");
    }
    

}
