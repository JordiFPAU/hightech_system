package dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class RutaDTO {
    private UUID id;
    private UUID repartidorId;
    private LocalDate fecha;
    private String estado;
    private OffsetDateTime createdAt;
    private List<PuntoRutaDTO> puntos;
}