package mapper;

import dto.RolDTO;
import entity.Rol;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RolMapper {
    public RolDTO toDTO(Rol rol) {
        if (rol == null) return null;
        RolDTO dto = new RolDTO();
        dto.id = rol.id;
        dto.nombre = rol.nombre;
        dto.descripcion = rol.descripcion;
        dto.activo = rol.activo;
        return dto;
    }
    public Rol toEntity(RolDTO dto) {
        if (dto == null) return null;

        Rol rol = new Rol();
        rol.id = dto.id;
        rol.nombre = dto.nombre;
        rol.descripcion = dto.descripcion;
        rol.activo = dto.activo;
        return rol;
    }


}
