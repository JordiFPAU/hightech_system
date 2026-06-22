package rest;

import dto.CrearPuntoEntregaDTO;
import dto.PuntoEntregaDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.PuntoEntregaService;

import java.util.List;
import java.util.UUID;

@Path("/puntos-entrega")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PuntoEntregaRest {

    @Inject
    PuntoEntregaService puntoEntregaService;

    @GET
    @Path("/cliente/{clienteId}")
    public List<PuntoEntregaDTO> listarPorCliente(@PathParam("clienteId")UUID clienteId) {
        return puntoEntregaService.listarPorCliente(clienteId);
    }
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        return Response.ok(puntoEntregaService.buscarPorId(id)).build();
    }

    @POST
    public Response crear(@Valid CrearPuntoEntregaDTO dto) {
        PuntoEntregaDTO creado = puntoEntregaService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") UUID id) {
        puntoEntregaService.eliminar(id);
        return Response.noContent().build();
    }

}
