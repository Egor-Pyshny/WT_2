package com.example.demo1.models;

import java.util.Base64;

public class Product {
    private int id;

    private String productName;

    private String price;

    private String category;

    private String image;

    public boolean inCart;

    public Product(int id, String productName, String price, String category, byte[] image) {
        this.setId(id);
        this.setProductName(productName);
        this.setPrice(price);
        this.setCategory(category);
        this.setImage(Base64.getEncoder().encodeToString(image));
        this.inCart = false;
    }

    public Product(int id, String productName, String price, String category, String image) {
        this.setId(id);
        this.setProductName(productName);
        this.setPrice(price);
        this.setCategory(category);
        this.setImage(image);
    }

    public Product Clone() {
        return new Product(getId(), getProductName(), getPrice(), getCategory(), getImage());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /** The name of the product. */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /** The price of the product. */
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /** The category of the product. */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /** The image of the product in Base64 format. */
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
