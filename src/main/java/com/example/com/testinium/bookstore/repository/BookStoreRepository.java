package com.example.com.testinium.bookstore.repository;

import com.example.com.testinium.bookstore.entity.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStoreRepository  extends JpaRepository<BookStore,Long> {
    List<BookStore> findByBookBookStore(long bookstoreId);
}
