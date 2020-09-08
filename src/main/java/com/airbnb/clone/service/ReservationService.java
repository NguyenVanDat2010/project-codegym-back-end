package com.airbnb.clone.service;

import com.airbnb.clone.dto.MessageResponse;
import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.exception.AppUserNotFoundException;
import com.airbnb.clone.exception.HouseNotFoundException;
import com.airbnb.clone.exception.ReservationNotFoundException;
import com.airbnb.clone.mapper.ReservationMapper;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.IHouseRepository;
import com.airbnb.clone.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ReservationService {
    private final Integer HOURS_OF_A_DAY = 24;
    private final Integer HOURS_OF_THREE_DAY = 72;

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private IHouseRepository houseRepository;

    @Autowired
    private AuthService authService;

    public ReservationDto findReservationById(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(id.toString()));
        return reservationMapper.mapToDo(reservation);
    }

    public Boolean deleteById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id.toString()));
        if (reservation != null){
            AppUser currentUser = authService.getCurrentUser();
            House house = houseRepository.findById(reservation.getHouse().getId())
                    .orElseThrow(() -> new HouseNotFoundException(reservation.getHouse().getId().toString()));
            if (currentUser.getUserId().equals(house.getAppUser().getUserId())){
                reservationRepository.deleteById(id);
                return true;
            }else {
                Timestamp getTimestampNow = new Timestamp(System.currentTimeMillis());
                long createdDateTime = reservation.getCreatedAt().getTime();
                long startDateTime = reservation.getStartDate().getTime();
                long dateTimeNow = getTimestampNow.getTime();
                long millisecondsByCreatedDate = dateTimeNow - createdDateTime;
                long millisecondsByStartDate = startDateTime - dateTimeNow;

                int secondsByCreatedDate = (int) millisecondsByCreatedDate / 1000;
                int secondsByStartDate = (int) millisecondsByStartDate / 1000;
                int hoursByCreatedDate = secondsByCreatedDate / 3600;
                int hoursByStartDate = secondsByStartDate / 3600;
//            int minutes = (secondsByCreatedDate % 3600) / 60;
//            seconds = (secondsByCreatedDate % 3600) % 60;

                if (hoursByCreatedDate <= HOURS_OF_THREE_DAY && hoursByStartDate > HOURS_OF_A_DAY) {
                    reservationRepository.deleteById(id);
                    return true;
                }
            }
        }
        return false;
    }

    /**Là khách hàng kiểm tra mình dang thuê những nhà nào*/
    public List<ReservationDto> getAllReservationsByUser(String username) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppUserNotFoundException(username.toString()));
        List<Reservation> reservations = reservationRepository.findAllByUser(user);
        return reservations.stream().map(reservationMapper::mapToDo).collect(toList());
    }

    /**Là chủ nhà, lấy ra những khách hàng đang thuê nhà của mình*/
    public List<ReservationDto> getAllReservationsByHouse(Long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(houseId.toString()));
        List<Reservation> reservations = reservationRepository.findAllByHouse(house);
        return reservations.stream().map(reservationMapper::mapToDo).collect(toList());
    }

    /**Đặt nhà*/
    public ReservationDto saveReservation(ReservationDto reservationDto){
        List<Reservation> reservations = reservationRepository.getAllConflictingReservations(reservationDto.getHouseId(),
                reservationDto.getStartDate(), reservationDto.getEndDate());
        if (reservations.size() == 0){
            House house = houseRepository.findById(reservationDto.getHouseId())
                    .orElseThrow(() -> new HouseNotFoundException(reservationDto.getId().toString()));
            AppUser currentUser = authService.getCurrentUser();
            if (house.getAppUser().getUserId().equals(currentUser.getUserId())){
                return null;
            }
                reservationRepository.save(reservationMapper.map(reservationDto, house, currentUser));
            return reservationDto;
        }
        return null;
    }

    /**Update nhà đã đặt*/
    public ReservationDto updateReservation(ReservationDto reservationDto){
        List<Reservation> reservations = reservationRepository.getAllConflictingReservationsWhenEdit(reservationDto.getId(),
                reservationDto.getHouseId(),reservationDto.getStartDate(), reservationDto.getEndDate());
        if (reservations.size() == 0){
            House house = houseRepository.findById(reservationDto.getHouseId())
                    .orElseThrow(() -> new HouseNotFoundException(reservationDto.getId().toString()));
            AppUser currentUser = authService.getCurrentUser();
            reservationRepository.save(reservationMapper.map(reservationDto, house, currentUser));
            return reservationDto;
        }
        return null;
    }
}
