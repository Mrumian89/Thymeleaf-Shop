package pl.edu.wszib.dao.impl;

import org.springframework.stereotype.Repository;
import pl.edu.wszib.dao.UserDao;
import pl.edu.wszib.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private Map<Long, User> userMap;
    private static Long id = 1L;

    public UserDaoImpl() {
        this.userMap = new HashMap<>();
        prepareUserList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() < 1) {
            user.setId(id);
            id++;
        }
        userMap.put(user.getId(), user);
    }

    @Override
    public void deactivateUsers(Long id, User user) {
        for (User value : userMap.values()) {
            value.setActive(false);
        }
    }

    @Override
    public User getById(Long id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void removeUser(Long id) {
        userMap.remove(id);
    }

    private void prepareUserList() {
        User user1 = new User();
        user1.setLogin("malgoRu");
        user1.setEmail("m.ru@mru.pl");
        user1.setAge(23);
        user1.setCountry("Poland");
        user1.setActive(true);
        saveUser(user1);

        User user2 = new User();
        user2.setLogin("RumianM");
        user2.setEmail("rumianM@op.pl");
        user2.setAge(30);
        user2.setCountry("Uk");
        user2.setActive(false);
        saveUser(user2);



    }
}
