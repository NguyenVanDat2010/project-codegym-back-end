package com.airbnb.clone.mapper;


import com.airbnb.clone.dto.HouseRequest;
import com.airbnb.clone.dto.HouseResponse;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.City;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.HouseCategory;
import com.airbnb.clone.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class HouseMapper {
    @Mapping(target = "id", source = "houseRequest.houseId")
    @Mapping(target = "name", source = "houseRequest.houseName")
    @Mapping(target = "address", source = "houseRequest.address")
    @Mapping(target = "appUser", source = "appUser")
    @Mapping(target = "description", source = "houseRequest.description")
    @Mapping(target = "price", source = "houseRequest.price")
    @Mapping(target = "houseCategory", source = "houseCategory")
    @Mapping(target = "city", source = "city")
    public abstract House map(HouseRequest houseRequest, City city, HouseCategory houseCategory, AppUser appUser) ;

    @Mapping(target = "houseName", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "username", source = "appUser.username")
    @Mapping(target = "houseCategory", source = "houseCategory.name")
    @Mapping(target = "cityName", source = "city.name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    public abstract HouseResponse mapToDto(House house);

}
