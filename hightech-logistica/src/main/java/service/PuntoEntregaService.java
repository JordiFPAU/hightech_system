package service;

import dto.CrearPuntoEntregaDTO;
import dto.PuntoEntregaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.PuntoEntregaMapper;
import repository.ClienteRepository;
import repository.PuntoEntregaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

    @ApplicationScoped
    public class PuntoEntregaService {

        @Inject
        PuntoEntregaRepository puntoEntregaRepository;

        @Inject
        ClienteRepository clienteRepository;

        @Inject
        PuntoEntregaMapper puntoEntregaMapper;

        public List<PuntoEntregaDTO> listarPorCliente(UUID clienteId) {
            return puntoEntregaRepository.findByClient(clienteId)
                    .stream()
                    .map(puntoEntregaMapper::toDTO)
                    .collect(Collectors.toList());
        }

        public PuntoEntregaDTO buscarPorId(UUID id) {
            return puntoEntregaRepository.findByIdOptional(id)
                    .map(puntoEntregaMapper::toDTO)
                    .orElseThrow(() -> new NotFoundException("Punto de entrega no encontrado"));
        }

        @Transactional
        public PuntoEntregaDTO crear(CrearPuntoEntregaDTO dto) {
            var cliente = clienteRepository.findByIdOptional(dto.getClienteId())
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

            var punto = puntoEntregaMapper.toEntity(dto, cliente);
            puntoEntregaRepository.persist(punto);
            return puntoEntregaMapper.toDTO(punto);
        }

        @Transactional
        public void eliminar(UUID id) {
            var punto = puntoEntregaRepository.findByIdOptional(id)
                    .orElseThrow(() -> new NotFoundException("Punto de entrega no encontrado"));
            punto.activo = false;
            puntoEntregaRepository.persist(punto);
        }
    }
