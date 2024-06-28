package hu.me.javafeladat.controller;

import hu.me.javafeladat.dto.BookDTO;
import hu.me.javafeladat.model.Book;
import hu.me.javafeladat.service.AuthorService;
import hu.me.javafeladat.service.BookService;
import hu.me.javafeladat.service.BorrowService;
import hu.me.javafeladat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BorrowService borrowService;

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
        try {
            Book book = buildBook(bookDTO);
            bookService.saveBook(book);
            return ResponseEntity.ok("Book added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding book: " + e.getMessage());
        }
    }

    @GetMapping(value = "/list/all", produces = "application/json")
    public ResponseEntity<List<Book>> listAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(value = "/list/title/{title}", produces = "application/json")
    public ResponseEntity<List<Book>> listBooksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping(value = "/list/isbn/{isbn}", produces = "application/json")
    public ResponseEntity<Book> listBooksByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping(value = "/list/publisher/{publisher}", produces = "application/json")
    public ResponseEntity<List<Book>> listBooksByPublisher(@PathVariable String publisher) {
        return ResponseEntity.ok(bookService.getBookByPublisher(publisher));
    }

    @GetMapping(value = "/list/pubyear/{year}", produces = "application/json")
    public ResponseEntity<List<Book>> listBooksByPublicationYear(@PathVariable int year) {
        return ResponseEntity.ok(bookService.getBookByPublicationYear(year));
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        try {
            Book book = buildBook(bookDTO);
            bookService.updateBook(book, id);
            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating book: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting book: " + e.getMessage());
        }
    }

    private Book buildBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publisher(bookDTO.getPublisher())
                .publicationYear(bookDTO.getPublicationYear())
                .authors(bookDTO.getAuthors().stream()
                        .map(authorDTO -> authorService.getAuthorById(authorDTO.getId()))
                        .collect(Collectors.toList()))
                .categories(bookDTO.getCategories().stream()
                        .map(categoryDTO -> categoryService.getCategoryById(categoryDTO.getId()))
                        .collect(Collectors.toList()))
                .build();
    }
}
