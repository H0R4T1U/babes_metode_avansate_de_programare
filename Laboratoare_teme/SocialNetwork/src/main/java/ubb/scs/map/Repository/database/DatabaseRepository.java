package ubb.scs.map.Repository.database;

import ubb.scs.map.Domain.Entity;
import ubb.scs.map.Domain.validators.Validator;
import ubb.scs.map.Repository.Repository;

import java.util.Optional;

public class DatabaseRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {
    private final String url;
    private final String username;
    private final String password;
    protected final Validator<E> validator;

    public DatabaseRepository(String url, String username, String password, Validator<E> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Optional<E> findOne(ID id) {
        return Optional.empty();
    }

    @Override
    public Iterable<E> findAll() {
        return null;
    }

    @Override
    public Optional<E> save(E entity) {
        return Optional.empty();
    }

    @Override
    public Optional<E> delete(ID id) {
        return Optional.empty();
    }

    @Override
    public Optional<E> update(E entity) {
        return Optional.empty();
    }
}
