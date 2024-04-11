package main_package.service;

import main_package.Main;
import main_package.model.SimpleDiaryUser;
import main_package.model.Training;
import main_package.model.TypeOfTraining;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainMenuActions {
    public static final UserService userService = Main.userService;

    private static final TypeOfTrainingService typeOfTrainingService = Main.typeOfTrainingService;
    private static SimpleDiaryUser currentUser = Main.currentUser;
    private static Scanner scan = new Scanner(System.in);

    public static void run(){
        int choice;

        System.out.println("_Main menu_");
        System.out.println("Choose action :");
        System.out.println(
                "1. Add new training."
                        + "\n2. Show trainings." // sort by date
                        + "\n3. Edit training."
                        + "\n4. Add new Type of training."
                        + "\n5. Get statistics."
                        + "\n6. Get information about user."
                        + "\n7. Help."
                        + "\n8. Exit."
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
            case 6: getInfoAboutUser();
                break;
            case 7: help();
                break;
            case 8: exit();
        }
    }

    private static void addNewTraining() {
        Main.title();
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
        Main.title();
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
        Main.title();
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

    @Deprecated
    private static void help() {

    }

    private static void exit() {
        currentUser = null;
    }
}
