package repository;

import model.DiaryUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository{
    private Map<String,DiaryUser> map;

    public InMemoryUserRepository() {
        this.map = new HashMap<>();
    }

    @Override
    public void saveUser(final DiaryUser user) {
        map.put(user.getUserName(), user);
    }

    @Override
    public DiaryUser findUserByUserName(final String userName) {
        if (userName == null){
            throw new IllegalArgumentException("userName can't be null");
        }
        return map.get(userName);
    }

    @Override
    public List<DiaryUser> findAllUsers() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteUserByUserName(final String userName) {
        map.remove(userName);
    }
}
