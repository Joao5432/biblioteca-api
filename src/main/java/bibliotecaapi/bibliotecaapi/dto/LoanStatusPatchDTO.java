package bibliotecaapi.bibliotecaapi.dto;

import bibliotecaapi.bibliotecaapi.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanStatusPatchDTO {

    @NotNull (message = "Situação requirida")
    private Status status;

}
