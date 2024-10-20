package Domain.validators;

import Domain.Friendship;

public class FriendshipValidator implements Validator<Friendship> {
    @Override
    public void validate(Friendship entity) throws ValidationException {
        if(entity.getId().getE1()  == null || entity.getId().getE2() == null) {
            throw new ValidationException("Friendship is not valid");
        }
    }
}
