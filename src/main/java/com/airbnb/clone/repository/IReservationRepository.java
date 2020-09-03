package com.airbnb.clone.repository;

import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(AppUser user);

    List<Reservation> findAllByHouse(House house);

    @Modifying
    @Query(value = "SELECT * FROM reservation WHERE house_id= :houseId " +
            "and ((start_date between :startDate and :endDate )" +
            "or (end_date between :startDate and :endDate )" +
            "or (start_date <= :startDate and end_date >= :endDate )" +
            "or (start_date >= :startDate and end_date <= :endDate ))", nativeQuery = true)
    List<Reservation> getAllByHouseIdAndStartDateAndEndDate(@Param("houseId")Long house_id ,@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    @Modifying
    @Query(value = "SELECT * FROM reservation WHERE id != :reservationId and house_id= :houseId " +
            "and ((start_date between :startDate and :endDate )" +
            "or (end_date between :startDate and :endDate )" +
            "or (start_date <= :startDate and end_date >= :endDate )" +
            "or (start_date >= :startDate and end_date <= :endDate ))", nativeQuery = true)
    List<Reservation> getAllByHouseIdAndStartDateAndEndDateToUpdate(@Param("reservationId")Long reservationId, @Param("houseId")Long house_id ,@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}

