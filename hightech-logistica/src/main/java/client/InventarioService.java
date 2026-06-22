package client;

import dto.ProductoStockDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ProcessingException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.ws.rs.ServiceUnavailableException;

import java.util.UUID;

@ApplicationScoped
public class InventarioService {
    @Inject
    @RestClient
    InventarioClient inventarioClient;

    public ProductoStockDTO obtenerProducto(UUID productoId) {
        try {
            return inventarioClient.buscarPorId(productoId);
        } catch (ProcessingException e) {
            throw new ServiceUnavailableException(
                    "El servicio de inventario no está disponible en este momento");
        }
    }
    public void validarStockDisponible(UUID productoId, Integer cantidadSolicitada) {
        ProductoStockDTO producto = obtenerProducto(productoId);

        if (producto.getStockActual() < cantidadSolicitada) {
            throw new BadRequestException(
                    "Stock insuficiente para \"" + producto.getNombre() + "\" — disponible: "
                            + producto.getStockActual() + ", solicitado: " + cantidadSolicitada);
        }
    }
}
