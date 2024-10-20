package Services;

import Domain.Network;
import Repository.Repository;

public class NetworkService extends EntityService<Long,Network> {
    public NetworkService(Repository<Long, Network> repository) {
        super(repository);
    }

}
