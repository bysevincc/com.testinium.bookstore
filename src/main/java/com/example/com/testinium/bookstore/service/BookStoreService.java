package com.example.com.testinium.bookstore.service;

import com.example.com.testinium.bookstore.entity.BookStore;

import java.util.List;

public interface BookStoreService {
    BookStore saveBookStore(BookStore bookStore);

    List<BookStore> getBookStores();

    List<BookStore> findByBook_bookStore(long bookstoreId);

    void addBookToCategory(Long bookstoreId, String bookName);
}
