package com.airbnb.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date",nullable = false)
    @NotEmpty(message = "Start date is required")
    private String start_date;
    @Column(name = "end_date",nullable = false)
    @NotEmpty(message = "End date is required")
    private String end_date;

    //Xác định khách hàng thuê nhà
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

//    @ManyToOne
//    @JoinColumn(name = "today_price_id", nullable = false)
//    @JsonIgnore
//    private TodayPrice todayPrice;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

//    public TodayPrice getTodayPrice() {
//        return todayPrice;
//    }
//
//    public void setTodayPrice(TodayPrice todayPrice) {
//        this.todayPrice = todayPrice;
//    }
}
