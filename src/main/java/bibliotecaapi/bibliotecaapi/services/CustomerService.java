package bibliotecaapi.bibliotecaapi.services;

import java.time.LocalDate;
import java.util.Optional;

import bibliotecaapi.bibliotecaapi.model.Customer;

//import java.util.List;

import bibliotecaapi.bibliotecaapi.repository.CustomerRepository;
import lombok.NonNull;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(){

    }

    public Optional<Customer> find(Long id){
        return customerRepository.findById(id.longValue());
    }

    public Optional<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    public Optional<Customer> findByBirthDate(LocalDate birthDate){
        return customerRepository.findByBirthDate(birthDate);
    }

    public void create(@NonNull Customer customer){
        customerRepository.save(customer);
    }   
    
    public Boolean delete(@NonNull Long id){
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Boolean update(@NonNull Customer customer){
        if (customerRepository.existsById(customer.getId().longValue())){
            customerRepository.save(customer);            
            return true;
        }
        return false;
    }
}


