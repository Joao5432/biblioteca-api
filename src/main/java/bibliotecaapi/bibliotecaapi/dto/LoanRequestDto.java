package bibliotecaapi.bibliotecaapi.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDto {

    @NotNull (message = "Cliente requirido")
    private Long idCustumer;
    @NotNull (message = "Livro requirido")
    private List<Long> idBooks; 

}
