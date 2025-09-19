//package com.example.Myfirstproject;
//
//import ch.qos.logback.core.net.SyslogOutputStream;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class APIendpoint {
//    public static void main(String[] args) {
//        System.out.println("Hello World");
//    }
//    @GetMapping("API")
//    public String Myclass(){
//
//        return ("Hello");
//
//    }
//
//
//}

package com.example.Myfirstproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIendpoint {

    // 1. Simple Hello API
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello QA World!";
    }

    // 2. API with Path Variable
    @GetMapping("/welcome/{username}")
    public String welcomeUser(@PathVariable String username) {
        return "Welcome, " + username + "!";
    }

    // 3. API with Query Parameter
    @GetMapping("/check-age")
    public String checkAge(@RequestParam int age) {
        if (age >= 18) {
            return "You are an Adult!";
        } else {
            return "You are a Minor!";
        }
    }

    @GetMapping("/product")
    public Product getProduct() {
        return new Product(101, "Laptop", 45000.0);
    }

    // Inner Product class
    static class Product {
        private int id;
        private String name;
        private double price;

        // Constructor
        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        // Getters (needed for JSON serialization)
        public int getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }
    }
}

