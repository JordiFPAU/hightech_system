package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class CrearPuntoEntregaDTO {

    @NotNull(message = "El cliente es obligatorio")
    private UUID clienteId;

    private String descripcion;

    @NotBlank(message = "La dirección de texto es obligatoria")
    private String direccionTexto;

    @NotNull(message = "La latitud es obligatoria")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    private Double longitud;

    private Boolean esPrincipal = false;
}