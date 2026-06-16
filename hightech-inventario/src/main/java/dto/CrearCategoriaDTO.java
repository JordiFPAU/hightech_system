package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearCategoriaDTO {

    @NotBlank
    public String nombre;

    public String descripcion;

}
