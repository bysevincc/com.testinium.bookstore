package com.example.com.testinium.bookstore.repository;

import com.example.com.testinium.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByBookName(String bookName);

    List<Book> findByBookCategory(Long categoryId);
}
