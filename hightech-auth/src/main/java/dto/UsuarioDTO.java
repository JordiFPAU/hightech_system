package dto;

import lombok.Data;

import java.util.UUID;
@Data
public class UsuarioDTO {
    public UUID id;
    public String nombre;
    public String apellido;
    public String email;
    public String telefono;
    public Boolean activo;
    public RolDTO rol;
}
