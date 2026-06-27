package rest;

import dto.CrearProveedorDTO;
import dto.ProveedorDTO;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ProveedorService;

import java.util.List;
import java.util.UUID;

@Path("/proveedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ProveedorRest {
    @Inject
    ProveedorService proveedorService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "GERENTE"})
    public Response buscarPorId(@jakarta.ws.rs.PathParam("id") UUID id) {
        return Response.ok(proveedorService.buscarPorId(id)).build();
    }

    @GET
    @RolesAllowed({"ADMIN", "GERENTE"})
    public List<ProveedorDTO> listarTodas() {
        return proveedorService.listarTodos();
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response crear(@Valid CrearProveedorDTO dto){
        ProveedorDTO creado = proveedorService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response actualizar(@PathParam("id") UUID id, @Valid CrearProveedorDTO dto){
        return Response.ok(proveedorService.actualizar(id, dto)).build();
    }
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response eliminar(@PathParam("id") UUID id){
        proveedorService.eliminar(id);
        return Response.noContent().build();
    }


}
