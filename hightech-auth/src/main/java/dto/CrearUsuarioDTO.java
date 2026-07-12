package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CrearUsuarioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    public String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    public String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    public String email;

    @NotBlank(message = "La contraseña es obligatoria")
    public String password;

    public String telefono;

    @NotNull(message = "El rol es obligatorio")
    public UUID rolId;

}
