package service;

import dto.CategoriaDTO;
import dto.CrearCategoriaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.CategoriaMapper;
import repository.CategoriaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaService {
    @Inject
    CategoriaRepository categoriaRepository ;
    @Inject
    CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> listarTodas(){
        return categoriaRepository.findAllActivas()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(UUID id) {
        return categoriaRepository.findByIdOptional(id)
                .map(categoriaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @Transactional
    public CategoriaDTO crear(CrearCategoriaDTO dto) {
        categoriaRepository.findByNombre(dto.nombre)
                .ifPresent(c -> { throw new RuntimeException("Ya existe una categoría con ese nombre"); });
        var categoria = categoriaMapper.toEntity(dto);
        categoriaRepository.persist(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    @Transactional
    public CategoriaDTO actualizar(UUID id, CrearCategoriaDTO dto) {
        var categoria = categoriaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        categoria.nombre = dto.nombre;
        categoria.descripcion = dto.descripcion;
        categoriaRepository.persist(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    @Transactional
    public void eliminar(UUID id) {
        var categoria = categoriaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        categoria.activo = false;
        categoriaRepository.persist(categoria);
    }

}
