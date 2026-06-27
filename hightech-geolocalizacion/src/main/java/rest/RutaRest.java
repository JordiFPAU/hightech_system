package rest;
import dto.IniciarRutaDTO;
import dto.RutaDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import service.RutaService;

@Path("/rutas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class RutaRest {
    @Inject
    RutaService rutaService;

    @POST
    @Path("/iniciar")
    @RolesAllowed({"ADMIN", "REPARTIDOR"})
    public Response iniciar(@Valid IniciarRutaDTO dto) {
        RutaDTO ruta = rutaService.iniciar(dto);
        return Response.status(Response.Status.CREATED).entity(ruta).build();
    }

    @PUT
    @Path("/{id}/completar")
    @RolesAllowed({"ADMIN", "REPARTIDOR"})
    public Response completar(@PathParam("id") UUID id) {
        return Response.ok(rutaService.completar(id)).build();
    }

    @GET
    @Path("/activa/{repartidorId}")
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public Response rutaActivaHoy(@PathParam("repartidorId") UUID repartidorId) {
        return Response.ok(rutaService.buscarRutaActivaHoy(repartidorId)).build();
    }

    @GET
    @Path("/repartidor/{repartidorId}")
    @RolesAllowed({"ADMIN", "GERENTE"})
    public List<RutaDTO> listarPorRepartidor(@PathParam("repartidorId") UUID repartidorId) {
        return rutaService.listarPorRepartidor(repartidorId);
    }

    @GET
    @Path("/activas")
    @RolesAllowed({"ADMIN", "GERENTE"})
    public List<RutaDTO> listarActivas() {
        return rutaService.listarRutasActivas();
    }
}
