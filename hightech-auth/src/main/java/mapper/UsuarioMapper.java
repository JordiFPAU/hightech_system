package mapper;

import dto.CrearUsuarioDTO;
import dto.UsuarioDTO;
import entity.Rol;
import entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioMapper {
    @Inject
    RolMapper rolMapper;

    public UsuarioDTO toDto(Usuario usuario){
        if(usuario== null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.id = usuario.id;
        dto.nombre = usuario.nombre;
        dto.apellido = usuario.apellido;
        dto.email = usuario.email;
        dto.telefono = usuario.telefono;
        dto.activo = usuario.activo;
        dto.rol = rolMapper.toDTO(usuario.rol);
        return dto;
    }
    public Usuario toEntity(CrearUsuarioDTO dto, Rol rol) {
        if (dto == null) return null;
        Usuario usuario = new Usuario();
        usuario.nombre = dto.nombre;
        usuario.apellido = dto.apellido;
        usuario.email = dto.email;
        usuario.telefono = dto.telefono;
        usuario.rol = rol;
        usuario.activo = true;
        return usuario;
    }

}

