package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    public String email;

    @NotBlank(message = "La contraseña es obligatoria")
    public String password;
}
