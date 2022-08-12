package com.example.com.testinium.bookstore.service;

import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.BookStore;
import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.exception.NoSuchElementFoundException;
import com.example.com.testinium.bookstore.repository.BookRepository;
import com.example.com.testinium.bookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Book saveBook(Book book) {
        log.info("Save new book {} to database", book.getBookName(),book.getBookId());

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        log.info("Fetching all books");
        return bookRepository
                .findAll();
    }

    @Override
    public void addBookToCategory(Long categoryId, String bookName){
        log.info("Add book {} to the category {}", categoryId, bookName);
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new NoSuchElementFoundException("Category not found"));

        Book book=bookRepository.findByBookName(bookName)
                .orElseThrow(()->new NoSuchElementFoundException("Product name not found"));
        category.getBooks().add(book);
    }

    @Override
    public List<Book> getBookListByCategoryId(Long categoryId) {
        return bookRepository.findByBookCategory(categoryId);
    }

    @Override
    public Book updateBookCategory(Long bookId, Long categoryId) {

         Book book = getBookById(bookId);
        Category category = getCategoryById(categoryId);
        setCategoryIdForBookAndSave(book, category);
        addBookToCategoryAndSave(book, category);
        return book;
    }

    private void setCategoryIdForBookAndSave(Book book, Category category) {
        book.getCategory().setCategoryId(category.getCategoryId());
        bookRepository.save(book);
    }

    private void addBookToCategoryAndSave(Book book, Category category) {
        category.getBooks().add(book);
        categoryRepository.save(category);
    }

    private Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Error: Book not found"));
    }

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementFoundException("Error: Book not found"));
    }
}
