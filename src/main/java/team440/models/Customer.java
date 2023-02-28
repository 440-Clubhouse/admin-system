package team440.models;

import team440.models.Cart;

public class Customer {
    private String name;
    private String email;
    private String address;
    private Cart cart;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }
}
