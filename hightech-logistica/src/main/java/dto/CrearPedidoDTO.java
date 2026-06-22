package dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CrearPedidoDTO {

    @NotNull(message = "El cliente es obligatorio")
    private UUID clienteId;

    private UUID puntoEntregaId;
    private UUID repartidorId;
    private String numeroFactura;
    private Boolean esUrgente = false;
    private String observaciones;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid
    private List<ItemPedidoDTO> items;
}
