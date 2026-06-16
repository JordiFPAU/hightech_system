package rest;

import dto.CrearProveedorDTO;
import dto.ProveedorDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ProveedorService;

import java.util.List;
import java.util.UUID;

@Path("/provedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorRest {
    @Inject
    ProveedorService proveedorService;

    @GET
    @Path("/{id}")
    public Response buscarPorId(@jakarta.ws.rs.PathParam("id") UUID id) {
        return Response.ok(proveedorService.buscarPorId(id)).build();
    }

    @GET
    public List<ProveedorDTO> listarTodas() {
        return proveedorService.listarTodos();
    }

    @POST
    public Response crear(@Valid CrearProveedorDTO dto){
        ProveedorDTO creado = proveedorService.crear(dto);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") UUID id, @Valid CrearProveedorDTO dto){
        return Response.ok(proveedorService.actualizar(id, dto)).build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") UUID id){
        proveedorService.eliminar(id);
        return Response.noContent().build();
    }


}
