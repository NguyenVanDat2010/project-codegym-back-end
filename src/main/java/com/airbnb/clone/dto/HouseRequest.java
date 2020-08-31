package com.airbnb.clone.dto;

public class HouseRequest {
    private Long houseId;
    private String houseName;
    private String houseCategory;
    private String username;
    private String cityName;
    private String description;
    private Long price;

    public HouseRequest() {
    }

    public HouseRequest(Long houseId, String houseName, String houseCategory, String username, String cityName, String description) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.houseCategory = houseCategory;
        this.username = username;
        this.cityName = cityName;
        this.description = description;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
