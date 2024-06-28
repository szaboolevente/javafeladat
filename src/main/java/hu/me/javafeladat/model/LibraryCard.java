package hu.me.javafeladat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "library_cards")
@Data
@Entity
@Builder
@AllArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LibraryCard(){
        this.issueDate = LocalDate.now();
        this.expiryDate = LocalDate.now().plusYears(3);
    }

    @Override
    public String toString() {
        return "LibraryCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                ", user=" + user +
                '}';
    }
}
