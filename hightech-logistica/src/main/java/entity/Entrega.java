package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "entregas")
public class Entrega extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    public Pedido pedido;

    @Column(name = "repartidor_id", nullable = false)
    public UUID repartidorId;

    @Column(name = "foto_url", length = 500)
    public String fotoUrl;

    @Column
    public String mensaje;

    @Column(name = "metodo_pago", length = 50)
    public String metodoPago;

    @Column
    public String observaciones;

    @Column(nullable = false, length = 20)
    public String estado = "COMPLETA";

    @Column(name = "timestamp_entrega", nullable = false)
    public OffsetDateTime timestampEntrega;

    @Column(name = "created_at", updatable = false)
    public OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
        if (timestampEntrega == null) {
            timestampEntrega = OffsetDateTime.now();
        }
    }

}
