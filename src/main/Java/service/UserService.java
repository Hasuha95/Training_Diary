package service;

import model.DiaryUser;

public interface UserService {
    DiaryUser getUserByUserName(String userName);
    void createNewUser(DiaryUser user);
}
