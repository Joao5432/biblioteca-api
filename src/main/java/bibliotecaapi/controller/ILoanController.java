package bibliotecaapi.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import bibliotecaapi.model.Customer;

public interface ILoanController<T> {
    ResponseEntity<?> getByCustomer(Customer customer);
    ResponseEntity<?> getByDate(LocalDate publishedDate);
    ResponseEntity<T> post(T obj);
    ResponseEntity<?> patch(T obj);
}
