package com.airbnb.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Address is required")
    private String address;
    //Xác định là chủ nhà

    @Column(nullable = false)
    @NotEmpty(message = "Description is required")
    @Lob
    private String description;

    @Column(nullable = false)
    @Min(1)
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "house_category_id",nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private HouseCategory houseCategory;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private City city;

    public House() {
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public HouseCategory getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(HouseCategory houseCategory) {
        this.houseCategory = houseCategory;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
