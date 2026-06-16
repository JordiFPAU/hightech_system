package mapper;

import dto.AlertaStockDTO;
import entity.AlertaStock;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlertaStockMapper {

    public AlertaStockDTO toDTO(AlertaStock alerta) {
        if (alerta == null) return null;

        AlertaStockDTO dto = new AlertaStockDTO();
        dto.id = alerta.id;
        dto.productoId = alerta.producto.id;
        dto.productoNombre = alerta.producto.nombre;
        dto.stockAlMomento = alerta.stockAlMomento;
        dto.stockMinimo = alerta.producto.stockMinimo;
        dto.leida = alerta.leida;
        dto.createdAt = alerta.createdAt;
        return dto;
    }
}
