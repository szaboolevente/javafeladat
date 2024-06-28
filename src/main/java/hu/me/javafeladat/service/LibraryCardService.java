package hu.me.javafeladat.service;

import hu.me.javafeladat.model.LibraryCard;
import hu.me.javafeladat.model.User;
import hu.me.javafeladat.model.exception.InvalidOperationException;
import hu.me.javafeladat.model.exception.ResourceNotFoundException;
import hu.me.javafeladat.repository.LibraryCardRepository;
import hu.me.javafeladat.service.interfaces.LibraryCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardService implements LibraryCardServiceInterface {
    private final LibraryCardRepository libraryCardRepository;
    private final UserService userService;

    @Autowired
    public LibraryCardService(LibraryCardRepository libraryCardRepository, UserService userService) {
        this.libraryCardRepository = libraryCardRepository;
        this.userService = userService;
    }

    @Override
    public void addLibraryCardToUser(LibraryCard libraryCard, Long userId) {
        User user = userService.getUserById(userId);
        libraryCard.setUser(user);
        try {
            libraryCardRepository.save(libraryCard);
        } catch (Exception e) {
            throw new InvalidOperationException("Operation cannot be performed on library card", e);
        }
    }

    @Override
    public LibraryCard getLibraryCardForUser(Long userId) {
        return libraryCardRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Library card not found for user id " + userId));
    }
}
