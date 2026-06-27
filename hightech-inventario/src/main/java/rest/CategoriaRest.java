package rest;

import dto.CategoriaDTO;
import dto.CrearCategoriaDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CategoriaService;

import java.util.List;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
@Path("/categorias")
public class CategoriaRest {
    @Inject
    CategoriaService categoriaService;

    @GET
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public List<CategoriaDTO> listarTodas(){
        return categoriaService.listarTodas();
    }
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "GERENTE", "REPARTIDOR"})
    public Response buscarPorId(@PathParam("id")UUID id){
        return Response.ok(categoriaService.buscarPorId(id)).build();
    }
    @POST
    @RolesAllowed({"ADMIN"})
    public Response crear(@Valid CrearCategoriaDTO dto){
        CategoriaDTO creada = categoriaService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creada).build();
    }
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response actualizar(@PathParam("id") UUID id, @Valid CrearCategoriaDTO dto) {
        return Response.ok(categoriaService.actualizar(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response eliminar(@PathParam("id") UUID id) {
        categoriaService.eliminar(id);
        return Response.noContent().build();
    }
}
