package com.airbnb.clone.dto;

public class HouseRequest {
    private Long id;
    private String name;
    private String address;
    private Long houseCategory;
    private Long city;
    private String description;
    private Integer bathrooms;
    private Integer sleepingRooms;
    private Integer price;

    public HouseRequest() {
    }

    public HouseRequest(Long id, String name, String address, Long houseCategory, Long city, String description, Integer bathrooms, Integer sleepingRooms, Integer price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.houseCategory = houseCategory;
        this.city = city;
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

    public Long getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(Long houseCategory) {
        this.houseCategory = houseCategory;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getSleepingRooms() {
        return sleepingRooms;
    }

    public void setSleepingRooms(Integer sleepingRooms) {
        this.sleepingRooms = sleepingRooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
