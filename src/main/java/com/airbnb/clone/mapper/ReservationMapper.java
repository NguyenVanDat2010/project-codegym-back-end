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
    @Mapping(target = "start_date", source ="reservationDto.start_date" )
    @Mapping(target = "end_date", source ="reservationDto.end_date" )
    @Mapping(target = "house", source ="house" )
    @Mapping(target = "user", source ="user" )
    public abstract Reservation map(ReservationDto reservationDto, House house, AppUser user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "start_date", source = "start_date")
    @Mapping(target = "end_date", source = "end_date")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "houseId", source = "house.id")
    public abstract ReservationDto mapToDo(Reservation reservation);
}
