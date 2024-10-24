package bibliotecaapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IBookController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<?> get(Long id);
    ResponseEntity<T> post(T obj);
    ResponseEntity<?> put(T obj);
    ResponseEntity<?> patch(T obj);
    ResponseEntity<?> delete(Long id);
}
