package rest;

import dto.AlertaStockDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.AlertaStockService;

import java.util.List;
import java.util.UUID;

@Path("/alertas")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AlertaStockRest {
    @Inject
    AlertaStockService alertaStockService;

    @GET
    @Path("/no-leidas")
    public List<AlertaStockDTO> listarNoLeidas(){
        return alertaStockService.listarNoLeidas();
    }
    @GET
    @Path("/producto/{productoId}")
    public List<AlertaStockDTO> listarPorProducto(@PathParam("productoId") UUID productoId) {
        return alertaStockService.listarPorProducto(productoId);
    }

    @PUT
    @Path("/{id}/marcar-leida")
    public Response marcarLeida(@PathParam("id") UUID id) {
        return Response.ok(alertaStockService.marcarLeida(id)).build();
    }
}
