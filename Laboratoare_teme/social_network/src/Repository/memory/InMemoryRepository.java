package Repository.memory;

import Domain.Entity;
import Domain.validators.ValidationException;
import Domain.validators.Validator;
import Repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {
    protected final Map<ID, E> entities;
    private final Validator<E> validator;

    public InMemoryRepository(Validator<E> validator) {
        this.entities = new HashMap<>();
        this.validator = validator;
    }

    @Override
    public E findOne(ID id) throws IllegalArgumentException{
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) throws ValidationException {

        if(entity == null) {
            throw new IllegalArgumentException("ENTITY CANNOT BE NULL");
        }
        validator.validate(entity);
        if(entities.containsKey(entity.getId())) {
            return entity;
        }
        entities.put(entity.getId(), entity);
        return null;
    }

    @Override
    public E delete(ID id) throws IllegalArgumentException {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return entities.remove(id);
    }

    @Override
    public E update(E entity) throws ValidationException {
        if(entity == null) {
            throw new IllegalArgumentException("ENTITY CANNOT BE NULL");
        }
        validator.validate(entity);
        if(entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
        return entity;
    }
}
