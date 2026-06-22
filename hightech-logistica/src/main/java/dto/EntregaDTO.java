package dto;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class EntregaDTO {
    private UUID id;
    private UUID pedidoId;
    private UUID repartidorId;
    private String fotoUrl;
    private String mensaje;
    private String metodoPago;
    private String observaciones;
    private String estado;
    private OffsetDateTime timestampEntrega;
}
