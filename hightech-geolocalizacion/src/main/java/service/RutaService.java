package service;

import dto.IniciarRutaDTO;
import dto.RutaDTO;
import entity.Ruta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.RutaMapper;
import repo.CoordenadaRepartidorRepository;
import repo.RutaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RutaService {

    @Inject
    RutaRepository rutaRepository;
    @Inject
    CoordenadaRepartidorRepository coordenadaRepository;

    @Inject
    RutaMapper rutaMapper;
    @Transactional
    public RutaDTO iniciar(IniciarRutaDTO dto){
        rutaRepository.findRutaActivaHoy(dto.getRepartidorId()).ifPresent(ruta -> {
            throw new RuntimeException("El repartidor ya tiene una ruta activa hoy");
        });

        Ruta ruta = new Ruta();
        ruta.setRepartidorId(dto.getRepartidorId());
        ruta.setEstado("ACTIVA");

        rutaRepository.persist(ruta);
        return rutaMapper.toDTO(ruta);
    }
    public RutaDTO buscarRutaActivaHoy(UUID repartidorId) {
        return rutaRepository.findRutaActivaHoy(repartidorId)
                .map(rutaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(
                        "No hay ruta activa para hoy"));
    }

    public List<RutaDTO> listarPorRepartidor(UUID repartidorId) {
        return rutaRepository.findByRepartidor(repartidorId)
                .stream()
                .map(rutaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RutaDTO> listarRutasActivas() {
        return rutaRepository.findRutasActivas()
                .stream()
                .map(rutaMapper::toDTO)
                .collect(Collectors.toList());
    }



}
