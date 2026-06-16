package repository;


import entity.Producto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductoRepository implements PanacheRepositoryBase<Producto, UUID> {

    public List<Producto> findAllActivos() {
        return list("activo", true);
    }

    public List<Producto> findByCategoria(UUID categoriaId) {
        return list("categoria.id = ?1 and activo = true", categoriaId);
    }

    public List<Producto> findByProveedor(UUID proveedorId) {
        return list("proveedor.id = ?1 and activo = true", proveedorId);
    }

    public List<Producto> findStockCritico() {
        return list("stockActual <= stockMinimo and activo = true");
    }

    public boolean existsByNombre(String nombre) {
        return count("nombre", nombre) > 0;
    }
}