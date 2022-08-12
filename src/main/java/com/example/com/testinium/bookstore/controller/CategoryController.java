package com.example.com.testinium.bookstore.controller;

import com.example.com.testinium.bookstore.entity.AddBookToCategoryDto;
import com.example.com.testinium.bookstore.entity.Book;
import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/category")
    public  ResponseEntity<Category> createCategory(@RequestBody @Valid Category category){

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();

        return ResponseEntity
                .created(location)
                .body(categoryService.saveCategory(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> listOfCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/category/{categoryId}")
    public List<Book> listbookAccordingToCategory(@PathVariable long categoryId){
        return this.categoryService.findByBook_category(categoryId);
    }

    @DeleteMapping(value = "/{categoryId}/books/{bookId}")
    public ResponseEntity<Category>deleteBookToCategory(@PathVariable Long categoryId, @PathVariable Long bookId){
        return ResponseEntity.ok(categoryService.removeProductFromCategory(categoryId,bookId));
    }


}
