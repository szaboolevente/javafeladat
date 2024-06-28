package hu.me.javafeladat.service.interfaces;

import hu.me.javafeladat.model.Author;

import java.util.List;

public interface AuthorServiceInterface {
    void addAuthorToBook(Author author, Long bookId);
    List<Author> getAuthorForBook(Long bookId);
    Author getAuthorById(Long id);
}
