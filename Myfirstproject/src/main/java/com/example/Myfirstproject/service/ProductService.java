//// With Database --
//
package com.example.Myfirstproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.Myfirstproject.exception.ResourceNotFoundException;
import com.example.Myfirstproject.model.Product;
import com.example.Myfirstproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        existing.setName(product.getName());
        existing.setQuantity(product.getQuantity());     // ✅ new field
        existing.setAvailable(product.isAvailable());   // ✅ new field

        return productRepository.save(existing);
    }

    public void deleteProduct(int id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        productRepository.delete(existing);
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> filterProductsByPrice(int minPrice, int maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public Product reduceStock(int id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        product.setQuantity(product.getQuantity() - quantity);
        return productRepository.save(product);
    }

    public Product addStock(int id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        product.setQuantity(product.getQuantity() + quantity);
        return productRepository.save(product);
    }


}







// Without Database --

// package com.example.Myfirstproject.service;
//
//import com.example.Myfirstproject.model.Product;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ProductService {
//
//    private List<Product> products = new ArrayList<>();
//
//    public ProductService() {
//        products.add(new Product(101, "Laptop", 45000.0));
//        products.add(new Product(102, "Mobile", 25000.0));
//        products.add(new Product(103, "Headphones", 1500.0));
//    }
//
//    // Get all products
//    public List<Product> getAllProducts() {
//        return products;
//    }
//
//    // Get product by ID
//    public Product getProductById(int id) {
//        return products.stream()
//                .filter(p -> p.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    // Create product
//    public Product createProduct(Product product) {
//        products.add(product);
//        return product;
//    }
//
//    // Update product
//    public Product updateProduct(int id, Product updatedProduct) {
//        Product existing = getProductById(id);
//        if (existing != null) {
//            existing.setName(updatedProduct.getName());
//            existing.setPrice(updatedProduct.getPrice());
//            return existing;
//        }
//        return null;
//    }
//
//    // Delete product
//    public boolean deleteProduct(int id) {
//        Product existing = getProductById(id);
//        if (existing != null) {
//            products.remove(existing);
//            return true;
//        }
//        return false;
//    }
//
//    // Search products by name (case-insensitive)
//    public List<Product> searchProductsByName(String name) {
//        List<Product> result = new ArrayList<>();
//        for (Product p : products) {
//            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
//                result.add(p);
//            }
//        }
//        return result;
//    }
//
//}
//
