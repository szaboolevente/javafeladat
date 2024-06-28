package hu.me.javafeladat.service.interfaces;

import hu.me.javafeladat.model.LibraryCard;

public interface LibraryCardServiceInterface {
    void addLibraryCardToUser(LibraryCard libraryCard, Long userId);
    LibraryCard getLibraryCardForUser(Long userId);
}
