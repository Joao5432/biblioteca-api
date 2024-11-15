package bibliotecaapi.bibliotecaapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import bibliotecaapi.bibliotecaapi.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

    @NotBlank (message = "Título requirido")
    private String title;

    @NotBlank (message = "Autor requirido")
    private String author;

    @NotBlank (message = "ISBN requirido")
    private String isbn;

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

    @NotNull (message = "Estado requirido")
    private Status status;

}
