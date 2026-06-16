package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    public Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    public Proveedor proveedor;

    @Column(nullable = false, length = 200)
    public String nombre;

    @Column
    public String descripcion;

    @Column(name = "codigo_marca", length = 100)
    public String codigoMarca;

    @Column(name = "precio_venta", precision = 10, scale = 2)
    public BigDecimal precioVenta = BigDecimal.ZERO;

    @Column(name = "precio_costo", precision = 10, scale = 2)
    public BigDecimal precioCosto = BigDecimal.ZERO;

    @Column(name = "stock_actual", nullable = false)
    public Integer stockActual = 0;

    @Column(name = "stock_minimo", nullable = false)
    public Integer stockMinimo = 10;

    @Column(name = "fecha_caducidad")
    public LocalDate fechaCaducidad;

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
