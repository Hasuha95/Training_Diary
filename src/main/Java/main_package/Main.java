package main_package;

import main_package.model.SimpleDiaryUser;
import main_package.model.Training;
import main_package.model.TypeOfTraining;
import main_package.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final UserService userService = new SimpleUserService();
    public static final TypeOfTrainingService typeOfTrainingService = new SimpleTypeOfTrainingService();
    private static final Scanner scan = new Scanner(System.in);
    public static SimpleDiaryUser currentUser;

    public static void main(String[] args) {
        basicData();
        int choice;

        while (true) {
            if (currentUser == null) {
                title();
//                System.out.println("1. Registration.");
//                System.out.println("2. Authorization.");
//                System.out.println("3. Help.");
//                System.out.print("\nEnter your choice : ");
//                choice = scan.nextInt();
//                switch (choice) {
//                    case 1: registration();
//                        break;
//                    case 2: authorization();
//                        break;
//                    case 3: help();
//                        break;
//                }
                currentUser = SimpleAuthService.run();

            } else {
                title();
//                System.out.println("_Main menu_");
//                System.out.println("Choose action :");
//                System.out.println(
//                        "1. Add new training."
//                        + "\n2. Show trainings." // sort by date
//                        + "\n3. Edit training."
//                        + "\n4. Add new Type of training."
//                        + "\n5. Get statistics."
//                        + "\n6. Get information about user."
//                        + "\n7. Help."
//                        + "\n8. Exit."
//                );
//                System.out.print("\nEnter your choice : ");
//                choice = scan.nextInt();
//                switch (choice) {
//                    case 1: addNewTraining();
//                        break;
//                    case 2: showTrainings();
//                        break;
//                    case 3: editTraining();
//                        break;
//                    case 4: addNewTypeOfTraining();
//                        break;
//                    case 5: getStatistics();
//                        break;
//                    case 6: getInfoAboutUser();
//                        break;
//                    case 7: help();
//                        break;
//                    case 8: exit();
//                }
                MainMenuActions.run();

            }
        }
    }

    private static void basicData() {
        TypeOfTraining type = new TypeOfTraining("push-up");
        typeOfTrainingService.saveTypeOfTraining(type);

        Training training1 = new Training(
                "basic_training1",
                LocalDateTime.now().minusDays(1),
                8,
                type,
                600,
                3,
                20);

        Training training2 = new Training(
                "basic_training2",
                LocalDateTime.now(),
                10,
                type,
                800,
                3,
                30);

        userService.saveUser(
                new SimpleDiaryUser(
                        "Admin",
                        "admin",
                        "admin",
                        UserRole.Admin)
        );
        userService.saveUser(
                new SimpleDiaryUser(
                        "User",
                        "user",
                        "user",
                        UserRole.User,
                        new ArrayList<>(Arrays.asList(training1,training2)))
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
        currentUser = new SimpleDiaryUser(name, userName, password, role);
        userService.saveUser(currentUser);
    }

    private static void authorization() {
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
        currentUser = user;
    }

    @Deprecated
    private static void help() {

    }

    public static void title() {
        System.out.println("  __________  ___    _____   _______   ________   ____  _______    ______  __\n" +
                " /_  __/ __ \\/   |  /  _/ | / /  _/ | / / ____/  / __ \\/  _/   |  / __ \\ \\/ /\n" +
                "  / / / /_/ / /| |  / //  |/ // //  |/ / / __   / / / // // /| | / /_/ /\\  / \n" +
                " / / / _, _/ ___ |_/ // /|  // // /|  / /_/ /  / /_/ // // ___ |/ _, _/ / /  \n" +
                "/_/ /_/ |_/_/  |_/___/_/ |_/___/_/ |_/\\____/  /_____/___/_/  |_/_/ |_| /_/  ");
    }

    private static void addNewTraining() {
        title();
        System.out.println("_New training_" + "\n");

        System.out.print("Enter date of training (use format - \"yyyy-mm-ddThh:mm\") : ");
        String dateStr = scan.next();
        LocalDateTime date = null;
        while (date == null) {
            try {
                date = LocalDateTime.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.print("Wrong date format. Try again: ");
                dateStr = scan.next();
            }
        }

        System.out.println("Enter type of training : ");
        List<TypeOfTraining> types = typeOfTrainingService.getAllTypes();
        System.out.println("(types: " + types.toString() + ")");
        String typeStr = scan.next();
        TypeOfTraining type = typeOfTrainingService.findTypeByName(typeStr);
        while (type == null || isTrainingWithTypeExistInDay(type, LocalDate.from(date))) {
            if (type == null) {
                System.out.print("No such type of training. Try again : ");
            } else {
                System.out.println("Training already exist. Try again : ");
            }
            typeStr = scan.next();
            type = typeOfTrainingService.findTypeByName(typeStr);
        }

        System.out.print("Enter name of training : ");
        String name = scan.next();

        System.out.print("Enter duration of training (in minutes) : ");
        int duration = scan.nextInt();

        System.out.print("Enter calories : ");
        long calories = scan.nextLong();

        System.out.print("Enter cycles : ");
        int cycles = scan.nextInt();

        System.out.print("Enter sets : ");
        int sets = scan.nextInt();
        Training training = new Training(name, date, duration, type, calories, cycles, sets);

        currentUser.addTraining(training);
        userService.saveUser(currentUser);
//        trainingService.saveTraining(training);
    }

    private static boolean isTrainingWithTypeExistInDay(final TypeOfTraining type, final LocalDate localDate) {
        for (Training t : currentUser.getDalyTrainings(localDate)) {
            if (t.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    private static void showTrainings() {
        System.out.println("_Trainings_" + "\n");
        Collections.sort(currentUser.getTrainings(), Comparator.comparing(Training::getDate));
        currentUser.getTrainings().forEach(t -> System.out.println(t + "\n"));

        System.out.println("\nIf you want exit, write \"exit\"");
        String command = scan.next();
        while (!command.equals("exit")){
            System.out.println("unknown command");
            command = scan.next();
        }
    }

    private static void addNewTypeOfTraining() {
        System.out.flush();
        title();
        System.out.println("_New type of training_" + "\n");

        System.out.print("Enter name for new type of training : ");
        String name = scan.next();
        while (name == null){
            System.out.print("Wrong name for type of training. Try again: ");
            name = scan.next();
        }

        typeOfTrainingService.saveTypeOfTraining(new TypeOfTraining(name));
    }

    private static void editTraining() {
        System.out.flush();
        title();
        System.out.println("_Edit training_" + "\n");

        List<String> trainings = currentUser.getTrainings()
                .stream()
                .map(t -> t.getName())
                .collect(Collectors.toList());

        System.out.println("You have next trainings: ");
        System.out.println(currentUser.getTrainings().toString());

        System.out.print("Choose the training you want to edit, and write its name : ");
        String trainingName = scan.next();
        while (!trainings.contains(trainingName)){
            System.out.print("Incorrect training name. Try again : ");
            trainingName = scan.next();
        }
        Training selectedTraining = null;
        for (Training t : currentUser.getTrainings()) {
            if (t.getName().equals(trainingName)){
                selectedTraining = t;
                break;
            }
        }

        System.out.println("Choose the action you want to do: "
                + "\n1. Change"
                + "\n2. Delete");

        int choice = scan.nextInt();
        while (choice != 1 && choice != 2){
            System.out.print("Please write correct number : ");
            choice = scan.nextInt();
        }
        switch (choice){
            case 1: change(selectedTraining);
            break;
            case 2: delete(selectedTraining);
            break;
        }

    }

    private static void change(Training training) {

        System.out.println("What do you want to change: "
                + "\n1. Name"
                + "\n2. Date"
                + "\n3. DurationInMinutes"
                + "\n4. Type"
                + "\n5. Calories"
                + "\n6. Cycles"
                + "\n7. Sets");
        int choice = scan.nextInt();
        while (choice>7 || choice<1){
            System.out.println("Please write correct number : ");
            choice = scan.nextInt();
        }
        System.out.println("Write new value : ");
        switch (choice){
            case 1: training.setName(scan.next());
            break;
            case 2:
                try {
                    training.setDate(LocalDateTime.parse(scan.next()));
                } catch (DateTimeParseException e) {
                    System.out.print("Wrong date format. Try again: ");
                    scan.next();
                }
        }



    }

    private static void delete(Training training) {
        currentUser.deleteTraining(training);
        userService.saveUser(currentUser);
    }

    private static void getStatistics() {



        System.out.println("\nIf you want exit, write \"exit\"");
        String command = scan.next();
        while (!command.equals("exit")){
            System.out.println("unknown command");
            command = scan.next();
        }
    }

    private static void getInfoAboutUser() {
    }

    private static void exit() {
        currentUser = null;
    }
}
