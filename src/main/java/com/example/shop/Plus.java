package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("plus")
@ConfigurationProperties(prefix = "profile-plus")
public class Plus {
    private final List<Product> cart;

    @Value("${profile-plus.vat}")
    private double vat;

    @Autowired
    public Plus() {
        cart = new ArrayList<>();
        cart.add(new Product("Mleko", 3.5));
        cart.add(new Product("Chleb", 4.50));
        cart.add(new Product("Jajka", 9.00));
        cart.add(new Product("Pomidor", 3.50));
        cart.add(new Product("Salami", 10.00));
        cart.add(new Product("Ser", 11.99));
    }

    public void printProducts(){
        cart.forEach(System.out::println);
    }

    public void printTotalValue() {
        final Double totalPrice = cart
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total: " + totalPrice);
        System.out.println("Totl + VAT: " + totalPrice * vat);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printCart() {
        System.out.println("Profile Plus");
        printProducts();
        printTotalValue();
    }
}