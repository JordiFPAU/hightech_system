package service;

import dto.IniciarRutaDTO;
import dto.RutaDTO;
import entity.Ruta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.RutaMapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
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

    private static final GeometryFactory GEOMETRY_FACTORY =
            new GeometryFactory(new PrecisionModel(), 4326);

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

    @Transactional
    public RutaDTO completar(UUID rutaId) {
        var ruta = rutaRepository.findByIdOptional(rutaId)
                .orElseThrow(() -> new NotFoundException("Ruta no encontrada"));

        if (ruta.getEstado().equals("COMPLETADA")) {
            throw new BadRequestException("La ruta ya está completada");
        }

        // Construir el LineString a partir de todas las coordenadas del repartidor hoy
        var coordenadas = coordenadaRepository.findByRepartidor(ruta.getRepartidorId());

        if (coordenadas.size() >= 2) {
            Coordinate[] coords = coordenadas.stream()
                    .map(c -> new Coordinate(
                            c.getUbicacion().getX(),
                            c.getUbicacion().getY()
                    ))
                    .toArray(Coordinate[]::new);

            ruta.setTrayectoria(GEOMETRY_FACTORY.createLineString(coords));
        }

        ruta.setEstado("COMPLETADA");
        rutaRepository.persist(ruta);
        return rutaMapper.toDTO(ruta);
    }



}
