package rest;

import dto.CrearEntregaDTO;
import dto.EntregaDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.EntregaService;

import java.util.List;
import java.util.UUID;

@Path("/entregas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntregaRest {
    @Inject
    EntregaService entregaService;

    @POST
    public Response crear(@Valid CrearEntregaDTO dto) {
        EntregaDTO creada = entregaService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creada).build();
    }

    @GET
    @Path("/pedido/{pedidoId}")
    public Response buscarPorPedido(@PathParam("pedidoId") UUID pedidoId) {
        return Response.ok(entregaService.buscarPorPedido(pedidoId)).build();
    }

    @GET
    @Path("/repartidor/{repartidorId}")
    public List<EntregaDTO> listarPorRepartidor(@PathParam("repartidorId") UUID repartidorId) {
        return entregaService.listarPorRepartidor(repartidorId);
    }
}
