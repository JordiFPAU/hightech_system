package mapper;

import dto.PuntoRutaDTO;
import dto.RutaDTO;
import entity.Ruta;
import jakarta.enterprise.context.ApplicationScoped;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RutaMapper {

    public RutaDTO toDTO(Ruta ruta) {
        if (ruta == null) return null;

        RutaDTO dto = new RutaDTO();
        dto.setId(ruta.getId());
        dto.setRepartidorId(ruta.getRepartidorId());
        dto.setFecha(ruta.getFecha());
        dto.setEstado(ruta.getEstado());
        dto.setCreatedAt(ruta.getCreatedAt());

        // Convertir LineString a lista de puntos lat/lng
        if (ruta.getTrayectoria() != null) {
            List<PuntoRutaDTO> puntos = new ArrayList<>();
            for (Coordinate coord : ruta.getTrayectoria().getCoordinates()) {
                PuntoRutaDTO punto = new PuntoRutaDTO();
                punto.setLongitud(coord.x);
                punto.setLatitud(coord.y);
                puntos.add(punto);
            }
            dto.setPuntos(puntos);
        }
        return dto;
    }
}