package service;

import dto.AlertaStockDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.AlertaStockMapper;
import repository.AlertaStockRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlertaStockService {

    @Inject
    AlertaStockRepository alertaStockRepository;

    @Inject
    AlertaStockMapper alertaStockMapper;

    public List<AlertaStockDTO> listarNoLeidas() {
        return alertaStockRepository.findNoLeidas()
                .stream()
                .map(alertaStockMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AlertaStockDTO> listarPorProducto(UUID productoId) {
        return alertaStockRepository.findByProducto(productoId)
                .stream()
                .map(alertaStockMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlertaStockDTO marcarLeida(UUID id) {
        var alerta = alertaStockRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Alerta no encontrada"));
        alerta.leida = true;
        alertaStockRepository.persist(alerta);
        return alertaStockMapper.toDTO(alerta);
    }
}