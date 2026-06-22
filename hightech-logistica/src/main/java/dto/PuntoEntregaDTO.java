package dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PuntoEntregaDTO {
    private UUID id;
    private UUID clienteId;
    private String descripcion;
    private String direccionTexto;
    private Double latitud;
    private Double longitud;
    private Boolean esPrincipal;
    private Boolean activo;
}
