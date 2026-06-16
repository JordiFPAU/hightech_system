package mapper;

import dto.CrearProveedorDTO;
import dto.ProveedorDTO;
import entity.Proveedor;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProveedorMapper {

    public ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) return null;

        ProveedorDTO dto = new ProveedorDTO();
        dto.id = proveedor.id;
        dto.nombre = proveedor.nombre;
        dto.telefono = proveedor.telefono;
        dto.infoAdicional = proveedor.infoAdicional;
        dto.activo = proveedor.activo;
        return dto;
    }

    public Proveedor toEntity(CrearProveedorDTO dto) {
        if (dto == null) return null;

        Proveedor proveedor = new Proveedor();
        proveedor.nombre = dto.nombre;
        proveedor.telefono = dto.telefono;
        proveedor.infoAdicional = dto.infoAdicional;
        proveedor.activo = true;
        return proveedor;
    }
}
