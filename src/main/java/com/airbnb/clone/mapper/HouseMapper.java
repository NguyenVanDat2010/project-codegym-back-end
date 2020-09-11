package com.airbnb.clone.mapper;


import com.airbnb.clone.dto.HouseRequest;
import com.airbnb.clone.dto.HouseResponse;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.City;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.HouseCategory;
import com.airbnb.clone.service.AuthService;
import com.airbnb.clone.service.CommentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class HouseMapper {
    @Autowired
    private CommentService commentService;

    @Mapping(target = "id", source = "houseRequest.id")
    @Mapping(target = "name", source = "houseRequest.name")
    @Mapping(target = "address", source = "houseRequest.address")
    @Mapping(target = "appUser", source = "appUser")
    @Mapping(target = "description", source = "houseRequest.description")
    @Mapping(target = "price", source = "houseRequest.price")
    @Mapping(target = "houseCategory", source = "houseCategory")
    @Mapping(target = "bathrooms", source = "houseRequest.bathrooms")
    @Mapping(target = "sleepingRooms", source = "houseRequest.sleepingRooms")
    @Mapping(target = "city", source = "city")
    public abstract House map(HouseRequest houseRequest, City city, HouseCategory houseCategory, AppUser appUser) ;

    @Mapping(target = "username", source = "appUser.username")
    @Mapping(target = "houseCategory", source = "houseCategory.name")
    @Mapping(target = "rating", expression = "java(ratingCount(house))")
    @Mapping(target = "city", source = "city.name")
    public abstract HouseResponse mapToDto(House house);

    float ratingCount(House house){
        return commentService.getRatingForHouse(house);
    }
}
