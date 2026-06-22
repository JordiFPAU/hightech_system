package repository;

import entity.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<Cliente, UUID> {

    public List<Cliente> findAllActivos() {
        return list("activo", true);
    }

    public Optional <Cliente> findByCi(String rucCI){
        return find("ci", rucCI).firstResultOptional();
    }

    public boolean existsByRucCi(String rucCi){
        return count("rucCi", rucCi) > 0 ;

    }


}
