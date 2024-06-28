package hu.me.javafeladat.repository;

import hu.me.javafeladat.model.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    List<Borrow> findBorrowByUserId(Long userId);
    List<Borrow> findBorrowByBookId(Long bookId);
}
