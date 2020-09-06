package com.airbnb.clone.dto;

public class HouseRequest {
    private Long id;
    private String name;
    private String address;
    private String houseCategory;
    private String cityName;
    private String description;
    private int bathrooms;
    private int sleepingRooms;
    private int price;

    public HouseRequest() {
    }

    public HouseRequest(Long id, String name, String address, String houseCategory, String cityName, String description, int bathrooms, int sleepingRooms, int price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.houseCategory = houseCategory;
        this.cityName = cityName;
        this.description = description;
        this.bathrooms = bathrooms;
        this.sleepingRooms = sleepingRooms;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSleepingRooms() {
        return sleepingRooms;
    }

    public void setSleepingRooms(int sleepingRooms) {
        this.sleepingRooms = sleepingRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
