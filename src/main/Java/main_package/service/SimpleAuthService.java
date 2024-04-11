package main_package.service;

import main_package.Main;
import main_package.UserRole;
import main_package.model.SimpleDiaryUser;

import java.util.Scanner;

public class SimpleAuthService {
    private static Scanner scan = new Scanner(System.in);
    private static final UserService userService = Main.userService;

    public static SimpleDiaryUser run(){
        int choice;
        System.out.println("1. Registration.");
        System.out.println("2. Authorization.");
        System.out.print("\nEnter your choice : ");
        choice = scan.nextInt();
        switch (choice) {
            case 1: return registration();
            case 2: return authorization();
            default: return null;
        }
    }

    private static SimpleDiaryUser registration(){
        System.out.println("_Registration_");
        System.out.print("Enter your Name : ");
        String name = scan.next();
        System.out.print("Enter login : ");
        String userName = scan.next();
        while (userService.getUserByUserName(userName) != null) {
            System.out.print("Username already exists. Set again : ");
            userName = scan.next();
        }
        System.out.print("Enter password: ");
        String password = scan.next();

        System.out.println("Choose role : "
                + "\n1. User"
                + "\n2. Admin");
        int selectedRole = scan.nextInt();
        while (selectedRole != 1 && selectedRole != 2){
            System.out.println("Please write correct role");
            selectedRole = scan.nextInt();
        }
        UserRole role = null;
        switch (selectedRole){
            case 1: role = UserRole.User;
            case 2: role = UserRole.Admin;
        }

        SimpleDiaryUser user = new SimpleDiaryUser(name, userName, password, role);
        userService.saveUser(user);

        scan.close();
        return user;
    }

    private static SimpleDiaryUser authorization() {
        System.out.println("_Authorization_");
        System.out.print("Enter login : ");
        String userName = scan.next();
        SimpleDiaryUser user = userService.getUserByUserName(userName);
        while (user == null) {
            System.out.println("User is not found. Try again : ");
            userName = scan.next();
            user = userService.getUserByUserName(userName);
        }
        System.out.print("Enter password : ");
        String password = scan.next();
        while (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password. Try again : ");
            password = scan.next();
        }
        scan.close();
        return user;
    }
}
