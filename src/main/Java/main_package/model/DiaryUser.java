package main_package.model;

import main_package.UserRole;

import java.util.List;

public abstract class DiaryUser {
    private String name;
    private String userName;
    private String password;
    private UserRole role;
    private List<Training> trainings;

    public abstract void addTraining(Training training);

    public abstract void deleteTraining(Training training);
}
