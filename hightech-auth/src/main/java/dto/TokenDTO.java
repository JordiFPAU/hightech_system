package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    public String token;
    public String tipo = "Bearer";
    public UsuarioDTO usuario;
    }
