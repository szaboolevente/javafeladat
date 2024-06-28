package hu.me.javafeladat.repository;

import hu.me.javafeladat.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByPublisher(String publisher);
    List<Book> findByPublicationYear(int year);
}
