
package bibliotecaapi.bibliotecaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bibliotecaapi.bibliotecaapi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{


}
