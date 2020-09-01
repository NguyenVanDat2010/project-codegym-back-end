package com.airbnb.clone.service;

import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.exception.AppUserNotFoundException;
import com.airbnb.clone.exception.HouseNotFoundException;
import com.airbnb.clone.mapper.ReservationMapper;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.IHouseRepository;
import com.airbnb.clone.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ReservationService {
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

    public void remove(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservationDto> getAllReservationsByUser(String username) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppUserNotFoundException(username.toString()));
        List<Reservation> reservations = reservationRepository.findAllByUser(user);
        return reservations.stream().map(reservationMapper::mapToDo).collect(toList());
    }

    public List<ReservationDto> getAllReservationsByHouse(Long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(houseId.toString()));
        List<Reservation> reservations = reservationRepository.findAllByHouse(house);
        return reservations.stream().map(reservationMapper::mapToDo).collect(toList());
    }

    public void saveReservation(ReservationDto reservationDto){
        House house = houseRepository.findById(reservationDto.getHouseId())
                .orElseThrow(() -> new HouseNotFoundException(reservationDto.getId().toString()));
        AppUser currentUser = authService.getCurrentUser();
        reservationRepository.save(reservationMapper.map(reservationDto,house,currentUser));
    }
}
