package Repository.file;

import Domain.Entity;
import Domain.validators.Validator;
import Repository.memory.InMemoryRepository;

import java.io.*;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private final String filename;

    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        filename=fileName;
        loadData();
    }


    public abstract E createEntity(String line);
    public abstract String saveEntity(E entity);

    @Override
    public E save(E entity) {
        E e = super.save(entity);
        if (e == null)
            writeToFile();
        return e;
    }

    private void writeToFile() {

        try  ( BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (E entity: entities.values()) {
                String ent = saveEntity(entity);
                writer.write(ent);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadData(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                E entity = createEntity(line);
                super.save(entity);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public E delete(ID id) {
        E entity = super.delete(id);
        if (entity != null) writeToFile();
        return entity;
    }

    @Override
    public E update(E entity) {
        E entity2 = super.update(entity);
        if (entity2 == null) writeToFile();
        return entity2;
    }
}
