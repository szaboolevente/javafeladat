package hu.me.javafeladat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "books")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;
    private int publicationYear;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Borrow> borrows = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", authors=" + authors +
                ", categories=" + categories +
                ", borrows=" + borrows +
                '}';
    }
}
