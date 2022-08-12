package com.example.com.testinium.bookstore.controller;

import com.example.com.testinium.bookstore.entity.Category;
import com.example.com.testinium.bookstore.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CategoryController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<Category> categoryDtoArgumentCaptor;

    @Test
    void testSaveOrUpdateCategory() throws Exception {
        Category ValidCategory = Category.builder()
                .categoryId(5L)
                .categoryName("Machine")
                .build();

        mockMvc
                .perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ValidCategory))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString("/api/category")));

        verify(categoryService).saveCategory(any(Category.class));
        verify(categoryService).saveCategory(categoryDtoArgumentCaptor.capture());
        AssertionsForClassTypes.assertThat(categoryDtoArgumentCaptor.getValue().getCategoryName()).isEqualTo("Machine");
    }




}