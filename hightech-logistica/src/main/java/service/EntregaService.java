package service;

import dto.CrearEntregaDTO;
import dto.EntregaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.EntregaMapper;
import repository.EntregaRepository;
import repository.PedidoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class EntregaService {

    @Inject
    EntregaRepository entregaRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    EntregaMapper entregaMapper;

    @Transactional
    public EntregaDTO crear(CrearEntregaDTO dto) {
        var pedido = pedidoRepository.findByIdOptional(dto.getPedidoId())
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

        entregaRepository.findByPedidoId(dto.getPedidoId()).ifPresent(e -> {
            throw new BadRequestException("Este pedido ya tiene una entrega registrada");
        });

        var entrega = entregaMapper.toEntity(dto, pedido);
        entregaRepository.persist(entrega);

        // Actualizar el estado del pedido según el resultado de la entrega
        pedido.estado = switch (dto.getEstado()) {
            case "COMPLETA" -> "ENTREGADO";
            case "PARCIAL" -> "PARCIAL";
            case "FALLIDA" -> "FALLIDO";
            default -> pedido.getEstado();
        };
        pedidoRepository.persist(pedido);

        return entregaMapper.toDTO(entrega);
    }

    public EntregaDTO buscarPorPedido(UUID pedidoId) {
        return entregaRepository.findByPedidoId(pedidoId)
                .map(entregaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("No hay entrega registrada para este pedido"));
    }

    public List<EntregaDTO> listarPorRepartidor(UUID repartidorId) {
        return entregaRepository.findByRepartidor(repartidorId)
                .stream()
                .map(entregaMapper::toDTO)
                .collect(Collectors.toList());
    }
}