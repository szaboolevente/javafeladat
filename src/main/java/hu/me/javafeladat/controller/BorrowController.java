package hu.me.javafeladat.controller;

import hu.me.javafeladat.dto.BorrowDTO;
import hu.me.javafeladat.model.Borrow;
import hu.me.javafeladat.service.BookService;
import hu.me.javafeladat.service.BorrowService;
import hu.me.javafeladat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String> addBorrow(@RequestBody BorrowDTO borrowDTO) {
        try {
            Borrow borrow = convertToEntity(borrowDTO);
            borrowService.saveBorrow(borrow);
            return ResponseEntity.ok("Borrow added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding borrow: " + e.getMessage());
        }
    }

    @PutMapping(value = "/return/{id}")
    public ResponseEntity<String> returnBook(@PathVariable Long id) {
        try {
            borrowService.returnBook(id);
            return ResponseEntity.ok("Book returned successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error returning book: " + e.getMessage());
        }
    }

    @GetMapping(value = "/list/all", produces = "application/json")
    public ResponseEntity<List<Borrow>> listAllBorrows() {
        return ResponseEntity.ok(borrowService.getAll());
    }

    @GetMapping(value = "/list/user/{id}", produces = "application/json")
    public ResponseEntity<List<Borrow>> listUserBorrows(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.getByUserId(id));
    }

    @GetMapping(value = "/list/book/{id}", produces = "application/json")
    public ResponseEntity<List<Borrow>> listBookBorrows(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.getByBookId(id));
    }

    private Borrow convertToEntity(BorrowDTO borrowDTO) {
        return Borrow.builder()
                .user(userService.getUserById(borrowDTO.getUserId()))
                .book(bookService.getBookById(borrowDTO.getBookId()))
                .borrowDate(borrowDTO.getBorrowDate())
                .returnDate(borrowDTO.getReturnDate() != null ? borrowDTO.getReturnDate() : null)
                .build();
    }
}
