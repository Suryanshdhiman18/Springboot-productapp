package com.example.Myfirstproject.controller;

import com.example.Myfirstproject.model.Product;
import com.example.Myfirstproject.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Constructor Injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET all
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) return ResponseEntity.ok(product);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Product with ID " + id + " not found"));
    }

    // POST create
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Product with ID " + id + " not found"));
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Product with ID " + id + " not found"));
    }

    // Search products by name
//    @GetMapping("/search")
//    public List<Product> searchProducts(@RequestParam String name) {
//        return productService.searchProductsByName(name);
//    }

    // Inner ErrorResponse
    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

}
