package com.example.com.testinium.bookstore.service;

import com.example.com.testinium.bookstore.entity.AddBookToCategoryDto;
import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.exception.NoSuchElementFoundException;
import com.example.com.testinium.bookstore.repository.BookRepository;
import com.example.com.testinium.bookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final BookRepository bookRepository;

    @Override
    public Category saveCategory(Category category) {
        log.info("Save new category {} to database", category.getCategoryName(),category.getCategoryId());

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        log.info("Fetching all categories");
        return categoryRepository
                .findAll();
    }

    @Override
    public List<Book> findByBook_category(long categoryId) {
        return categoryRepository.findByBookCategory(categoryId);
    }

    @Override
    public Category removeProductFromCategory(Long categoryId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new NoSuchElementFoundException("Not found product by id: " + bookId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new NoSuchElementFoundException("Not found category by id: " + categoryId));
        category.getBooks().remove(book);
        return  categoryRepository.save(category);

    }


}
