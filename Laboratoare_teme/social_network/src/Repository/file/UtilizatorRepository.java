package Repository.file;

import Domain.User;
import Domain.validators.Validator;

import java.time.LocalDateTime;

public class UtilizatorRepository extends AbstractFileRepository<Long, User>{
    public UtilizatorRepository(Validator<User> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public User createEntity(String line) {
        String[] splited = line.split(";");
        User u = new User(splited[1], splited[2],splited[3], LocalDateTime.parse(splited[4]),Integer.parseInt(splited[5]));
        u.setId(Long.parseLong(splited[0]));
        return u;
    }

    @Override
    public String saveEntity(User entity) {
        String s = entity.getId() + ";" + entity.getUsername() + ";" + entity.getPassword() + ";" + entity.getPhoneNumber() + ";" +
                entity.getJoinDate() + ";" + entity.getAge();
        return s;
    }
}
