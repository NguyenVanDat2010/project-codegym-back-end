package com.airbnb.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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
    @Column
    private String image;

    @Transient
    private MultipartFile imageFile;

    //Xác định là chủ nhà
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "house_category_id",nullable = false)
    @JsonIgnore
    private HouseCategory houseCategory;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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
}
