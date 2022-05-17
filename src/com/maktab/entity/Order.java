package com.maktab.entity;

public class Order {
    private Product[] products = new Product[5];

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {

        this.products = products;
    }
}
