package hu.me.javafeladat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties("books")
public class AuthorDTO {
    private Long id;
    private String name;
    private String nationality;
    private List<BookDTO> books;
}
