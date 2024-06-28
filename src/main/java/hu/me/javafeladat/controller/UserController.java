package hu.me.javafeladat.controller;

import hu.me.javafeladat.dto.LibraryCardDTO;
import hu.me.javafeladat.dto.UserDTO;
import hu.me.javafeladat.model.LibraryCard;
import hu.me.javafeladat.model.User;
import hu.me.javafeladat.service.BorrowService;
import hu.me.javafeladat.service.LibraryCardService;
import hu.me.javafeladat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LibraryCardService libraryCardService;

    @Autowired
    private BorrowService borrowService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        try {
            User user = convertToEntity(userDTO);
            userService.saveUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding user: " + e.getMessage());
        }
    }

    @GetMapping(value = "/list/all", produces = "application/json")
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<String> addLibraryCard(@PathVariable Long id, @RequestBody LibraryCardDTO libraryCardDTO) {
        try {
            LibraryCard libraryCard = convertToEntity(libraryCardDTO);
            libraryCardService.addLibraryCardToUser(libraryCard, id);
            return ResponseEntity.ok("Library card added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding library card: " + e.getMessage());
        }
    }

    @GetMapping(value = "/list/id/{id}", produces = "application/json")
    public ResponseEntity<User> listUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(value = "/list/name/{name}", produces = "application/json")
    public ResponseEntity<List<User>> listUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping(value = "/list/email/{email}", produces = "application/json")
    public ResponseEntity<User> listUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            User user = convertToEntity(userDTO);
            userService.updateUser(user, id);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating user: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting user: " + e.getMessage());
        }
    }

    private User convertToEntity(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .libraryCard(convertToEntity(userDTO.getLibraryCard() != null ? userDTO.getLibraryCard() : null))
                .build();
    }

    private LibraryCard convertToEntity(LibraryCardDTO libraryCardDTO) {
        return LibraryCard.builder()
                .cardNumber(libraryCardDTO.getCardNumber())
                .issueDate(libraryCardDTO.getIssueDate())
                .expiryDate(libraryCardDTO.getExpiryDate())
                .build();
    }
}
