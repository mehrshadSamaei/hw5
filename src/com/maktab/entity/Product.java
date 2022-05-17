package com.maktab.entity;

public class Product {
    private int id;
    private String title;
    private int counter;
    private double price;
    private int addOrder;
    private int removeOrder;
    private int productInOrder;


    public int getProductInOrder() {
        return productInOrder;
    }

    public int getAddOrder() {
        return addOrder;
    }

    public void setAddOrder(int addOrder) {

        if (this.counter != 0)
            this.counter = counter - 1;

        if (addOrder == 5)
            this.addOrder = addOrder;
        else
            this.addOrder = addOrder + 1;
    }

    public int getRemoveOrder() {
        return removeOrder;
    }

    public void setRemove(int removeOrder) {
        this.counter = counter + 1;
        if (removeOrder == 0)
            this.removeOrder = removeOrder;
        else
            this.removeOrder = removeOrder - 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
