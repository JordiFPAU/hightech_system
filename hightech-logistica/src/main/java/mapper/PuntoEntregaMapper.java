package mapper;

import dto.CrearPuntoEntregaDTO;
import dto.PuntoEntregaDTO;
import entity.Cliente;
import entity.PuntoEntrega;
import jakarta.enterprise.context.ApplicationScoped;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;

@ApplicationScoped
public class PuntoEntregaMapper {

    // SRID 4326 = WGS84, el sistema usado por GPS y Google Maps
    private static final GeometryFactory GEOMETRY_FACTORY =
            new GeometryFactory(new PrecisionModel(), 4326);

    public PuntoEntregaDTO toDTO(PuntoEntrega punto) {
        if (punto == null) return null;

        PuntoEntregaDTO dto = new PuntoEntregaDTO();
        dto.setId(punto.getId());
        dto.setClienteId(punto.getCliente().getId());
        dto.setDescripcion(punto.getDescripcion());
        dto.setDireccionTexto(punto.getDireccion_texto());
        dto.setEsPrincipal(punto.getEsPrincipal());
        dto.setActivo(punto.getActivo());

        // Point.getX() = longitud | Point.getY() = latitud (convención JTS)
        if (punto.getCoordenadas() != null) {
            dto.setLongitud(punto.getCoordenadas().getX());
            dto.setLatitud(punto.getCoordenadas().getY());
        }
        return dto;
    }

    public PuntoEntrega toEntity(CrearPuntoEntregaDTO dto, Cliente cliente) {
        if (dto == null) return null;

        PuntoEntrega punto = new PuntoEntrega();
        punto.setCliente(cliente);
        punto.setDescripcion(dto.getDescripcion());
        punto.setDireccion_texto(dto.getDireccionTexto());
        punto.setEsPrincipal(dto.getEsPrincipal() != null ? dto.getEsPrincipal() : false) ;
        punto.setActivo(true);

        // Coordinate(x=longitud, y=latitud) — orden importante en JTS
        punto.setCoordenadas( GEOMETRY_FACTORY.createPoint(
                new Coordinate(dto.getLongitud(), dto.getLatitud())
        ));
        return punto;
    }
}