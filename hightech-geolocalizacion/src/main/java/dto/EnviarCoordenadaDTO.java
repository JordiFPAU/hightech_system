package dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class EnviarCoordenadaDTO {

    @NotNull(message = "El repartidor es obligatorio")
    private UUID repartidorId;

    private UUID pedidoId;

    @NotNull(message = "La latitud es obligatoria")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    private Double longitud;
}