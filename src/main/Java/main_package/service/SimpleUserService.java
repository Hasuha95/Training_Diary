package main_package.service;

import main_package.model.SimpleDiaryUser;
import main_package.model.Training;
import main_package.repository.InMemoryUserRepository;
import main_package.repository.UserRepository;


public class SimpleUserService implements UserService{
//    Реализует функции регистрации, авторизации и контроля прав доступа.
    private UserRepository repository;

    public SimpleUserService() {
        repository = new InMemoryUserRepository();
    }

    @Override
    public SimpleDiaryUser getUserByUserName(final String userName) {
         return repository.findUserByUserName(userName);
    }

    @Override
    public void saveUser(final SimpleDiaryUser user) {
        repository.saveUser(user);
    }

    @Deprecated
    public void addNewTraining(final Training training) {
        SimpleDiaryUser updatedUser = repository.findUserByUserName(null);
        updatedUser.addTraining(training);
        repository.saveUser(updatedUser);
    }

}


