package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CrearProductoDTO {
    @NotBlank(message = "El nombre es obligatorio")
    public String nombre;

    public String descripcion;
    public String codigoMarca;

    @NotNull(message = "El precio de venta es obligatorio")
    @PositiveOrZero(message = "El precio de venta no puede ser negativo")
    public BigDecimal precioVenta;

    @NotNull(message = "El precio de costo es obligatorio")
    @PositiveOrZero(message = "El precio de costo no puede ser negativo")
    public BigDecimal precioCosto;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    public Integer stockActual = 0;

    @PositiveOrZero(message = "El stock mínimo no puede ser negativo")
    public Integer stockMinimo = 10;

    public LocalDate fechaCaducidad;

    @NotNull(message = "La categoría es obligatoria")
    public UUID categoriaId;

    public UUID proveedorId;

}
