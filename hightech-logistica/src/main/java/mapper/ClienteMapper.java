package mapper;

import dto.ClienteDTO;
import dto.CrearClienteDTO;
import entity.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) return null;

        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setRucCi(cliente.getRucCi());
        dto.setTelefono(cliente.getTelefono());
        dto.setCorreo(cliente.getCorreo());
        dto.setDireccion(cliente.getDireccion());
        dto.setActivo(cliente.getActivo());
        return dto;
    }

    public Cliente toEntity(CrearClienteDTO dto) {
        if (dto == null) return null;

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setRucCi(dto.getRucCi());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        cliente.setDireccion(dto.getDireccion());
        cliente.setActivo(true) ;
        return cliente;
    }
}