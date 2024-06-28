package hu.me.javafeladat.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}