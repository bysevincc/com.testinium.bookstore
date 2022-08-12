package com.example.com.testinium.bookstore.repository;

import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Book> findByBookCategory(long categoryId);
}
