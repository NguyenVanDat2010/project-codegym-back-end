package com.airbnb.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "house_category")
public class HouseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Category is required")
    private String name;

    @ManyToOne
    @JoinColumn(name = "today_price_id", nullable = false)
    @JsonIgnore
    private TodayPrice todayPrice;

    public HouseCategory() {
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

    public TodayPrice getTodayPrice() {
        return todayPrice;
    }

    public void setTodayPrice(TodayPrice todayPrice) {
        this.todayPrice = todayPrice;
    }
}
