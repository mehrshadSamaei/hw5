package com.maktab.entity;

public class Address {
    private int id;
    private String state;
    private String city;
    private String street;
    private String postalCode;
    private int userId;

    public Address(int i, int id, String state, String city, String street, String postalCode) {
    }

    public Address(int id, String state, String city, String street, String postalCode, int userId) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
