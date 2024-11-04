package ubb.scs.map;

import ubb.scs.map.Domain.Friendship;
import ubb.scs.map.Domain.Tuple;
import ubb.scs.map.Domain.User;
import ubb.scs.map.Domain.validators.FriendshipValidator;
import ubb.scs.map.Domain.validators.UserValidator;
import ubb.scs.map.Repository.Repository;
import ubb.scs.map.Repository.database.FriendshipDatabaseRepository;
import ubb.scs.map.Repository.database.UserDatabaseRepository;
import ubb.scs.map.Repository.file.FriendshipFileRepository;
import ubb.scs.map.Repository.file.UserFileRepository;
import ubb.scs.map.Services.FriendshipService;
import ubb.scs.map.Services.NetworkService;
import ubb.scs.map.Services.UserService;
import ubb.scs.map.Ui.Console;


public class Main {
    public static void main(String[] args) {
        Repository<Long, User> userRepository = new UserDatabaseRepository("jdbc:postgresql://localhost:5432/SocialNetwork","postgres","12345678",new UserValidator());
        Repository<Tuple<Long, Long>, Friendship> friendRepository = new FriendshipDatabaseRepository("jdbc:postgresql://localhost:5432/SocialNetwork","postgres","12345678",new FriendshipValidator());
        UserService userService = new UserService(userRepository);
        FriendshipService friendshipService = new FriendshipService(friendRepository,userService);
        NetworkService networkService = new NetworkService(userService,friendshipService);
        Console console = new Console(userService,friendshipService,networkService);
        console.MainMenu();

    }
}