package bibliotecaapi.bibliotecaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import bibliotecaapi.bibliotecaapi.model.Status;

public class CustomerDTO {

    private String name;

    private String lastname;

    private String address;

    private String city;

    private BigDecimal state;

    private String country;
    
    private LocalDate birthDate;

    private Status status;

}
