package com.example.com.testinium.bookstore.service;

import com.example.com.testinium.bookstore.entity.AddBookToCategoryDto;
import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);

    List<Category> getCategories();

    List<Book> findByBook_category(long categoryId);

    Category removeProductFromCategory(Long categoryId, Long bookId);

    }
