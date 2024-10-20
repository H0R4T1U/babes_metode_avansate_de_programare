package Services;

import Domain.Entity;
import Domain.User;
import Repository.Repository;

public class EntityService<ID, E extends Entity<ID>> {
    protected Repository<ID, E> repository;

    public EntityService(Repository<ID, E> repository) {
        this.repository = repository;
    }

    public E create(E entity) {
        return repository.save(entity);
    }

    public E update(E entity) {
        return repository.update(entity);
    }

    public E delete(ID entityId) {
        return repository.delete(entityId);
    }

    public Iterable<E> getAll() {
        return repository.findAll();
    }

    public E getById(ID entityId) {
        return repository.findOne(entityId);
    }
}
