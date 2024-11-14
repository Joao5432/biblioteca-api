package bibliotecaapi.bibliotecaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bibliotecaapi.bibliotecaapi.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{


}
