package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("start")
public class Start {
    private final List<Product> cart;
    @Autowired
    public Start() {
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
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printCart() {
        System.out.println("Profile Start");
        printProducts();
        printTotalValue();
    }
}
