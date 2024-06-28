package hu.me.javafeladat.service;

import hu.me.javafeladat.model.Book;
import hu.me.javafeladat.model.Category;
import hu.me.javafeladat.model.exception.InvalidOperationException;
import hu.me.javafeladat.model.exception.ResourceNotFoundException;
import hu.me.javafeladat.repository.CategoryRepository;
import hu.me.javafeladat.service.interfaces.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {
    private final CategoryRepository categoryRepository;
    private final BookService bookService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BookService bookService) {
        this.categoryRepository = categoryRepository;
        this.bookService = bookService;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }

    @Override
    public void addCategoryToBook(Category category, Long bookId) {
        Book book = bookService.getBookById(bookId);
        category.getBooks().add(book);
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            throw new InvalidOperationException("Operation could not be performed on category", e);
        }
    }

    @Override
    public List<Category> getCategoriesForBook(Long bookId) {
        return categoryRepository.findByBooksId(bookId);
    }
}
