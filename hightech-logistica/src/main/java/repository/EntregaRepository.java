package repository;

import entity.Entrega;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class EntregaRepository implements PanacheRepositoryBase<Entrega, UUID> {

    public Optional<Entrega> findByPedidoId(UUID pedidoId) {
        return find("pedido.id", pedidoId).firstResultOptional();
    }

    public List<Entrega> findByRepartidor(UUID repartidorId) {
        return list("repartidorId = ?1 order by timestampEntrega desc", repartidorId);
    }
}
