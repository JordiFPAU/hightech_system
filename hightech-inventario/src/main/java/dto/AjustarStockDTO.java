package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class AjustarStockDTO {
    @NotNull(message = "El producto es obligatorio")
    public UUID productoId;

    @NotNull(message = "El tipo es obligatorio")
    @NotBlank(message = "El tipo debe ser ENTRADA o SALIDA")
    public String tipo;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    public Integer cantidad;

    public String observacion;
    public UUID usuarioId;
}
