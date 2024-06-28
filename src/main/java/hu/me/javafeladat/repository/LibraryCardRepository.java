package hu.me.javafeladat.repository;

import hu.me.javafeladat.model.LibraryCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends CrudRepository<LibraryCard, Long> {
}
