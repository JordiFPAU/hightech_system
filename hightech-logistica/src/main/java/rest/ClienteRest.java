package rest;

import dto.ClienteDTO;
import dto.CrearClienteDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ClienteService;

import java.util.List;
import java.util.UUID;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ClienteRest {

    @Inject
    ClienteService clienteService;

    @GET
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public List<ClienteDTO> listarTodos() {
        return clienteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public Response buscarPorId(@PathParam("id") UUID id) {
        return Response.ok(clienteService.buscarPorId(id)).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response crear(@Valid CrearClienteDTO dto) {
        ClienteDTO creado = clienteService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response actualizar(@PathParam("id") UUID id, @Valid CrearClienteDTO dto) {
        return Response.ok(clienteService.actualizar(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response eliminar(@PathParam("id") UUID id) {
        clienteService.eliminar(id);
        return Response.noContent().build();
    }
}
