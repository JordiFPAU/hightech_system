package service;

import dto.ClienteDTO;
import dto.CrearClienteDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.ClienteMapper;
import repository.ClienteRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    ClienteMapper clienteMapper;

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAllActivos()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(UUID id) {
        return clienteRepository.findByIdOptional(id)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    @Transactional
    public ClienteDTO crear(CrearClienteDTO dto) {
        if (dto.getRucCi() != null && clienteRepository.existsByRucCi(dto.getRucCi())) {
            throw new BadRequestException("Ya existe un cliente con ese RUC/CI");
        }

        var cliente = clienteMapper.toEntity(dto);
        clienteRepository.persist(cliente);
        return clienteMapper.toDTO(cliente);
    }

    @Transactional
    public ClienteDTO actualizar(UUID id, CrearClienteDTO dto) {
        var cliente = clienteRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setRucCi(dto.getRucCi());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        cliente.setDireccion(dto.getDireccion());

        clienteRepository.persist(cliente);
        return clienteMapper.toDTO(cliente);
    }

    @Transactional
    public void eliminar(UUID id) {
        var cliente = clienteRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        cliente.setActivo(false);
        clienteRepository.persist(cliente);
    }
}
