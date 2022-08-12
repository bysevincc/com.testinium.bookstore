package com.example.com.testinium.bookstore.service;

import com.example.com.testinium.bookstore.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BookService {
    Book saveBook(Book book);

    List<Book> getBooks();

    void addBookToCategory(Long categoryId, String bookName);

    List<Book> getBookListByCategoryId(Long categoryId);


    Book updateBookCategory(Long bookId, Long categoryId);
}
