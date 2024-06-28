package hu.me.javafeladat.service.interfaces;

import hu.me.javafeladat.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    void updateUser(User user, Long id);
    List<User> getAllUser();
    User getUserById(Long id);
    List<User> getUserByName(String name);
    User getUserByEmail(String email);
    void deleteUser(Long id);
}
