package com.example.com.testinium.bookstore.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddBookToCategoryDto {
    @NotNull
    private Long categoryId;

    @NotNull
    private String bookName;
}
