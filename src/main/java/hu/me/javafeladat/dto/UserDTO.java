package hu.me.javafeladat.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LibraryCardDTO libraryCard;
}
