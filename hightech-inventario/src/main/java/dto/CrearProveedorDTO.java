package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearProveedorDTO {
    @NotBlank(message = "el nombre es obligatorio")
    public String nombre;
    public String telefono;
    public String infoAdicional;
}
