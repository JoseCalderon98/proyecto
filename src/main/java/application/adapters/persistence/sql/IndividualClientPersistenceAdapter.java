package application.adapters.persistence.sql;

import application.adapters.persistence.sql.entities.IndividualClientEntity;
import application.adapters.persistence.sql.repositories.SpringDataJpaIndividualClientRepository;
import domain.models.client.IndividualClient;
import domain.ports.client.IndividualClientPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IndividualClientPersistenceAdapter implements IndividualClientPort {

    private final SpringDataJpaIndividualClientRepository repository;

    public IndividualClientPersistenceAdapter(SpringDataJpaIndividualClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public IndividualClient save(IndividualClient client) {
        IndividualClientEntity entity = new IndividualClientEntity(
            client.getId(),
            client.getFullName(),
            client.getIdentification(),
            client.getClientStatus(),
            client.getBirthDate()
        );
        IndividualClientEntity savedEntity = repository.save(entity);
        
        client.setId(savedEntity.getId());
        return client;
    }

    @Override
    public Optional<IndividualClient> findById(int id) {
        return repository.findById(id).map(entity -> {
            IndividualClient client = new IndividualClient(
                entity.getFullName(),
                entity.getIdentification(),
                entity.getClientStatus(),
                entity.getBirthDate()
            );
            client.setId(entity.getId());
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
            IndividualClient client = new IndividualClient(
                entity.getFullName(),
                entity.getIdentification(),
                entity.getClientStatus(),
                entity.getBirthDate()
            );
            client.setId(entity.getId());
            return client;
        }).collect(Collectors.toList());
    }
}
