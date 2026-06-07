package rest;

import dto.RolDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.RolService;

import java.util.List;
import java.util.UUID;

@Path("/roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class RolRest {
    @Inject
    RolService rolService;

    @GET
    public List<RolDTO> listarTodos() {
        return rolService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        return Response.ok(rolService.buscarPorId(id)).build();
    }
}
