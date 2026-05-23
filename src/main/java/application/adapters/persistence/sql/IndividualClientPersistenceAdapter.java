package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.IndividualClientEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaIndividualClientRepository;
import domain.models.client.IndividualClient;
import domain.ports.client.IndividualClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IndividualClientPersistenceAdapter implements IndividualClientPort {

    @Autowired
    private SpringDataJpaIndividualClientRepository repository;

    @Override
    public IndividualClient save(IndividualClient client) {
        IndividualClientEntity entity = new IndividualClientEntity();
        entity.setId(client.getId());
        entity.setFullName(client.getFullName());
        entity.setIdentification(client.getIdentification());
        entity.setClientStatus(client.getClientStatus());
        entity.setBirthDate(client.getBirthDate());

        IndividualClientEntity savedEntity = repository.save(entity);
        
        client.setId(savedEntity.getId());
        return client;
    }

    @Override
    public Optional<IndividualClient> findById(int id) {
        return repository.findById(id).map(entity -> {
            IndividualClient client = new IndividualClient();
            client.setId(entity.getId());
            client.setFullName(entity.getFullName());
            client.setIdentification(entity.getIdentification());
            client.setClientStatus(entity.getClientStatus());
            client.setBirthDate(entity.getBirthDate());
            return client;
        });
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<IndividualClient> findAll() {
        return repository.findAll().stream().map(entity -> {
            IndividualClient client = new IndividualClient();
            client.setId(entity.getId());
            client.setFullName(entity.getFullName());
            client.setIdentification(entity.getIdentification());
            client.setClientStatus(entity.getClientStatus());
            client.setBirthDate(entity.getBirthDate());
            return client;
        }).collect(Collectors.toList());
    }
}

