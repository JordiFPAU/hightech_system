package dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class IniciarRutaDTO {

    @NotNull(message = "El repartidor es obligatorio")
    private UUID repartidorId;
}