package hu.me.javafeladat.service;

import hu.me.javafeladat.model.User;
import hu.me.javafeladat.model.exception.InvalidOperationException;
import hu.me.javafeladat.model.exception.ResourceNotFoundException;
import hu.me.javafeladat.repository.UserRepository;
import hu.me.javafeladat.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new InvalidOperationException("Save operation could not be performed on user", e);
        }
    }

    @Override
    public void updateUser(User user, Long id) {
        Optional<User> oldUserOpt = userRepository.findById(id);
        if (oldUserOpt.isPresent()) {
            User oldUser = oldUserOpt.get();
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setLibraryCard(user.getLibraryCard());
            oldUser.setBorrows(user.getBorrows());
            try {
                userRepository.save(oldUser);
            } catch (Exception e) {
                throw new InvalidOperationException("Update operation could not be performed on user", e);
            }
        } else {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> getUserByName(String name) {
        return userRepository.findByName(name).stream().toList();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            try {
                userRepository.deleteById(id);
            } catch (Exception e) {
                throw new InvalidOperationException("Delete operation could not be performed on user", e);
            }
        } else {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }
}
