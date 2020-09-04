package com.airbnb.clone.service;

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

import java.util.List;
import java.util.Optional;

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

    public ReservationDto findReservationById(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(id.toString()));
        return reservationMapper.mapToDo(reservation);
    }

    public void remove(Long id) {
        reservationRepository.deleteById(id);
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
        List<Reservation> reservations = reservationRepository.getAllByHouseIdAndStartDateAndEndDate(reservationDto.getHouseId(),reservationDto.getStartDate(), reservationDto.getEndDate());
        if (reservations.size() == 0){
            House house = houseRepository.findById(reservationDto.getHouseId())
                    .orElseThrow(() -> new HouseNotFoundException(reservationDto.getId().toString()));
            AppUser currentUser = authService.getCurrentUser();
            reservationRepository.save(reservationMapper.map(reservationDto, house, currentUser));
            return reservationDto;
        }
        return null;
    }

    /**Update nhà đã đặt*/
    public ReservationDto updateReservation(ReservationDto reservationDto){
        List<Reservation> reservations = reservationRepository.getAllByHouseIdAndStartDateAndEndDateToUpdate(reservationDto.getId(),reservationDto.getHouseId(),reservationDto.getStartDate(), reservationDto.getEndDate());
        if (reservations.size() == 0){
            House house = houseRepository.findById(reservationDto.getHouseId())
                    .orElseThrow(() -> new HouseNotFoundException(reservationDto.getId().toString()));
            AppUser currentUser = authService.getCurrentUser();
            reservationRepository.save(reservationMapper.map(reservationDto, house, currentUser));
            return reservationDto;
        }
        return null;
    }

//    public List<ReservationDto> reservationDtos(Long userId){
//        Optional<AppUser> user = userRepository.findById(userId);
//        House house = houseRepository.findByAppUser(user.get());
//        List<Reservation> reservations = reservationRepository.findAllByHouse(house);
//        if (reservations.size()==0){
//            return null;
//        }
//        return
//    }
}
