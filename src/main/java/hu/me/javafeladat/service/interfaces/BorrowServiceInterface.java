package hu.me.javafeladat.service.interfaces;

import hu.me.javafeladat.model.Borrow;

import java.util.List;

public interface BorrowServiceInterface {
    void saveBorrow(Borrow borrow);
    void returnBook(Long borrowId);
    List<Borrow> getAll();
    Borrow getById(Long id);
    List<Borrow> getByUserId(Long userId);
    List<Borrow> getByBookId(Long bookId);
}
