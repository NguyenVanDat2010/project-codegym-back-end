package com.airbnb.clone.dto;

public class HouseRequest {
    private Long id;
    private String houseName;
    private String address;
    private String houseCategory;
    private String cityName;
    private String description;
    private Integer price;

    public HouseRequest() {
    }

    public HouseRequest(Long id, String houseName, String houseCategory, String username,
                        String address, String cityName, String description, Integer price) {
        this.id = id;
        this.houseName = houseName;
        this.houseCategory = houseCategory;
        this.address = address;
        this.cityName = cityName;
        this.description = description;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
