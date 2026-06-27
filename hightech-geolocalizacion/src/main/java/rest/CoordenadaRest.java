package rest;

import dto.CoordenadaDTO;
import dto.EnviarCoordenadaDTO;
import dto.UbicacionActualDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CoordenadaService;

import java.util.List;
import java.util.UUID;

@Path("/coordenadas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class CoordenadaRest {

    @Inject
    CoordenadaService coordenadaService;

    @POST
    @RolesAllowed({"ADMIN", "REPARTIDOR"})
    public Response registrar(@Valid EnviarCoordenadaDTO dto) {
        CoordenadaDTO creada = coordenadaService.registrar(dto);
        return Response.status(Response.Status.CREATED).entity(creada).build();
    }

    @GET
    @Path("/ubicacion-actual/{repartidorId}")
    @RolesAllowed({"ADMIN", "GERENTE"})
    public Response ubicacionActual(@PathParam("repartidorId") UUID repartidorId) {
        UbicacionActualDTO ubicacion = coordenadaService.obtenerUltimaUbicacion(repartidorId);
        return Response.ok(ubicacion).build();
    }

    @GET
    @Path("/historial/{repartidorId}")
    @RolesAllowed({"ADMIN", "GERENTE"})
    public List<CoordenadaDTO> historial(@PathParam("repartidorId") UUID repartidorId) {
        return coordenadaService.obtenerHistorial(repartidorId);
    }

    @GET
    @Path("/pedido/{repartidorId}/{pedidoId}")
    @RolesAllowed({"ADMIN", "GERENTE"})
    public List<CoordenadaDTO> porPedido(
            @PathParam("repartidorId") UUID repartidorId,
            @PathParam("pedidoId") UUID pedidoId) {
        return coordenadaService.obtenerPorPedido(repartidorId, pedidoId);
    }
}