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
@Table(name = "pedidos")
public class Pedido extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_entrega_id")
    public PuntoEntrega puntoEntrega;

    @Column(name = "repartidor_id")
    public UUID repartidorId;

    @Column(name = "numero_factura", length = 50)
    public String numeroFactura;

    @Column(nullable = false, length = 20)
    public String estado = "PENDIENTE";

    @Column(name = "es_urgente", nullable = false)
    public Boolean esUrgente = false;

    @Column
    public String observaciones;

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
