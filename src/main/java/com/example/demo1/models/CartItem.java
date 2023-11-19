package com.example.demo1.models;

public class CartItem {
    public Product product;

    public int amount;

    public CartItem(Product prod, int amount) {
        product = prod.Clone();
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}
