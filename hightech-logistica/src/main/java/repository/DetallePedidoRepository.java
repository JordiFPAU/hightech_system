package repository;

import entity.DetallePedido;
import entity.Entrega;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DetallePedidoRepository implements PanacheRepositoryBase<DetallePedido, UUID> {

    public List<DetallePedido> finByPedido (UUID pedidoId) {
        return list("pedido.id", pedidoId);
    }

}
