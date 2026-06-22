package client;

import dto.ProductoStockDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.UUID;

@RegisterRestClient(configKey = "inventario-api")
@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public interface InventarioClient {
    @GET
    @Path("/{id}")
    ProductoStockDTO buscarPorId (@PathParam("id") UUID id);
}
