import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/auth")
@Consumes("application/json")
@Produces("application/json")
public class AuthAapplication {

    @GET
    public String hola(){
        return  "Hola mundo";
    }
}
