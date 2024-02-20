package com.studies.course.services;

import com.studies.course.entities.Product;
import com.studies.course.repositories.ProductRepository;
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

import static org.junit.jupiter.api.Assertions.*;
@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void findAll() {
        Product p1 = new Product(1L, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(2L, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        List<Product> products = Arrays.asList(p1, p2);

        Mockito.when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();
        Assertions.assertEquals(products, result);
    }

    @Test
    void findById() {
        Product p1 = new Product(1L, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(p1));

        p1 = productService.findById(1L);
        Assertions.assertEquals(1L, p1.getId());
        Assertions.assertEquals("The Lord of the Rings", p1.getName());
        Assertions.assertEquals(90.5, p1.getPrice());
    }

    @Test
    void findByIdNotFound(){
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product p1 = productService.findById(1L);

        Assertions.assertNull(p1);
    }
}