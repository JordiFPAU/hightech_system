package repository;

import entity.Proveedor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ProveedorRepository implements PanacheRepositoryBase<Proveedor, UUID> {

    public List<Proveedor> findAllActivos() {
        return list("activo", true);
    }

    public Optional<Proveedor> findByNombre(String nombre) {
        return find("nombre", nombre).firstResultOptional();
    }
}