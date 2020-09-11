package com.airbnb.clone.controller;

import com.airbnb.clone.dto.MessageResponse;
import com.airbnb.clone.dto.ReservationDto;
import com.airbnb.clone.dto.ResponseMessage;
import com.airbnb.clone.exception.AppException;
import com.airbnb.clone.service.AppUserService;
import com.airbnb.clone.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AppUserService userService;

    /**Là khách hàng kiểm tra mình dang thuê những nhà nào*/
    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<ReservationDto>>getAllReservationsByUser(@PathVariable String username){
        return new ResponseEntity<>(reservationService.getAllReservationsByUser(username), HttpStatus.OK);
    }
    /**Là chủ nhà, lấy ra những khách hàng đang thuê nhà của mình*/
    @GetMapping("/by-house/{houseId}")
    public ResponseEntity<List<ReservationDto>>getAllReservationsByHouse(@PathVariable Long houseId) {
        return new ResponseEntity<>(reservationService.getAllReservationsByHouse(houseId), HttpStatus.OK);
    }

    /**Đặt nhà*/
    @PostMapping
    public ResponseEntity<ResponseMessage> reservationHouseByCurrentUser(@RequestBody ReservationDto reservationDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ReservationDto reservationDto1 = reservationService.saveReservation(reservationDto);
        return new ResponseEntity<>(new ResponseMessage("Reservation successed!"),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> updateReservationForm(@PathVariable Long id){
        ReservationDto reservationDto = reservationService.findReservationById(id);
        if (reservationDto == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Get reservation failed!"));
        }
        return new ResponseEntity<>(reservationDto,HttpStatus.OK);
    }

    /**Update nhà đã đặt*/
    @PutMapping
    public ResponseEntity<?> updateReservationByIdAndCurrentUser(@RequestBody ReservationDto reservationDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(new MessageResponse("Update reservation failed!"));
        }
        ReservationDto reservationDto1 = reservationService.updateReservation(reservationDto);
        if (reservationDto1 == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Update reservation failed!"));
        }
        return new ResponseEntity<>(new MessageResponse("Update reservation succeed!"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteReservation(@PathVariable Long id){
        Boolean isDeleted = reservationService.deleteById(id);
        if (!isDeleted){
            return ResponseEntity.badRequest().body(new MessageResponse("Delete error for this reservation!"));
        }
        return new ResponseEntity<>(new MessageResponse("Delete reservation succeed!"), HttpStatus.OK);
    }

}
