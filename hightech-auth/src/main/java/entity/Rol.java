package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Rol extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    public UUID id;

    @Column(nullable = false, unique = true, length = 50)
    public String nombre;
    @Column
    public String descripcion;

    @Column
    public Boolean activo;

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
    public void preUpdate(){
        updatedAt = OffsetDateTime.now();
    }
}

