import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/inventario")
public class InventarioApplication {
@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String healt(){
        return "hightech-inventario OK";
}
}
