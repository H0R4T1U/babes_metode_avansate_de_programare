package Services;

import Domain.Entity;
import Domain.Tuple;
import Repository.Repository;

public class FriendshipService extends EntityService<Tuple<Long,Long>, Entity<Tuple<Long,Long>>> {

    public FriendshipService(Repository<Tuple<Long, Long>, Entity<Tuple<Long, Long>>> repository) {
        super(repository);
    }
}
