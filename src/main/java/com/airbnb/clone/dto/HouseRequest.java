package com.airbnb.clone.dto;

public class HouseRequest {
    private Long id;
    private String name;
    private String address;
    private String houseCategory;
    private String cityName;
    private String description;
    private Integer bathrooms;
    private Integer sleepingRooms;
    private Integer price;

    public HouseRequest() {
    }

    public HouseRequest(Long id, String name, String houseCategory,
                        String address, String cityName, String description, Integer bathrooms, Integer sleepingRooms, Integer price) {
        this.id = id;
        this.name = name;
        this.houseCategory = houseCategory;
        this.address = address;
        this.cityName = cityName;
        this.description = description;
        this.bathrooms = bathrooms;
        this.sleepingRooms = sleepingRooms;
        this.price = price;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
}
