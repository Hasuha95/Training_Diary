import model.DiaryUser;
import service.SimpleUserService;
import service.UserService;
import java.util.Scanner;

public class Main {
    static final UserService userService = new SimpleUserService();
    static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        System.out.println("\n-------------------");
        System.out.println("TRAINING DIARY");
        System.out.println("-------------------\n");

        boolean authenticated = false;
        while (true) {
            if (!authenticated) {
                System.out.println("1. Registration.");
                System.out.println("2. Authorization.");
                System.out.println("3. Help.");
                System.out.print("\nEnter your choice : ");
            }
            choice = scan.nextInt();
            switch (choice) {
                case 1: registration();
            }
        }
    }

    public static void registration(){
        System.out.print("Enter your Name : ");
        String name = scan.nextLine();
        System.out.print("Enter login : ");
        String userName = scan.nextLine();
        while (userService.getUserByUserName(userName) != null) {
            System.out.println("Username already exists. Set again : ");
            userName = scan.next();
        }
        System.out.println("Enter password:");
        String password = scan.next();
        System.out.println("Choose role: "+ "\nUser or Admin");
        String role = scan.nextLine();
        while (role!="User" || role!="Admin"){
            System.out.println("Please choose correct role");
            role = scan.nextLine();
        }
        userService.createNewUser(new DiaryUser(name, userName, password, role));
    }

}
