import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/logistica/health")
public class LogisticaApplication {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "hightech-logistica OK";
    }
}