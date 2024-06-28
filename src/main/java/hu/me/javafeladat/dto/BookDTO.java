package hu.me.javafeladat.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private List<AuthorDTO> authors;
    private List<CategoryDTO> categories;
    private String publisher;
    private Integer publicationYear;
}
