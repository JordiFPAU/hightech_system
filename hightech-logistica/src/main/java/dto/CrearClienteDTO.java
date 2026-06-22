package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearClienteDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String rucCi;
    private String telefono;
    private String correo;
    private String direccion;
}
