package com.lida.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by DuLida on 2016/10/20.
 */
public class Address {

    private String city;
    private String street;
    private int num;

    public Address() {
    }

    public Address(String city, String street, int num) {
        this.city = city;
        this.street = street;
        this.num = num;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
