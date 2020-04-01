package pl.edu.wszib.dao;

import pl.edu.wszib.domain.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void removeUser(Long id);
    void saveUser(User user);
    void deactivateUsers(Long id, User user);
    User getById(Long id);
}
