package service;

import dto.RolDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapper.RolMapper;
import org.hibernate.Incubating;
import repo.RolRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RolService {
    @Inject
    RolRepository rolRepository;
    @Inject
    RolMapper rolMapper;

    public List<RolDTO> listarTodos() {
        return rolRepository.listAll()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RolDTO buscarPorId(UUID id){
        return rolRepository.findByIdOptional(id)
                .map(rolMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }
}
