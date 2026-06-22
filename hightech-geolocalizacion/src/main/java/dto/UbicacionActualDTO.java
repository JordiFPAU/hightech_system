package dto;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class UbicacionActualDTO {
    private UUID repartidorId;
    private Double latitud;
    private Double longitud;
    private OffsetDateTime ultimaActualizacion;
}