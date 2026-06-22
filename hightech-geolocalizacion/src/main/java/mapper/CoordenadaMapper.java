package mapper;

import dto.CoordenadaDTO;
import dto.EnviarCoordenadaDTO;
import dto.UbicacionActualDTO;
import entity.CoordenadaRepartidor;
import jakarta.enterprise.context.ApplicationScoped;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;

@ApplicationScoped
public class CoordenadaMapper {

    private static final GeometryFactory GEOMETRY_FACTORY =
            new GeometryFactory(new PrecisionModel(), 4326);

    public CoordenadaDTO toDTO(CoordenadaRepartidor coordenada) {
        if (coordenada == null) return null;

        CoordenadaDTO dto = new CoordenadaDTO();
        dto.setId(coordenada.getId());
        dto.setRepartidorId(coordenada.getRepartidorId());
        dto.setPedidoId(coordenada.getPedidoId());
        dto.setTimestamp(coordenada.getTimestamp());

        if (coordenada.getUbicacion() != null) {
            dto.setLongitud(coordenada.getUbicacion().getX());
            dto.setLatitud(coordenada.getUbicacion().getY());
        }
        return dto;
    }

    public CoordenadaRepartidor toEntity(EnviarCoordenadaDTO dto) {
        if (dto == null) return null;

        CoordenadaRepartidor coordenada = new CoordenadaRepartidor();
        coordenada.setRepartidorId(dto.getRepartidorId());
        coordenada.setPedidoId(dto.getPedidoId());
        coordenada.setUbicacion(
                GEOMETRY_FACTORY.createPoint(
                        new Coordinate(dto.getLongitud(), dto.getLatitud())
                )
        );
        return coordenada;
    }

    public UbicacionActualDTO toUbicacionActualDTO(CoordenadaRepartidor coordenada) {
        if (coordenada == null) return null;

        UbicacionActualDTO dto = new UbicacionActualDTO();
        dto.setRepartidorId(coordenada.getRepartidorId());
        dto.setUltimaActualizacion(coordenada.getTimestamp());

        if (coordenada.getUbicacion() != null) {
            dto.setLongitud(coordenada.getUbicacion().getX());
            dto.setLatitud(coordenada.getUbicacion().getY());
        }
        return dto;
    }
}