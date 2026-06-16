package mapper;

import dto.CategoriaDTO;
import dto.CrearCategoriaDTO;
import entity.Categoria;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria){
        if(categoria == null) return null;

        CategoriaDTO dto = new CategoriaDTO();
        dto.id = categoria.id;
        dto.nombre = categoria.nombre;
        dto.descripcion = categoria.descripcion;
        dto.activo = categoria.activo;
        return dto ;
    }

    public Categoria toEntity(CrearCategoriaDTO dto) {
        if(dto == null) return null;

        Categoria categoria = new Categoria();
        categoria.nombre = dto.nombre;
        categoria.descripcion = dto.descripcion;
        categoria.activo = true;
        return categoria;

    }
}
