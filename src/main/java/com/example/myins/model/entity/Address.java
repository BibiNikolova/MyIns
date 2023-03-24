package com.example.myins.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int streetNumber;
    private int block;
    private String entrance;
    private int floor;
    private int apartmentNumber;

    public Address(String city, String street, int streetNumber, int block, String entrance, int floor, int apartmentNumber) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.block = block;
        this.entrance = entrance;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public Address setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public int getBlock() {
        return block;
    }

    public Address setBlock(int block) {
        this.block = block;
        return this;
    }

    public String getEntrance() {
        return entrance;
    }

    public Address setEntrance(String entrance) {
        this.entrance = entrance;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public Address setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public Address setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }
}
