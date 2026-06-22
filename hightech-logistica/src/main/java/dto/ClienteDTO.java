package dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClienteDTO {
    private UUID id;
    private String nombre;
    private String rucCi;
    private String telefono;
    private String correo;
    private String direccion;
    private Boolean activo;
}