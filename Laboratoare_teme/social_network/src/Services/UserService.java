package Services;

import Domain.User;
import Repository.Repository;


public class UserService extends EntityService<Long, User> {

    public UserService(Repository<Long, User> repository) {
        super(repository);
    }


}
