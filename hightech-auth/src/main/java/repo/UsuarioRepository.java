package repo;

import entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, UUID> {
    public Optional<Usuario> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
    public Optional<Usuario> findByEmailActivo(String email) {
        return find("email = ?1 and activo = true", email).firstResultOptional();
    }
    public List<Usuario> findAllActivos() {
        return list("activo", true);
    }
    public boolean existsByEmail(String email) {
        return count("email", email) > 0;

    }
}
