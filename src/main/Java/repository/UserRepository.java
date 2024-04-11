package repository;

import model.DiaryUser;

import java.util.List;

public interface UserRepository {
    //    Содержит методы для сохранения,
//    получения и обновления информации о пользователях.
    void saveUser(final DiaryUser user);
    DiaryUser findUserByUserName(final String userName);
    List<DiaryUser> findAllUsers();
    void deleteUserByUserName(final String userName);

}
