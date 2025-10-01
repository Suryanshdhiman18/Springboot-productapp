package com.example.Myfirstproject.controller;

import com.example.Myfirstproject.model.Product;
import com.example.Myfirstproject.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id); // exception handled globally
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product); // validation + service logic
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product); // no null check needed
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id); // exception if not found
    }

//    @GetMapping
//    public List<Product> getProducts(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return productService.getProducts(PageRequest.of(page, size, Sort.by("id").ascending())).getContent();
//    }

    @GetMapping
    public List<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return productService.getProducts(PageRequest.of(page, size, sort)).getContent();
    }

    @GetMapping("/products")
    public List<Product> filterProducts(
            @RequestParam int minPrice,
            @RequestParam int maxPrice) {
        return productService.filterProductsByPrice(minPrice, maxPrice);
    }

    @PutMapping("/{id}/reduce")
    public ResponseEntity<?> reduceStock(@PathVariable int id, @RequestParam int quantity) {
        return ResponseEntity.ok(productService.reduceStock(id, quantity));
    }

    @PutMapping("/{id}/add")
    public ResponseEntity<?> addStock(@PathVariable int id, @RequestParam int quantity) {
        return ResponseEntity.ok(productService.addStock(id, quantity));
    }

}

