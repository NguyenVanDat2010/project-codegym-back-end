package com.airbnb.clone.controller;

import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.Reservation;
import com.airbnb.clone.service.AppUserService;
import com.airbnb.clone.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AppUserService userService;

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<List<ReservationDto>>getAllReservationsByUser(@PathVariable String username){
        return new ResponseEntity<>(reservationService.getAllReservationsByUser(username), HttpStatus.OK);
    }

    @GetMapping("/byHouse/{houseId}")
    public ResponseEntity<Iterable<ReservationDto>>getAllReservationsByHouse(@PathVariable Long houseId) {
        return new ResponseEntity<>(reservationService.getAllReservationsByHouse(houseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> reservationHouseByCurrentUser(@RequestBody ReservationDto reservationDto){
        reservationService.saveReservation(reservationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
