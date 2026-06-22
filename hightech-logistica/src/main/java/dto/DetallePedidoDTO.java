package dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DetallePedidoDTO {
    private UUID id;
    private UUID productoId;
    private Integer cantidadSolicitada;
    private Integer cantidadEntregada;
    private BigDecimal precioUnitario;
}