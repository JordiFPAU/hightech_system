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
@Table(name = "puntos_entrega")
public class PuntoEntrega extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(length = 200)
    private String descripcion;
    @Column(name = "direccion_texto",nullable = false)
    private String direccion_texto;
    @Column(name = "coordenadas", columnDefinition = "geometry(Point,4326)")
    private Point coordenadas;

    @Column(name = "es_principal", nullable = false)
    public Boolean esPrincipal = false;

    @Column(nullable = false)
    public Boolean activo = true;

    @Column(name = "created_at", updatable = false)
    public OffsetDateTime createdAt;

    @Column(name = "updated_at")
    public OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }

}
