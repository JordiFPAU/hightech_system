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
@Table(name = "stock_movimientos")
public class StockMovimiento extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    public Producto producto;

    @Column(name = "usuario_id", nullable = false)
    public UUID usuarioId;

    @Column(nullable = false, length = 10)
    public String tipo;

    @Column(nullable = false)
    public Integer cantidad;

    @Column(name = "stock_anterior", nullable = false)
    public Integer stockAnterior;

    @Column(name = "stock_nuevo", nullable = false)
    public Integer stockNuevo;

    @Column
    public String observacion;

    @Column(name = "created_at", updatable = false)
    public OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
    }
}