package main_package.service;

import main_package.model.SimpleDiaryUser;

public interface UserService {
    SimpleDiaryUser getUserByUserName(final String userName);
    void saveUser(final SimpleDiaryUser user);
}
