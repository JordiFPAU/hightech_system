package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "coordenadas_repartidor")
public class CoordenadaRepartidor extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "repartidor_id", nullable = false)
    private UUID repartidorId;

    @Column(name = "pedido_id")
    private UUID pedidoId;

    @Column(name = "ubicacion", columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point ubicacion;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    @PrePersist
    public void prePersist() {
        if (timestamp == null) {
            timestamp = OffsetDateTime.now();
        }
    }
}
