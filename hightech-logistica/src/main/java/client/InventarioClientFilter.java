package client;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import io.quarkus.arc.Arc;

@ApplicationScoped
public class InventarioClientFilter implements ClientHeadersFactory {

    @Override
    public MultivaluedMap<String, String> update(
            MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {

        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        String authHeader = incomingHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null) {
            result.add(HttpHeaders.AUTHORIZATION, authHeader);
        }

        return result;
    }
}