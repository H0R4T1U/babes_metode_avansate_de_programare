package Services;

import Domain.Entity;
import Repository.Repository;

import java.util.Optional;

public class EntityService<ID, E extends Entity<ID>> {
    protected Repository<ID, E> repository;

    public EntityService(Repository<ID, E> repository) {
        this.repository = repository;
    }

    public Optional<E> create(E entity) {
        return repository.save(entity);
    }

    public Optional<E> update(E entity) {
        return repository.update(entity);
    }

    public Optional<E> delete(ID entityId) {
        return repository.delete(entityId);
    }

    public Iterable<E> getAll() {
        return repository.findAll();
    }

    public Optional<E> getById(ID entityId) {
        return repository.findOne(entityId);
    }
}
