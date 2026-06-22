package dto;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class PedidoDTO {
    private UUID id;
    private ClienteDTO cliente;
    private PuntoEntregaDTO puntoEntrega;
    private UUID repartidorId;
    private String numeroFactura;
    private String estado;
    private Boolean esUrgente;
    private String observaciones;
    private OffsetDateTime createdAt;
    private List<DetallePedidoDTO> detalles;
}