package service;

import dto.CrearProveedorDTO;
import dto.ProveedorDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.ProveedorMapper;
import repository.ProveedorRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProveedorService {

    @Inject
    ProveedorRepository proveedorRepository;

    @Inject
    ProveedorMapper proveedorMapper;

    public List<ProveedorDTO> listarTodos() {
        return proveedorRepository.findAllActivos()
                .stream()
                .map(proveedorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProveedorDTO buscarPorId(UUID id) {
        return proveedorRepository.findByIdOptional(id)
                .map(proveedorMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"));
    }

    @Transactional
    public ProveedorDTO crear(CrearProveedorDTO dto) {
        var proveedor = proveedorMapper.toEntity(dto);
        proveedorRepository.persist(proveedor);
        return proveedorMapper.toDTO(proveedor);
    }

    @Transactional
    public ProveedorDTO actualizar(UUID id, CrearProveedorDTO dto) {
        var proveedor = proveedorRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"));

        proveedor.nombre = dto.nombre;
        proveedor.telefono = dto.telefono;
        proveedor.infoAdicional = dto.infoAdicional;
        proveedorRepository.persist(proveedor);
        return proveedorMapper.toDTO(proveedor);
    }

    @Transactional
    public void eliminar(UUID id) {
        var proveedor = proveedorRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"));
        proveedor.activo = false;
        proveedorRepository.persist(proveedor);
    }
}
