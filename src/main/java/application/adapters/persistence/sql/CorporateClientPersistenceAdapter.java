package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.CorporateClientEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaCorporateClientRepository;
import domain.models.client.CorporateClient;
import domain.ports.client.CorporateClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CorporateClientPersistenceAdapter implements CorporateClientPort {

    @Autowired
    private SpringDataJpaCorporateClientRepository repository;

    @Override
    public CorporateClient save(CorporateClient client) {
        CorporateClientEntity entity = toEntity(client);
        CorporateClientEntity savedEntity = repository.save(entity);
        client.setId(savedEntity.getId());
        return client;
    }

    @Override
    public Optional<CorporateClient> findById(int id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<CorporateClient> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private CorporateClientEntity toEntity(CorporateClient client) {
        CorporateClientEntity entity = new CorporateClientEntity();
        entity.setId(client.getId());
        entity.setFullName(client.getFullName());
        entity.setIdentification(client.getIdentification());
        entity.setClientStatus(client.getClientStatus());
        entity.setCompanyName(client.getCompanyName());
        entity.setTaxId(client.getTaxId());
        entity.setLegalRepresentativeId(client.getLegalRepresentativeId());
        return entity;
    }

    private CorporateClient toDomain(CorporateClientEntity entity) {
        CorporateClient client = new CorporateClient();
        client.setId(entity.getId());
        client.setFullName(entity.getFullName());
        client.setIdentification(entity.getIdentification());
        client.setClientStatus(entity.getClientStatus());
        client.setCompanyName(entity.getCompanyName());
        client.setTaxId(entity.getTaxId());
        client.setLegalRepresentativeId(entity.getLegalRepresentativeId());
        return client;
    }
}
