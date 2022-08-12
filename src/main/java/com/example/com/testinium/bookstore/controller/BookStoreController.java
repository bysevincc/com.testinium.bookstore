package com.example.com.testinium.bookstore.controller;

import com.example.com.testinium.bookstore.entity.AddBookToBookStoreDto;
import com.example.com.testinium.bookstore.entity.AddBookToCategoryDto;
import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.BookStore;
import com.example.com.testinium.bookstore.service.BookStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookStoreController {

    private final BookStoreService bookStoreService;


    @PostMapping("/bookStore")
    public  ResponseEntity<BookStore> createBookStore(@RequestBody @Valid BookStore bookStore ){

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();

        return ResponseEntity
                .created(location)
                .body(bookStoreService.saveBookStore(bookStore));
    }

    @GetMapping("/bookStores")
    public ResponseEntity<List<BookStore>> listOfBookStores(){
        return ResponseEntity.ok(bookStoreService.getBookStores());
    }

    @GetMapping("/bookstore/{bookstoreId}")
    public List<BookStore> listbookAccordingToBookStore(@PathVariable long bookstoreId){
        return bookStoreService.findByBook_bookStore(bookstoreId);
    }

    @PostMapping("/add-book-to-bookstore")
    public ResponseEntity<Void> addBookToBookStore(@RequestBody @Valid AddBookToBookStoreDto addBookToBookStoreDto) {
        bookStoreService.addBookToCategory(addBookToBookStoreDto.getBookstoreId(),addBookToBookStoreDto.getBookName());
        return ResponseEntity.ok().build();
    }
}
