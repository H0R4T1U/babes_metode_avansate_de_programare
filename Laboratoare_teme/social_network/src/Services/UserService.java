package Services;

import Domain.User;
import Repository.Repository;

import java.util.random.RandomGenerator;

import static java.lang.Math.abs;


public class UserService extends EntityService<Long, User> {
    @Override
    public User create(User entity) {
        return super.create(entity);
    }

    public UserService(Repository<Long, User> repository) {
        super(repository);
    }


}
