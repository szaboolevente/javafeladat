package hu.me.javafeladat.service;

import hu.me.javafeladat.model.Book;
import hu.me.javafeladat.model.exception.InvalidOperationException;
import hu.me.javafeladat.model.exception.ResourceNotFoundException;
import hu.me.javafeladat.repository.BookRepository;
import hu.me.javafeladat.service.interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new InvalidOperationException("Save operation could not be performed on book", e);
        }
    }

    @Override
    public void updateBook(Book book, Long id) {
        Optional<Book> oldBookOpt = bookRepository.findById(id);
        if (oldBookOpt.isPresent()) {
            Book oldBook = oldBookOpt.get();
            oldBook.setTitle(book.getTitle());
            oldBook.setIsbn(book.getIsbn());
            oldBook.setPublisher(book.getPublisher());
            oldBook.setPublicationYear(book.getPublicationYear());
            oldBook.setAuthors(book.getAuthors());
            oldBook.setCategories(book.getCategories());
            try {
                bookRepository.save(oldBook);
            } catch (Exception e) {
                throw new InvalidOperationException("Update operation could not be performed on book", e);
            }
        } else {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with isbn " + isbn));
    }

    @Override
    public List<Book> getBookByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    @Override
    public List<Book> getBookByPublicationYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    @Override
    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            try {
                bookRepository.deleteById(id);
            } catch (Exception e) {
                throw new InvalidOperationException("Delete operation could not be performed on book", e);
            }
        } else {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
    }
}
