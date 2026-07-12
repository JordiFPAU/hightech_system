package service;

import dto.CoordenadaDTO;
import dto.EnviarCoordenadaDTO;
import dto.UbicacionActualDTO;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.CoordenadaMapper;
import repo.CoordenadaRepartidorRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CoordenadaService {

    @Inject
    CoordenadaRepartidorRepository coordenadaRepository;

    @Inject
    CoordenadaMapper coordenadaMapper;
    @Transactional
    public CoordenadaDTO registrar(EnviarCoordenadaDTO dto){

        var coordenada = coordenadaMapper.toEntity(dto);
        coordenadaRepository.persist(coordenada);
        return coordenadaMapper.toDTO(coordenada);
    }

    public UbicacionActualDTO obtenerUltimaUbicacion(UUID repartidorId) {
        return coordenadaRepository.findUltimaUbicacion(repartidorId)
                .map(coordenadaMapper::toUbicacionActualDTO)
                .orElseThrow(() -> new NotFoundException(
                        "No hay ubicación registrada para este repartidor"));
    }

    public List<CoordenadaDTO> obtenerHistorial(UUID repartidorId) {
        return coordenadaRepository.findByRepartidor(repartidorId)
                .stream()
                .map(coordenadaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CoordenadaDTO> obtenerPorPedido(UUID repartidorId, UUID pedidoId) {
        return coordenadaRepository.findByRepartidorYPedido(repartidorId, pedidoId)
                .stream()
                .map(coordenadaMapper::toDTO)
                .collect(Collectors.toList());
    }

}
