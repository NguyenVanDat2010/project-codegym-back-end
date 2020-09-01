package com.airbnb.clone.repository;

import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(AppUser user);

    List<Reservation> findAllByHouse(House house);

//    @Modifying
//    @Query(value = "SELECT * FROM reservation WHERE house_id=? NOT BETWEEN :start_date AND :end_date", nativeQuery = true)
//    public List<EntityClassTable> getAllBetweenDates(@Param("startDate")String startDate, @Param("endDate")Date endDate);


    Reservation findReservationByHouseBetween(House house, String startDate, String endDate);

}

