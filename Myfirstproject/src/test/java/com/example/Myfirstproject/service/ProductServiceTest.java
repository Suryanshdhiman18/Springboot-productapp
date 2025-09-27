package com.example.Myfirstproject.service;

import com.example.Myfirstproject.model.Product;
import com.example.Myfirstproject.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1000);

        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.createProduct(product);

        assertNotNull(saved);
        assertEquals("Laptop", saved.getName());
        assertEquals(1000, saved.getPrice());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1);
        product.setName("Phone");
        product.setPrice(500);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product found = productService.getProductById(1);

        assertNotNull(found);
        assertEquals("Phone", found.getName());
        assertEquals(500, found.getPrice());

        verify(productRepository, times(1)).findById(1);
    }
}
