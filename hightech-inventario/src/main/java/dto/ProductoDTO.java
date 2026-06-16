package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProductoDTO {
    public UUID id;
    public String nombre;
    public String descripcion;
    public String codigoMarca;
    public BigDecimal precioVenta;
    public BigDecimal precioCosto;
    public Integer stockActual;
    public Integer stockMinimo;
    public LocalDate fechaCaducidad;
    public Boolean activo;
    public CategoriaDTO categoria;
    public ProveedorDTO proveedor;
}
