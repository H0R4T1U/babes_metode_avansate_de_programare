package Services;

import Domain.User;
import Repository.Repository;

import java.util.Optional;


public class UserService extends EntityService<Long, User> {
    @Override
    public Optional<User> create(User entity) {
        return super.create(entity);
    }

    public UserService(Repository<Long, User> repository) {
        super(repository);
    }


}
