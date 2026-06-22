package repo;
import entity.Ruta;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RutaRepository implements PanacheRepositoryBase<Ruta, UUID> {

    public Optional<Ruta> findRutaActivaHoy(UUID repartidorId) {
        return find("repartidorId = ?1 and fecha = ?2 and estado = 'ACTIVA'",
                repartidorId, LocalDate.now())
                .firstResultOptional();
    }

    public List<Ruta> findByRepartidor(UUID repartidorId) {
        return list("repartidorId = ?1 order by fecha desc", repartidorId);
    }

    public List<Ruta> findRutasActivas() {
        return list("estado", "ACTIVA");
    }
}