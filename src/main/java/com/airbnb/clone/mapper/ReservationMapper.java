package com.airbnb.clone.mapper;

import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public abstract class ReservationMapper {
    @Mapping(target = "id", source ="reservationDto.id" )
    @Mapping(target = "startDate", source ="reservationDto.startDate" )
    @Mapping(target = "endDate", source ="reservationDto.endDate" )
    @Mapping(target = "house", source ="house" )
    @Mapping(target = "user", source ="user" )
    public abstract Reservation map(ReservationDto reservationDto, House house, AppUser user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "houseId", source = "house.id")
    public abstract ReservationDto mapToDo(Reservation reservation);
}
