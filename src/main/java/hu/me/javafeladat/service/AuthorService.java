package hu.me.javafeladat.service;

import hu.me.javafeladat.model.Author;
import hu.me.javafeladat.model.Book;
import hu.me.javafeladat.model.exception.InvalidOperationException;
import hu.me.javafeladat.model.exception.ResourceNotFoundException;
import hu.me.javafeladat.repository.AuthorRepository;
import hu.me.javafeladat.service.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements AuthorServiceInterface {

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    @Override
    public void addAuthorToBook(Author author, Long bookId) {
        Book book = bookService.getBookById(bookId);
        author.getBooks().add(book);
        try {
            authorRepository.save(author);
        } catch (Exception e) {
            throw new InvalidOperationException("Operation could not be performed on author", e);
        }
    }

    @Override
    public List<Author> getAuthorForBook(Long bookId) {
        return authorRepository.findByBooksId(bookId);
    }
}
