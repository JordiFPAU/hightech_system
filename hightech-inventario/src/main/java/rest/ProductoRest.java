package rest;

import dto.CrearProductoDTO;
import dto.ProductoDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ProductoService;

import java.util.List;
import java.util.UUID;

@Path("/productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRest {

    @Inject
    ProductoService productoService;
    @GET
    public List<ProductoDTO> listarTodos(){
        return productoService.listarTodos();
    }

    @GET
    @Path("/stockCritico")
    public List<ProductoDTO> listarStockCritico(){
        return productoService.listarStockCritico();
    }
    @GET
    @Path("/categoria/{categoriaId}")
    public List<ProductoDTO> listarPorCategoria(@PathParam("categoriaId") UUID categoriaId){
        return productoService.listarPorCategoria(categoriaId);
    }
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id){
        return Response.ok(productoService.buscarPorId(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(@Valid CrearProductoDTO dto) {
        ProductoDTO creado = productoService.crear(dto);
        return  Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") UUID id, @Valid CrearProductoDTO dto){
        return Response.ok(productoService.actualizar(id,dto)).build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") UUID id){
        productoService.eliminar(id);
        return Response.noContent().build();
    }

}
