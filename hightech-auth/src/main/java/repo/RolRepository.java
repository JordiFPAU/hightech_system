package repo;

import entity.Rol;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;
@ApplicationScoped
public class RolRepository implements PanacheRepositoryBase<Rol, UUID> {

    public Optional<Rol> findByNombre(String nombre) {
        return find("nombre", nombre).firstResultOptional();
    }
    public Optional<Rol> findByActivo(UUID id){
        return find("id = ?1 and activo = true", id).firstResultOptional();

    }
}
