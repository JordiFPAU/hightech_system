package mapper;


import dto.DetallePedidoDTO;
import dto.ItemPedidoDTO;
import entity.DetallePedido;
import entity.Pedido;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DetallePedidoMapper {

    public DetallePedidoDTO toDTO(DetallePedido detalle) {
        if (detalle == null) return null;

        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detalle.getId());
        dto.setProductoId(detalle.getProductoId());
        dto.setCantidadSolicitada(detalle.getCantidadSolicitada());
        dto.setCantidadEntregada(detalle.getCantidadEntregada());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        return dto;
    }

    public DetallePedido toEntity(ItemPedidoDTO item, Pedido pedido) {
        if (item == null) return null;

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProductoId(item.getProductoId());
        detalle.setCantidadSolicitada(item.getCantidad());
        detalle.setCantidadEntregada(0);
        detalle.setPrecioUnitario(item.getPrecioUnitario ()!= null
                ? item.getPrecioUnitario()
                : java.math.BigDecimal.ZERO);
        return detalle;
    }
}