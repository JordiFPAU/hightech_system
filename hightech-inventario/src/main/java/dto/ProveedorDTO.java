package dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ProveedorDTO {
    public UUID id;
    public String nombre;
    public String telefono;
    public String infoAdicional;
    public Boolean activo;
}
