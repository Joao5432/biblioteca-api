package bibliotecaapi.bibliotecaapi.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibliotecaapi.bibliotecaapi.dto.CustomerDTO;
import bibliotecaapi.bibliotecaapi.model.Customer;
import bibliotecaapi.bibliotecaapi.model.Status;
import bibliotecaapi.bibliotecaapi.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired private CustomerRepository repository;

    public CustomerDTO create(CustomerDTO dto){
        Customer model = new Customer();
       model.setName(dto.getName());
       model.setLastname(dto.getLastname());
       model.setAddress(dto.getAddress());
       model.setCity(dto.getCity());
        repository.save(model);

        return dto;
    }   
    

}


