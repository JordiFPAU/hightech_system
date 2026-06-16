package dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class StockMovimientoDTO {
    public UUID id;
    public String tipo;
    public Integer cantidad;
    public Integer stockAnterior;
    public Integer stockNuevo;
    public String observacion;
    public OffsetDateTime createdAt;
    public UUID productoId;
    public String productoNombre;
    public UUID usuarioId;
}
