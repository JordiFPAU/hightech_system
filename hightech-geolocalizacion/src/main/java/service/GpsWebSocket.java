package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.EnviarCoordenadaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.ws.rs.PathParam;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/gps/{repartidorId}")
@ApplicationScoped
public class GpsWebSocket {

    private static final Map<String, Session> sesiones = new ConcurrentHashMap<>();

    @Inject
    CoordenadaService coordenadaService;

    @Inject
    ObjectMapper objectMapper;

    @OnOpen
    public void onOpen(Session session, @PathParam("repartidorId") String repartidorId) {
        sesiones.put(repartidorId, session);
        System.out.println("Repartidor conectado: " + repartidorId);
    }

    @OnClose
    public void onClose(Session session, @PathParam("repartidorId") String repartidorId) {
        sesiones.remove(repartidorId);
        System.out.println("Repartidor desconectado: " + repartidorId);
    }

    @OnError
    public void onError(Session session, @PathParam("repartidorId") String repartidorId,
                        Throwable error) {
        sesiones.remove(repartidorId);
        System.err.println("Error WebSocket repartidor " + repartidorId + ": " + error.getMessage());
    }

    @OnMessage
    public void onMessage(String mensaje, @PathParam("repartidorId") String repartidorId, Session session) {
        try {
            var payload = objectMapper.readTree(mensaje);

            EnviarCoordenadaDTO dto = new EnviarCoordenadaDTO();
            dto.setRepartidorId(UUID.fromString(repartidorId));
            dto.setLatitud(payload.get("latitud").asDouble());
            dto.setLongitud(payload.get("longitud").asDouble());

            if (payload.has("pedidoId") && !payload.get("pedidoId").isNull()) {
                dto.setPedidoId(UUID.fromString(payload.get("pedidoId").asText()));
            }

            var coordenada = coordenadaService.registrar(dto);

            String respuesta = objectMapper.writeValueAsString(Map.of(
                    "repartidorId", repartidorId,
                    "latitud", coordenada.getLatitud(),
                    "longitud", coordenada.getLongitud(),
                    "timestamp", coordenada.getTimestamp().toString()
            ));

            // Enviar confirmación al repartidor
            session.getAsyncRemote().sendText(respuesta);

            // Broadcast a sesión de monitoreo si existe
            Session adminSession = sesiones.get("admin-monitor");
            if (adminSession != null && adminSession.isOpen()) {
                adminSession.getAsyncRemote().sendText(respuesta);
            }

        } catch (Exception e) {
            System.err.println("Error procesando mensaje GPS: " + e.getMessage());
        }
    }

    // Método para enviar ubicación a una sesión específica desde fuera del WebSocket
    public static void broadcastUbicacion(String mensaje) {
        sesiones.values().forEach(session -> {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(mensaje);
            }
        });
    }
}
