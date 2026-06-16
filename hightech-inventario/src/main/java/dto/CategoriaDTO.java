package dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoriaDTO {
    public UUID id;
    public String nombre;
    public String descripcion;
    public Boolean activo;
}
