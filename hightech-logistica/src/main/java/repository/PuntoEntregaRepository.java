package repository;

import entity.PuntoEntrega;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PuntoEntregaRepository implements PanacheRepositoryBase<PuntoEntrega, UUID> {
    public List<PuntoEntrega> findByClient(UUID clienteId){
        return list("cliente.id = ?1 and activo = true", clienteId);
    }

    public List<PuntoEntrega> findPrincipalByCliente(UUID clienteId){
        return list("cliente.id = ?1 and esPrincipal = true and activo = true", clienteId);
    }
}
