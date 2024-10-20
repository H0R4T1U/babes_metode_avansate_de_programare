package Repository.file;

import Domain.Friendship;
import Domain.Tuple;
import Domain.validators.Validator;


public class FriendshipRepository extends AbstractFileRepository<Tuple<Long,Long>, Friendship> {
    public FriendshipRepository(Validator<Friendship> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public Friendship createEntity(String line) {
        String[] splited = line.split(";");
        Friendship f = new Friendship();
        f.setId(new Tuple<>(Long.parseLong(splited[0]), Long.parseLong(splited[1])));
        return f;
    }

    @Override
    public String saveEntity(Friendship entity) {
        return entity.getId().getE1() + ";" + entity.getId().getE2();
    }
}
