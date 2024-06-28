package hu.me.javafeladat.service.interfaces;

import hu.me.javafeladat.model.Book;

import java.util.List;

public interface BookServiceInterface {
    Book saveBook(Book book);
    void updateBook(Book book, Long id);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    List<Book> getBookByTitle(String title);
    Book getBookByIsbn(String isbn);
    List<Book> getBookByPublisher(String publisher);
    List<Book> getBookByPublicationYear(int year);
    void deleteBook(Long id);
}
