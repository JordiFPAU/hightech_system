package repository;


import entity.AlertaStock;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AlertaStockRepository implements PanacheRepositoryBase<AlertaStock, UUID> {

    public List<AlertaStock> findNoLeidas() {
        return list("leida = false order by createdAt desc");
    }

    public List<AlertaStock> findByProducto(UUID productoId) {
        return list("producto.id = ?1 order by createdAt desc", productoId);
    }
}
