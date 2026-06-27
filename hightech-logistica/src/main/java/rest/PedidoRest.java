package rest;

import dto.ActualizarEstadoPedidoDTO;
import dto.CrearPedidoDTO;
import dto.PedidoDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.PedidoService;

import java.util.List;
import java.util.UUID;

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class PedidoRest {
    @Inject
    PedidoService pedidoService;

    @GET
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public List<PedidoDTO> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GET
    @Path("/estado/{estado}")
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public List<PedidoDTO> listarPorEstado(@PathParam("estado") String estado) {
        return pedidoService.listarPorEstado(estado);
    }

    @GET
    @Path("/repartidor/{repartidorId}")
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public List<PedidoDTO> listarPorRepartidor(@PathParam("repartidorId") UUID repartidorId) {
        return pedidoService.listarPorRepartidor(repartidorId);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public Response buscarPorId(@PathParam("id") UUID id) {
        return Response.ok(pedidoService.buscarPorId(id)).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response crear(@Valid CrearPedidoDTO dto) {
        PedidoDTO creado = pedidoService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}/estado")
    @RolesAllowed({"ADMIN", "REPARTIDOR"})
    public Response actualizarEstado(@PathParam("id") UUID id, @Valid ActualizarEstadoPedidoDTO dto) {
        return Response.ok(pedidoService.actualizarEstado(id, dto)).build();
    }
}
