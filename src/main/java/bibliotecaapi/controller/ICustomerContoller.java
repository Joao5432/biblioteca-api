package bibliotecaapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ICustomerContoller<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<?> get(Long id);
    ResponseEntity<?> getByName(String name);
    ResponseEntity<?> getByDate(LocalDate birthDate);
    ResponseEntity<T> post(T obj);
    ResponseEntity<?> put(T obj);
    ResponseEntity<?> patch(T obj);
    ResponseEntity<?> delete(Long id);
}
