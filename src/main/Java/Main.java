import model.DiaryUser;
import service.SimpleUserService;
import service.UserService;
import java.util.Scanner;

public class Main {
    static final UserService userService = new SimpleUserService();
    static final Scanner scan = new Scanner(System.in);
    static DiaryUser currentUser;

    public static void main(String[] args) {
        int choice;
        userService.createNewUser(
                new DiaryUser("GrandAdmin",
                "adminLogin",
                "admin666",
                "Admin")
        );

        while (true) {
            System.out.println("\n-------------------");
            System.out.println("TRAINING DIARY");
            System.out.println("-------------------\n");
            if (currentUser == null) {
                System.out.println("1. Registration.");
                System.out.println("2. Authorization.");
                System.out.println("3. Help.");
                System.out.print("\nEnter your choice : ");
                choice = scan.nextInt();
                switch (choice) {
                    case 1: registration();
                    break;
                    case 2: authorization();
                    break;
                    case 3: help();
                    break;
                }
            }
        }
    }

    private static void registration(){
        System.out.println("_Registration_");
        System.out.println("Enter your Name : ");
        String name = scan.next();
        System.out.println("Enter login : ");
        String userName = scan.next();
        while (userService.getUserByUserName(userName) != null) {
            System.out.println("Username already exists. Set again : ");
            userName = scan.next();
        }
        System.out.println("Enter password:");
        String password = scan.next();
        System.out.println("Choose role: User or Admin");
        String role = scan.next();
        while (!role.equals("User") && !role.equals("Admin")){
            System.out.println("Please write correct role");
            role = scan.next();
        }
        currentUser = new DiaryUser(name, userName, password, role);
        userService.createNewUser(currentUser);
    }

    private static void authorization() {
        System.out.println("_Authorization_");
        System.out.print("Enter login : ");
        String userName = scan.next();
        DiaryUser user = userService.getUserByUserName(userName);
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
        currentUser = user;
    }

    @Deprecated
    private static void help() {

    }

}
