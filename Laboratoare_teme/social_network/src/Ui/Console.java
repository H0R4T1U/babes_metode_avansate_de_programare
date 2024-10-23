package Ui;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Domain.validators.ValidationException;
import Services.FriendshipService;
import Services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Console{
    private UserService userService;
    private FriendshipService friendshipService;

    public Console(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
    }
    private void showMenu() {
        System.out.println("1. add user\n" +
                "2. remove user\n" +
                "3. view users\n" +
                "4. add friendship\n" +
                "5. remove friendship\n" +
                "6. view number of communities\n" +
                "7. view most sociable community\n" +
                "0. exit\n");
    }
    private void executeInput(int option) {
        switch (option) {
            case 1:
                addUser();
                return;
            case 2:
                deleteUser();
                return;
            case 3:
                System.out.println(userService.getAll());
                return;
            case 4:
                addFriend();
                return;
            case 5:
                deleteFriend();
                return;
            case 7:
                getNoOfComunities();
                return;
            case 8:
                mostSociableNetwork();
                return;
            default:
                return;
        }
    }
    
    public void MainMenu() {
        boolean stop = false;
        while (!stop) {
            showMenu();
            Integer option = readUserOption();
            if (option == 0)
                stop = true;
            else
                executeInput(option);
        }
    }
    private Integer readUserOption() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int inputValue = -1;
        try {
            String input = reader.readLine();
            inputValue = Integer.parseInt(input);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading input!\n");
        }
        return inputValue;
    }


    
    private void addUser() {
        System.out.println("Add User\nid: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.parseLong(reader.readLine());
            System.out.println("username: ");
            String username = reader.readLine();
            System.out.println("Password: ");
            String password = reader.readLine();
            System.out.println("Phone number: ");
            String phoneNumber = reader.readLine();
            System.out.println("age: ");
            int age = Integer.parseInt(reader.readLine());
            User user = new User(username, password,phoneNumber, LocalDateTime.now(),age);
            user.setId(id);
            userService.create(user);
            System.out.println("User was added successfully.\n");
        } catch (IOException | IllegalArgumentException | ValidationException e) {
            System.out.println("Error reading input:\n" + e.getMessage());
        }
    }
    
    private void addFriend() {
        System.out.println("Add Friend\nID user1: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long id1 = Long.parseLong(reader.readLine());
            System.out.println("ID user2: ");
            Long id2 = Long.parseLong(reader.readLine());
            if(userService.getById(id1) != null && userService.getById(id2)!=null){
                Friendship friendship = new Friendship();
                friendship.setId(new Tuple<>(id1,id2));
                friendshipService.create(friendship);
            }else{
                System.out.println("Users are not valid!");
            }
        }catch(IOException | IllegalArgumentException e){
            System.out.println("Error reading input:\n" + e.getMessage());
        }
    }

    
    private void deleteUser() {
        System.out.println("Delete User\n id: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long id = Long.parseLong(reader.readLine());
            String name = userService.getById(id).getUsername();
            if(! name.isEmpty()){
                System.out.println("Deleteing User:"+ name  );
                userService.delete(id);
            }else{
                System.out.println("User not found");
            }


        }catch(IOException  | IllegalArgumentException e){
            System.out.println("Error reading input: \n" + e.getMessage());

        }
    }

    
    private void deleteFriend() {
        System.out.println("Delete Friend\n id1: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Long id1 = Long.parseLong(reader.readLine());
            System.out.println("ID user2: ");
            long id2 = Long.parseLong(reader.readLine());
            if(userService.getById(id1) != null && userService.getById(id2)!=null){
                friendshipService.delete(new Tuple<>(id1,id2));
            }
        } catch( IOException | IllegalArgumentException e){
            System.out.println("Error reading input:\n" + e.getMessage());
        }
    }

    
    private void mostSociableNetwork() {
        System.out.println("Most sociable community consists of: " + friendshipService.getMostSocialCommunity() + '\n');
    }

    
    private void getNoOfComunities() {
        System.out.println("Number of communities = " + friendshipService.getNumberOfCommunities() + "\n");
    }
}
