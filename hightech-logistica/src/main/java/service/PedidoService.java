package service;

import client.InventarioClient;
import client.InventarioService;
import dto.ActualizarEstadoPedidoDTO;
import dto.CrearPedidoDTO;
import dto.ItemPedidoDTO;
import dto.PedidoDTO;
import entity.DetallePedido;
import entity.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.DetallePedidoMapper;
import mapper.PedidoMapper;
import repository.ClienteRepository;
import repository.DetallePedidoRepository;
import repository.PedidoRepository;
import repository.PuntoEntregaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoService {

    private static final List<String> ESTADOS_VALIDOS =
            List.of("PENDIENTE", "EN_RUTA", "ENTREGADO", "PARCIAL", "FALLIDO");

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    PuntoEntregaRepository puntoEntregaRepository;

    @Inject
    DetallePedidoRepository detallePedidoRepository;

    @Inject
    PedidoMapper pedidoMapper;

    @Inject
    DetallePedidoMapper detallePedidoMapper;

    @Inject
    InventarioService inventarioService;


    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.listAll()
                .stream()
                .map(this::toDTOConDetalles)
                .collect(Collectors.toList());
    }

    public List<PedidoDTO> listarPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado)
                .stream()
                .map(this::toDTOConDetalles)
                .collect(Collectors.toList());
    }

    public List<PedidoDTO> listarPorRepartidor(UUID repartidorId) {
        return pedidoRepository.findByRepartidor(repartidorId)
                .stream()
                .map(this::toDTOConDetalles)
                .collect(Collectors.toList());
    }

    public PedidoDTO buscarPorId(UUID id) {
        var pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
        return toDTOConDetalles(pedido);
    }

    @Transactional
    public PedidoDTO crear(CrearPedidoDTO dto) {
        var cliente = clienteRepository.findByIdOptional(dto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        for (ItemPedidoDTO item : dto.getItems()) {
            inventarioService.validarStockDisponible(item.getProductoId(), item.getCantidad());
        }

        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setRepartidorId(dto.getRepartidorId());
        pedido.setNumeroFactura(dto.getNumeroFactura());
        pedido.setEsUrgente(dto.getEsUrgente() != null ? dto.getEsUrgente() : false) ;
        pedido.setObservaciones(dto.getObservaciones());
        pedido.setEstado("PENDIENTE");

        if (dto.getPuntoEntregaId() != null) {
            var punto = puntoEntregaRepository.findByIdOptional(dto.getPuntoEntregaId())
                    .orElseThrow(() -> new NotFoundException("Punto de entrega no encontrado"));
            pedido.setPuntoEntrega(punto);
        }

        pedidoRepository.persist(pedido);

        for (ItemPedidoDTO item : dto.getItems()) {
            DetallePedido detalle = detallePedidoMapper.toEntity(item, pedido);
            detallePedidoRepository.persist(detalle);
        }

        return toDTOConDetalles(pedido);
    }

    @Transactional
    public PedidoDTO actualizarEstado(UUID id, ActualizarEstadoPedidoDTO dto) {
        var pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

        if (!ESTADOS_VALIDOS.contains(dto.getEstado())) {
            throw new BadRequestException("Estado inválido. Use: " + ESTADOS_VALIDOS);
        }

        pedido.setEstado(dto.getEstado());
        if (dto.getRepartidorId() != null) {
            pedido.setRepartidorId(dto.getRepartidorId());
        }

        pedidoRepository.persist(pedido);
        return toDTOConDetalles(pedido);
    }

    private PedidoDTO toDTOConDetalles(Pedido pedido) {
        var detalles = detallePedidoRepository.finByPedido(pedido.getId());
        return pedidoMapper.toDTO(pedido, detalles);
    }
}