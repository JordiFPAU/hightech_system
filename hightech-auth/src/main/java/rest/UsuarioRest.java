package rest;
import dto.CrearUsuarioDTO;
import dto.LoginDTO;
import dto.TokenDTO;
import dto.UsuarioDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.UsuarioService;

import java.util.List;
import java.util.UUID;
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UsuarioRest {
    @Inject
    UsuarioService usuarioService;

    @GET
    @RolesAllowed({"ADMIN","GERENTE"})
    public List<UsuarioDTO> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN","GERENTE"})
    public Response buscarPorId(@PathParam("id") UUID id) {
        return Response.ok(usuarioService.buscarPorId(id)).build();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response crear(@Valid CrearUsuarioDTO dto) {
        UsuarioDTO creado = usuarioService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginDTO dto) {
        TokenDTO token = usuarioService.login(dto);
        return Response.ok(token).build();
    }
}
