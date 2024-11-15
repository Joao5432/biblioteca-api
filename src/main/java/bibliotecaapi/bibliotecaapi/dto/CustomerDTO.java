package bibliotecaapi.bibliotecaapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotNull
    private String name;

    @NotBlank(message = "Sobrenome requirido")
    private String lastname;

    @NotBlank(message = "Endereço requirido")
    private String address;

    @NotBlank(message = "Cidade requirida")
    private String city;

    @NotNull(message = "Estado requirido")
    private BigDecimal state;

    @NotBlank(message = "País requirido")
    private String country;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    
}
