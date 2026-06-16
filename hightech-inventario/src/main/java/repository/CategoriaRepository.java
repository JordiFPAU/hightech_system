package repository;
import entity.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepositoryBase<Categoria, UUID> {

    public Optional<Categoria> findByNombre(String nombre) {
        return find("nombre", nombre).firstResultOptional();
    }

    public List<Categoria> findAllActivas() {
        return list("activo", true);
    }
}