package dto;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class CoordenadaDTO {
    private UUID id;
    private UUID repartidorId;
    private UUID pedidoId;
    private Double latitud;
    private Double longitud;
    private OffsetDateTime timestamp;
}