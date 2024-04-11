package main_package.repository;

import main_package.model.SimpleDiaryUser;

import java.util.List;

public interface UserRepository {
    //    Содержит методы для сохранения,
//    получения и обновления информации о пользователях.
    void saveUser(final SimpleDiaryUser user);
    SimpleDiaryUser findUserByUserName(final String userName);
    List<SimpleDiaryUser> findAllUsers();
    void deleteUserByUserName(final String userName);

}
