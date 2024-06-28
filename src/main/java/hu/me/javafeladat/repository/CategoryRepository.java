package hu.me.javafeladat.repository;

import hu.me.javafeladat.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByBooksId(Long id);
}
