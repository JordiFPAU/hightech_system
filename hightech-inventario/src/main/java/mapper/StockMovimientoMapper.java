package mapper;

import dto.StockMovimientoDTO;
import entity.StockMovimiento;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockMovimientoMapper {

    public StockMovimientoDTO toDTO(StockMovimiento movimiento) {
        if (movimiento == null) return null;

        StockMovimientoDTO dto = new StockMovimientoDTO();
        dto.id = movimiento.id;
        dto.tipo = movimiento.tipo;
        dto.cantidad = movimiento.cantidad;
        dto.stockAnterior = movimiento.stockAnterior;
        dto.stockNuevo = movimiento.stockNuevo;
        dto.observacion = movimiento.observacion;
        dto.createdAt = movimiento.createdAt;
        dto.usuarioId = movimiento.usuarioId;
        dto.productoId = movimiento.producto.id;
        dto.productoNombre = movimiento.producto.nombre;
        return dto;
    }
}
