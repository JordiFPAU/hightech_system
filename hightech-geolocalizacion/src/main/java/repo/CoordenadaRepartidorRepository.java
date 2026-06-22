package repo;
import entity.CoordenadaRepartidor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CoordenadaRepartidorRepository
        implements PanacheRepositoryBase<CoordenadaRepartidor, UUID> {

    public List<CoordenadaRepartidor> findByRepartidor(UUID repartidorId) {
        return list("repartidorId = ?1 order by timestamp desc", repartidorId);
    }

    public Optional<CoordenadaRepartidor> findUltimaUbicacion(UUID repartidorId) {
        return find("repartidorId = ?1 order by timestamp desc", repartidorId)
                .firstResultOptional();
    }

    public List<CoordenadaRepartidor> findByRepartidorYPedido(UUID repartidorId, UUID pedidoId) {
        return list("repartidorId = ?1 and pedidoId = ?2 order by timestamp asc",
                repartidorId, pedidoId);
    }
}