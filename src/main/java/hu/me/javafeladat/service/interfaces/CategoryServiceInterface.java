package hu.me.javafeladat.service.interfaces;

import hu.me.javafeladat.model.Category;

import java.util.List;

public interface CategoryServiceInterface {
    void addCategoryToBook(Category category, Long bookId);
    List<Category> getCategoriesForBook(Long bookId);
    Category getCategoryById(Long id);
}
