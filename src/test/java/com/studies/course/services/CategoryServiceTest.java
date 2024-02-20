package com.studies.course.services;

import com.studies.course.entities.Category;
import com.studies.course.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Tag("UnitTests")
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;


    @Test
    void findAll() {
        Category category1 = new Category(1L, "Electronics");
        Category category2 = new Category(2L, "Books");
        List<Category> categories = Arrays.asList(category1, category2);

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.findAll();

        Assertions.assertEquals(categories, result);
    }

    @Test
    void findById() {
        Category category = new Category(1L, "Electronics");

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        category = categoryService.findById(1L);

        Assertions.assertEquals(1L, category.getId());
        Assertions.assertEquals("Electronics", category.getName());
    }

    @Test
    void findByIdNotFound(){

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Category result = categoryService.findById(1L);

        Assertions.assertNull(result);
    }
}