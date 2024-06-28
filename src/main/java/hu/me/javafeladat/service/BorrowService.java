package hu.me.javafeladat.service;

import hu.me.javafeladat.model.Borrow;
import hu.me.javafeladat.model.exception.InvalidOperationException;
import hu.me.javafeladat.model.exception.ResourceNotFoundException;
import hu.me.javafeladat.repository.BorrowRepository;
import hu.me.javafeladat.service.interfaces.BorrowServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService implements BorrowServiceInterface {
    private final BorrowRepository borrowRepository;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository, UserService userService, BookService bookService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void saveBorrow(Borrow borrow) {
        try {
            borrowRepository.save(borrow);
        } catch (Exception e) {
            throw new InvalidOperationException("Save operation could not be performed on borrow", e);
        }
    }

    @Override
    public void returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id " + borrowId));
        borrow.setReturnDate(LocalDate.now());
        try {
            borrowRepository.save(borrow);
        } catch (Exception e) {
            throw new InvalidOperationException("Return operation could not be performed on borrow", e);
        }
    }

    @Override
    public List<Borrow> getAll() {
        return (List<Borrow>) borrowRepository.findAll();
    }

    @Override
    public Borrow getById(Long id) {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id " + id));
    }

    @Override
    public List<Borrow> getByUserId(Long userId) {
        return borrowRepository.findBorrowByUserId(userId);
    }

    @Override
    public List<Borrow> getByBookId(Long bookId) {
        return borrowRepository.findBorrowByBookId(bookId);
    }
}
