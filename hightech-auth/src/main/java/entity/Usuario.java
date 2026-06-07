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
@Table(name = "usuarios")
public class Usuario extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    public UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    public Rol rol;

    @Column(nullable = false, length = 100)
    public String nombre;

    @Column(nullable = false, length = 100)
    public String apellido;

    @Column(nullable = false, unique = true, length = 150)
    public String email;

    @Column(name = "password_hash", nullable = false)
    public String passwordHash;

    @Column(length = 20)
    public String telefono;

    @Column(nullable = false)
    public Boolean activo = true;

    @Column(name = "ultimo_acceso")
    public OffsetDateTime ultimoAcceso;

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
