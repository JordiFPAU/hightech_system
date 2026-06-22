package mapper;
import dto.CrearEntregaDTO;
import dto.EntregaDTO;
import entity.Entrega;
import entity.Pedido;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntregaMapper {

    public EntregaDTO toDTO(Entrega entrega) {
        if (entrega == null) return null;

        EntregaDTO dto = new EntregaDTO();
        dto.setId(entrega.getId());
        dto.setPedidoId(entrega.getPedido().getId());
        dto.setRepartidorId(entrega.getRepartidorId());
        dto.setFotoUrl(entrega.getFotoUrl());
        dto.setMensaje(entrega.getMensaje());
        dto.setMetodoPago(entrega.getMetodoPago());
        dto.setObservaciones(entrega.getObservaciones());
        dto.setEstado(entrega.getEstado());
        dto.setTimestampEntrega(entrega.getTimestampEntrega());
        return dto;
    }

    public Entrega toEntity(CrearEntregaDTO dto, Pedido pedido) {
        if (dto == null) return null;

        Entrega entrega = new Entrega();
        entrega.setPedido(pedido);
        entrega.setRepartidorId(dto.getRepartidorId() );
        entrega.setFotoUrl(dto.getFotoUrl());
        entrega.setMensaje(dto.getMensaje());
        entrega.setMetodoPago(dto.getMetodoPago());
        entrega.setObservaciones(dto.getObservaciones());
        entrega.setEstado(dto.getEstado() != null ? dto.getEstado() : "COMPLETA");
        return entrega;
    }
}