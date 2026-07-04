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
import org.eclipse.microprofile.jwt.JsonWebToken;
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

    @Inject
    JsonWebToken jwt;

    @Transactional
    public StockMovimientoDTO ajustarStock(AjustarStockDTO dto) {
        var producto = productoRepository.findByIdOptional(dto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        if (!dto.getTipo().equals("ENTRADA") && !dto.getTipo().equals("SALIDA")) {
            throw new BadRequestException("El tipo debe ser ENTRADA o SALIDA");
        }

        int stockAnterior = producto.getStockActual();
        int stockNuevo;

        if (dto.getTipo().equals("ENTRADA")) {
            stockNuevo = stockAnterior + dto.getCantidad();
        } else {
            if (stockAnterior < dto.getCantidad()) {
                throw new BadRequestException(
                        "Stock insuficiente — disponible: " + stockAnterior);
            }
            stockNuevo = stockAnterior - dto.getCantidad();
        }

        producto.setStockActual(stockNuevo);
        productoRepository.persist(producto);

        var movimiento = new StockMovimiento();
        movimiento.setProducto(producto);
        movimiento.setUsuarioId(UUID.fromString(jwt.getSubject())); // ← del token
        movimiento.setTipo(dto.getTipo());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setStockAnterior(stockAnterior);
        movimiento.setStockNuevo(stockNuevo);
        movimiento.setObservacion(dto.getObservacion());

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
