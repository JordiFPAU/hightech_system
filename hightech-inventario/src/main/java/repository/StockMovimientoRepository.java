package repository;

import entity.StockMovimiento;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StockMovimientoRepository implements PanacheRepositoryBase<StockMovimiento, UUID> {

    public List<StockMovimiento> findByProducto(UUID productoId) {
        return list("producto.id = ?1 order by createdAt desc", productoId);
    }
}
