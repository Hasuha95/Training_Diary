package service;

import model.DiaryUser;
import model.Training;

public interface UserService {
    DiaryUser getUserByUserName(String userName);
    void createNewUser(DiaryUser user);
    void addNewTraining(Training training);
}
