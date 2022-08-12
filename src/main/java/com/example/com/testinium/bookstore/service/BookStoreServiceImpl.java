package com.example.com.testinium.bookstore.service;

import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.BookStore;
import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.exception.NoSuchElementFoundException;
import com.example.com.testinium.bookstore.repository.BookRepository;
import com.example.com.testinium.bookstore.repository.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BookStoreServiceImpl implements BookStoreService{

    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    @Override
    public BookStore saveBookStore(BookStore bookStore) {
        log.info("Save new book {} to database", bookStore.getBookStoreName());

        return bookStoreRepository.save(bookStore);
    }

    @Override
    public List<BookStore> getBookStores() {
        log.info("Fetching all bookstores");
        return bookStoreRepository
                .findAll();
    }

    @Override
    public List<BookStore> findByBook_bookStore(long bookstoreId) {
        return bookStoreRepository.findByBookBookStore(bookstoreId);
    }

    @Override
    public void addBookToCategory(Long bookstoreId, String bookName) {
        log.info("Add book {} to the category {}", bookstoreId, bookName);
        BookStore bookStore=bookStoreRepository.findById(bookstoreId)
                .orElseThrow(()->new NoSuchElementFoundException("Category not found"));

        Book book=bookRepository.findByBookName(bookName)
                .orElseThrow(()->new NoSuchElementFoundException("Product name not found"));
        bookStore.getBooks().add(book);
    }
}
