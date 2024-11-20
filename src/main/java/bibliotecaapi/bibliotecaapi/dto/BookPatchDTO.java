package bibliotecaapi.bibliotecaapi.dto;

import bibliotecaapi.bibliotecaapi.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookPatchDTO {

    @NotNull (message = "Situação requirida")
    private Status status;

}
