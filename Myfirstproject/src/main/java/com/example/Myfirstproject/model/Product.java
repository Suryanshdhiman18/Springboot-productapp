// With Database

package com.example.Myfirstproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Product name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Min(value = 1, message = "Price must be at least 1")
    private double price;

//    @Min(value = 1, message = "Quantity must be atleast 1")
//    private int quantity;

    public Product() {}

    // constructors
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

//    public int getQuantity() { return quantity; }
//    public void setQuantity(int quantity) { this.quantity = quantity; }
}

// Without Database --

//package com.example.Myfirstproject.model;
//
//public class Product {
//    private int id;
//    private String name;
//    private double price;
//
//    public Product() {} // needed for @RequestBody
//
//    public Product(int id, String name, double price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
//
//    public int getId() { return id; }
//    public String getName() { return name; }
//    public double getPrice() { return price; }
//
//    public void setId(int id) { this.id = id; }
//    public void setName(String name) { this.name = name; }
//    public void setPrice(double price) { this.price = price; }
//}




