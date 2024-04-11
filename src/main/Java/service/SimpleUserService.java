package service;

import model.DiaryUser;
import repository.InMemoryUserRepository;
import repository.UserRepository;

public class SimpleUserService implements UserService{
//    Реализует функции регистрации, авторизации и контроля прав доступа.
    private UserRepository repository;

    public SimpleUserService() {
        repository = new InMemoryUserRepository();
    }

    @Override
    public DiaryUser getUserByUserName(String userName) {
         return repository.findUserByUserName(userName);
    }

    @Override
    public void createNewUser(DiaryUser user) {
        repository.saveUser(user);
    }
}


