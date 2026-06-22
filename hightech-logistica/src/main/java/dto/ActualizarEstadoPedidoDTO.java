package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class ActualizarEstadoPedidoDTO {

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    private UUID repartidorId;
}
