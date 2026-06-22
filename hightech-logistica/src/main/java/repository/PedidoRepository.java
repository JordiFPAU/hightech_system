package repository;

import entity.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<Pedido, UUID> {
    public List<Pedido> findByEstado(String estado) {
        return list("estado", estado);
    }

    public List<Pedido> findByRepartidor(UUID repartidorId) {
        return list("repartidorId = ?1 order by createdAt desc", repartidorId);
    }

    public List<Pedido> findByCliente(UUID clienteId) {
        return list("cliente.id = ?1 order by createdAt desc", clienteId);
    }

    public List<Pedido> findPendientesPorRepartidor(UUID repartidorId) {
        return list("repartidorId = ?1 and estado in ('PENDIENTE','EN_RUTA')", repartidorId);
    }
}
