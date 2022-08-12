package com.example.com.testinium.bookstore.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddBookToBookStoreDto {
    @NotNull
    private Long bookstoreId;

    @NotNull
    private String bookName;
}
