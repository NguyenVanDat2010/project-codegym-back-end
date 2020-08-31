//package com.airbnb.clone.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//
//@Entity
//@Table(name = "today_price")
//public class TodayPrice {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false)
//    @NotEmpty(message = "Price cannot is required")
//    private String price;
//    @Column(name = "available_rooms",nullable = false)
//    private boolean availableRooms;
//    @NotEmpty(message = "Date is required")
//    @Column(nullable = false)
//    private String date;
//
//    public TodayPrice() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public boolean isAvailableRooms() {
//        return availableRooms;
//    }
//
//    public void setAvailableRooms(boolean availableRooms) {
//        this.availableRooms = availableRooms;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//}
