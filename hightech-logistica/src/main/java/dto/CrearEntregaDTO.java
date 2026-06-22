package dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class CrearEntregaDTO {

    @NotNull(message = "El pedido es obligatorio")
    private UUID pedidoId;

    @NotNull(message = "El repartidor es obligatorio")
    private UUID repartidorId;

    private String fotoUrl;
    private String mensaje;
    private String metodoPago;
    private String observaciones;
    private String estado = "COMPLETA";
}
