import model.DiaryUser;
import model.Training;
import model.TypeOfTraining;
import service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final UserService userService = new SimpleUserService();
    private static final TrainingService trainingService = new SimpleTrainingService();
    private static final TypeOfTrainingService typeOfTrainingService = new SimpleTypeOfTrainingService();
    private static final Scanner scan = new Scanner(System.in);
    private static DiaryUser currentUser;

    public static void main(String[] args) {
        basicData();
        int choice;

        while (true) {
            if (currentUser == null) {
                title();

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
            } else if(currentUser.getRole().equals("User")) {
                title();
                System.out.println("_Main menu_");
                System.out.println("Choose action :");
                System.out.println(
                        "1. Add new training."
                        + "\n2. Show trainings." // sort by date
                        + "\n3. Edit training."
                        + "\n4. Add new Type of training."
                        + "\n5. Get statistics."
                        + "\n6. Help."
                        + "\n7. Exit."
                );
                System.out.print("\nEnter your choice : ");
                choice = scan.nextInt();
                switch (choice) {
                    case 1: addNewTraining();
                        break;
                    case 2: showTrainings();
                        break;
                    case 3: editTraining();
                        break;
                    case 4: addNewTypeOfTraining();
                        break;
                    case 5: getStatistics();
                        break;
                    case 6: help();
                        break;
                    case 7: exit();
                        break;
                }
            } else if (currentUser.getRole().equals("Admin")) {
                title();
                //
            }
        }
    }

    private static void basicData() {
        TypeOfTraining type = new TypeOfTraining("push-up");
        typeOfTrainingService.createNewTypeOfTraining(type);

        trainingService.addNewTraining(new Training(
                "basic_training",
                LocalDateTime.now(),
                10,
                type,
                800,
                3,
                30));

        userService.createNewUser(
                new DiaryUser(
                        "GrandAdmin",
                        "adminLogin",
                        "admin666",
                        "Admin")
        );
        userService.createNewUser(
                new DiaryUser(
                        "SimpleUser",
                        "userLogin",
                        "user",
                        "User")
        );

    }

    private static void registration(){
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

    private static void title(){
        System.out.println("\n-------------------");
        System.out.println("TRAINING DIARY");
        System.out.println("-------------------\n");
    }

    private static void addNewTraining() {
        System.out.print("Enter name of training : ");
        String name = scan.next();
        System.out.print("Enter date of training (use format - \"yyyy-mm-ddThh:mm\") : ");
        String dateStr = scan.next();
        LocalDateTime date = null;
        while (date == null) {
            try {
                date = LocalDateTime.parse(dateStr);
            } catch (DateTimeParseException e){
                System.out.print("Wrong date format. Try again: ");
            }
            dateStr = scan.next();
        }
        System.out.print("Enter duration of training (in minutes) : ");
        int duration = scan.nextInt();
        System.out.print("Enter type of training : ");
        String typeStr = scan.next();
        TypeOfTraining type = typeOfTrainingService.findTypeByName(typeStr);
        while (type != null) {
            System.out.print("No such type of training. Try again : ");
            typeStr = scan.next();
            type = typeOfTrainingService.findTypeByName(typeStr);
        }
        System.out.print("Enter calories : ");
        long calories = scan.nextLong();
        System.out.print("Enter cycles : ");
        int cycles = scan.nextInt();
        System.out.print("Enter sets : ");
        int sets = scan.nextInt();

        trainingService.addNewTraining(new Training(name, date, duration, type, calories, cycles, sets));
    }

    private static void exit() {
    }

    private static void getStatistics() {
    }

    private static void addNewTypeOfTraining() {
    }

    private static void editTraining() {
    }

    private static void showTrainings() {
    }
}
