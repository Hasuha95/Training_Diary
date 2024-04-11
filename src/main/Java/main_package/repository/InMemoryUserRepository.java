package main_package.repository;

import main_package.model.SimpleDiaryUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository{
    private Map<String, SimpleDiaryUser> map;

    public InMemoryUserRepository() {
        this.map = new HashMap<>();
    }

    @Override
    public void saveUser(final SimpleDiaryUser user) {
        map.put(user.getUserName(), user);
    }

    @Override
    public SimpleDiaryUser findUserByUserName(final String userName) {
        if (userName == null){
            throw new IllegalArgumentException("userName can't be null");
        }
        return map.get(userName);
    }

    @Override
    public List<SimpleDiaryUser> findAllUsers() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteUserByUserName(final String userName) {
        map.remove(userName);
    }
}
