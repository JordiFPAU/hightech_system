package mapper;

import dto.CrearProductoDTO;
import dto.ProductoDTO;
import entity.Categoria;
import entity.Producto;
import entity.Proveedor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductoMapper {

    @Inject
    CategoriaMapper categoriaMapper;

    @Inject
    ProveedorMapper proveedorMapper;

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.id = producto.id;
        dto.nombre = producto.nombre;
        dto.descripcion = producto.descripcion;
        dto.codigoMarca = producto.codigoMarca;
        dto.precioVenta = producto.precioVenta;
        dto.precioCosto = producto.precioCosto;
        dto.stockActual = producto.stockActual;
        dto.stockMinimo = producto.stockMinimo;
        dto.fechaCaducidad = producto.fechaCaducidad;
        dto.activo = producto.activo;
        dto.categoria = categoriaMapper.toDTO(producto.categoria);
        dto.proveedor = proveedorMapper.toDTO(producto.proveedor);
        return dto;
    }

    public Producto toEntity(CrearProductoDTO dto, Categoria categoria, Proveedor proveedor) {
        if (dto == null) return null;

        Producto producto = new Producto();
        producto.nombre = dto.nombre;
        producto.descripcion = dto.descripcion;
        producto.codigoMarca = dto.codigoMarca;
        producto.precioVenta = dto.precioVenta;
        producto.precioCosto = dto.precioCosto;
        producto.stockActual = dto.stockActual != null ? dto.stockActual : 0;
        producto.stockMinimo = dto.stockMinimo != null ? dto.stockMinimo : 10;
        producto.fechaCaducidad = dto.fechaCaducidad;
        producto.categoria = categoria;
        producto.proveedor = proveedor;
        producto.activo = true;
        return producto;
    }
}
