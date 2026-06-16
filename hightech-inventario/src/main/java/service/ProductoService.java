package service;

import dto.CrearProductoDTO;
import dto.ProductoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import mapper.ProductoMapper;
import repository.CategoriaRepository;
import repository.ProductoRepository;
import repository.ProveedorRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductoService {

    @Inject
    ProductoRepository productoRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    ProveedorRepository proveedorRepository;

    @Inject
    ProductoMapper productoMapper;

    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAllActivos()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductoDTO> listarStockCritico() {
        return productoRepository.findStockCritico()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductoDTO> listarPorCategoria(UUID categoriaId) {
        return productoRepository.findByCategoria(categoriaId)
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO buscarPorId(UUID id) {
        return productoRepository.findByIdOptional(id)
                .map(productoMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    }

    @Transactional
    public ProductoDTO crear(CrearProductoDTO dto) {
        if (productoRepository.existsByNombre(dto.nombre)) {
            throw new BadRequestException("Ya existe un producto con ese nombre");
        }

        var categoria = categoriaRepository.findByIdOptional(dto.categoriaId)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        var proveedor = dto.proveedorId != null
                ? proveedorRepository.findByIdOptional(dto.proveedorId)
                  .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"))
                : null;

        var producto = productoMapper.toEntity(dto, categoria, proveedor);
        productoRepository.persist(producto);
        return productoMapper.toDTO(producto);
    }

    @Transactional
    public ProductoDTO actualizar(UUID id, CrearProductoDTO dto) {
        var producto = productoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        var categoria = categoriaRepository.findByIdOptional(dto.categoriaId)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        var proveedor = dto.proveedorId != null
                ? proveedorRepository.findByIdOptional(dto.proveedorId)
                  .orElseThrow(() -> new NotFoundException("Proveedor no encontrado"))
                : null;

        producto.nombre = dto.nombre;
        producto.descripcion = dto.descripcion;
        producto.codigoMarca = dto.codigoMarca;
        producto.precioVenta = dto.precioVenta;
        producto.precioCosto = dto.precioCosto;
        producto.stockMinimo = dto.stockMinimo;
        producto.fechaCaducidad = dto.fechaCaducidad;
        producto.categoria = categoria;
        producto.proveedor = proveedor;

        productoRepository.persist(producto);
        return productoMapper.toDTO(producto);
    }

    @Transactional
    public void eliminar(UUID id) {
        var producto = productoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        producto.activo = false;
        productoRepository.persist(producto);
    }
}