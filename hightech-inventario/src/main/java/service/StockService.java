package service;

import dto.AjustarStockDTO;
import dto.StockMovimientoDTO;
import entity.StockMovimiento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.StockMovimientoMapper;
import repository.ProductoRepository;
import repository.StockMovimientoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class StockService {

    @Inject
    ProductoRepository productoRepository;

    @Inject
    StockMovimientoRepository stockMovimientoRepository;

    @Inject
    StockMovimientoMapper stockMovimientoMapper;

    @Transactional
    public StockMovimientoDTO ajustarStock(AjustarStockDTO dto) {
        var producto = productoRepository.findByIdOptional(dto.productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        if (!dto.tipo.equals("ENTRADA") && !dto.tipo.equals("SALIDA")) {
            throw new BadRequestException("El tipo debe ser ENTRADA o SALIDA");
        }

        int stockAnterior = producto.stockActual;
        int stockNuevo;

        if (dto.tipo.equals("ENTRADA")) {
            stockNuevo = stockAnterior + dto.cantidad;
        } else {
            if (stockAnterior < dto.cantidad) {
                throw new BadRequestException("Stock insuficiente — disponible: " + stockAnterior);
            }
            stockNuevo = stockAnterior - dto.cantidad;
        }

        producto.stockActual = stockNuevo;
        productoRepository.persist(producto);

        var movimiento = new StockMovimiento();
        movimiento.producto = producto;
        movimiento.usuarioId = dto.usuarioId;
        movimiento.tipo = dto.tipo;
        movimiento.cantidad = dto.cantidad;
        movimiento.stockAnterior = stockAnterior;
        movimiento.stockNuevo = stockNuevo;
        movimiento.observacion = dto.observacion;

        stockMovimientoRepository.persist(movimiento);
        return stockMovimientoMapper.toDTO(movimiento);
    }

    public List<StockMovimientoDTO> historialPorProducto(UUID productoId) {
        return stockMovimientoRepository.findByProducto(productoId)
                .stream()
                .map(stockMovimientoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
