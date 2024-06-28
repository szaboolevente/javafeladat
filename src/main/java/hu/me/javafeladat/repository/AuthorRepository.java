package hu.me.javafeladat.repository;

import hu.me.javafeladat.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByName(String name);
    List<Author> findByNationality(String nationality);
    List<Author> findByBooksId(Long id);
}
