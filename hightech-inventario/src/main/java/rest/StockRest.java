package rest;

import dto.AjustarStockDTO;
import dto.StockMovimientoDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.StockService;

import java.util.List;
import java.util.UUID;

@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockRest {

    @Inject
    StockService stockService;

    @POST
    @Path("/ajustar")
    public Response ajustar(@Valid AjustarStockDTO dto) {
        StockMovimientoDTO movimiento = stockService.ajustarStock(dto);
        return Response.status(Response.Status.CREATED).entity(movimiento).build();
    }

    @GET
    @Path("/historial/{productoId}")
    public List<StockMovimientoDTO> historial(@PathParam("productoId") UUID productoId) {
        return stockService.historialPorProducto(productoId);
    }
}
