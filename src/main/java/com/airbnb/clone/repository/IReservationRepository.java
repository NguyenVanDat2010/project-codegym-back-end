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
    /**Là khách hàng kiểm tra mình dang thuê những nhà nào*/
    List<Reservation> findAllByUser(AppUser user);

    /**Là chủ nhà, lấy ra những khách hàng đang thuê nhà của mình*/
    List<Reservation> findAllByHouse(House house);

    void findByUser(AppUser user);

    void findByHouse(House house);

    /**Đặt nhà*/
    @Modifying
    @Query(value = "SELECT * FROM reservation WHERE house_id= :houseId " +
            "and ((start_date between :startDate and :endDate )" +
            "or (end_date between :startDate and :endDate )" +
            "or (start_date <= :startDate and end_date >= :endDate )" +
            "or (start_date >= :startDate and end_date <= :endDate ))", nativeQuery = true)
    List<Reservation> getAllConflictingReservations(@Param("houseId")Long house_id ,
                             @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    /**Update nhà đã đặt*/
    @Modifying
    @Query(value = "SELECT * FROM reservation WHERE id != :reservationId and house_id= :houseId " +
            "and ((start_date between :startDate and :endDate )" +
            "or (end_date between :startDate and :endDate )" +
            "or (start_date <= :startDate and end_date >= :endDate )" +
            "or (start_date >= :startDate and end_date <= :endDate ))", nativeQuery = true)
    List<Reservation> getAllConflictingReservationsWhenEdit(@Param("reservationId")Long reservationId,
                        @Param("houseId")Long house_id ,@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

//    @Modifying
//    @Query(value = "select * from reservation where user_id = :userId", nativeQuery = true)
//    List<Reservation> getAllByUserOwnedHouse(@Param("userId")Long userId);
}

