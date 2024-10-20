package Domain.validators;


import Domain.User;
public class UtilizatorValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        if(entity.getUsername().isEmpty() || entity.getPassword().isEmpty() ||
            entity.getPhone_number().isEmpty() || entity.getAge() < 15)
            throw new ValidationException("User is not valid");
    }
}
