package dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AlertaStockDTO {
    public UUID id;
    public UUID productoId;
    public String productoNombre;
    public Integer stockAlMomento;
    public Integer stockMinimo;
    public Boolean leida;
    public OffsetDateTime createdAt;
}
