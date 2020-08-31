package com.airbnb.clone.repository;

import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(AppUser user);
    List<Reservation> findAllByHouse(House house);
}
