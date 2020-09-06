package com.airbnb.clone.dto;

import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

public class SearchRequest {
    private Long houseCategoryId;
    private Long cityId;
    private String address;
    private String name;
    private int bathrooms;
    private int sleepingRooms;
    private int price;
    private Timestamp startDate;
    private Timestamp endDate;

    public SearchRequest() {
    }

    public SearchRequest(Long houseCategoryId, Long cityId, String address, String name, int bathrooms, int sleepingRooms, int price, Timestamp startDate, Timestamp endDate) {
        this.houseCategoryId = houseCategoryId;
        this.cityId = cityId;
        this.address = address;
        this.name = name;
        this.bathrooms = bathrooms;
        this.sleepingRooms = sleepingRooms;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getHouseCategoryId() {
        return houseCategoryId;
    }

    public void setHouseCategoryId(Long houseCategoryId) {
        this.houseCategoryId = houseCategoryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
