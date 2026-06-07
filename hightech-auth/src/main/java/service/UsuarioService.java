package service;

import dto.CrearUsuarioDTO;
import dto.LoginDTO;
import dto.TokenDTO;
import dto.UsuarioDTO;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.UsuarioMapper;
import repo.RolRepository;
import repo.UsuarioRepository;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    RolRepository rolRepository;
    @Inject
    UsuarioMapper usuarioMapper;


    @Transactional
    public UsuarioDTO crear(CrearUsuarioDTO dto){
        if(usuarioRepository.existsByEmail(dto.email)){
            throw new RuntimeException("El email ya está registrado");
        }
        var rol = rolRepository.findByActivo(dto.rolId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        var usuario = usuarioMapper.toEntity(dto,rol);
        usuario.passwordHash = BcryptUtil.bcryptHash(dto.password);
        usuarioRepository.persist(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Transactional
    public TokenDTO login(LoginDTO dto) {
        var usuario = usuarioRepository.findByEmailActivo(dto.email)
                .orElseThrow(() -> new BadRequestException("Credenciales inválidas"));

        if (!BcryptUtil.matches(dto.password, usuario.passwordHash)) {
            throw new BadRequestException("Credenciales inválidas");
        }

        usuario.ultimoAcceso = OffsetDateTime.now();
        usuarioRepository.persist(usuario);

        String token = Jwt.issuer("https://hightech.com")
                .subject(usuario.id.toString())
                .groups(usuario.rol.nombre)
                .claim("email", usuario.email)
                .claim("nombre", usuario.nombre + " " + usuario.apellido)
                .expiresIn(Duration.ofHours(8))
                .sign();

        return new TokenDTO(token, "Bearer", usuarioMapper.toDto(usuario));
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAllActivos()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(UUID id) {
        return usuarioRepository.findByIdOptional(id)
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }
}
