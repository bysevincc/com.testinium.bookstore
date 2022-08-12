package com.example.com.testinium.bookstore.controller;

import com.example.com.testinium.bookstore.entity.AddBookToCategoryDto;
import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.service.BookService;
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
public class BookController {
    private final BookService bookService;



    @PostMapping("/book")
    public  ResponseEntity<Book> createBook(@RequestBody @Valid Book book ){

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();

        return ResponseEntity
                .created(location)
                .body(bookService.saveBook(book));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> listOfBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping("/add-book-to-category")
    public ResponseEntity<Void> addBookToCategory(@RequestBody @Valid AddBookToCategoryDto addBookToCategoryDto) {
        bookService.addBookToCategory(addBookToCategoryDto.getCategoryId(),addBookToCategoryDto.getBookName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/categories/{categoryId}", consumes = "application/json", produces = "application/json")
    public List<Book> getProductsByCategoryId(@PathVariable Long categoryId) {
        return bookService.getBookListByCategoryId(categoryId);
    }
    @PutMapping(value = "/category/{categoryId}/books/{bookId}")
    private Book updateBookCategory(@PathVariable Long bookId, @PathVariable Long categoryId) {
       return bookService.updateBookCategory(bookId,categoryId);


    }
}
