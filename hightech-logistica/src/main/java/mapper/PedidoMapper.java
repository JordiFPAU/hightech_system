package mapper;

import dto.PedidoDTO;
import entity.DetallePedido;
import entity.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoMapper {

    @Inject
    ClienteMapper clienteMapper;

    @Inject
    PuntoEntregaMapper puntoEntregaMapper;

    @Inject
    DetallePedidoMapper detallePedidoMapper;

    public PedidoDTO toDTO(Pedido pedido, List<DetallePedido> detalles) {
        if (pedido == null) return null;

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setCliente(clienteMapper.toDTO(pedido.getCliente()));
        dto.setPuntoEntrega(puntoEntregaMapper.toDTO(pedido.getPuntoEntrega()));
        dto.setRepartidorId(pedido.getRepartidorId());
        dto.setNumeroFactura(pedido.getNumeroFactura());
        dto.setEstado(pedido.getEstado());
        dto.setEsUrgente(pedido.getEsUrgente());
        dto.setObservaciones(pedido.getObservaciones());
        dto.setCreatedAt(pedido.getCreatedAt());
        dto.setDetalles(detalles.stream()
                .map(detallePedidoMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}
