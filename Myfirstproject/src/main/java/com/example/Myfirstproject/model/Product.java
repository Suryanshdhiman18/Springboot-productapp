// With Database

package com.example.Myfirstproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int id;

    private String name;

    private double price;

    // constructors
    public Product() {}
    public Product(String name, double price) {
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




