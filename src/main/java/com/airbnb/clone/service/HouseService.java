package com.airbnb.clone.service;

import com.airbnb.clone.controller.ReservationController;
import com.airbnb.clone.dto.HouseRequest;
import com.airbnb.clone.dto.HouseResponse;
import com.airbnb.clone.dto.SearchRequest;
import com.airbnb.clone.exception.*;
import com.airbnb.clone.mapper.HouseMapper;
import com.airbnb.clone.model.*;
import com.airbnb.clone.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HouseService {

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private IHouseCategoryRepository houseCategoryRepository;

    @Autowired
    private IHouseRepository houseRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ICommentRepository commentRepository;

    public HouseResponse saveHouse(HouseRequest houseRequest){
        HouseCategory houseCategory =
                houseCategoryRepository.findById(houseRequest.getHouseCategory())
                .orElseThrow(() -> new HouseCategoryNotFoundException(houseRequest.getHouseCategory().toString()));
        AppUser currentUser = authService.getCurrentUser();
        City city = cityRepository.findById(houseRequest.getCity())
                .orElseThrow(() -> new CityNotFoundException(houseRequest.getCity().toString()));
        House house = houseRepository.save(houseMapper.map(houseRequest,city,
                houseCategory,
                currentUser));
        return houseMapper.mapToDto(house);
    }

    @Transactional(readOnly = true)
    public HouseResponse getHouse(Long id){
        House house =houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException(id.toString()));
        return houseMapper.mapToDto(house);
    }


    public List<HouseResponse> getAllHouses(){
        return houseRepository.findAll()
                .stream()
                .map(houseMapper :: mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HouseResponse> getAllHousesByHouseCategory(Long houseCategoryId ){
        HouseCategory houseCategory = houseCategoryRepository.findById(houseCategoryId)
                .orElseThrow(() -> new HouseCategoryNotFoundException(houseCategoryId.toString()));
        List<House> houses = houseRepository.findAllByHouseCategory(houseCategory);
        return houses.stream().map(houseMapper :: mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HouseResponse> getAllHousesByUsername(String username){
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppUserNotFoundException(username));

        return houseRepository.findAllByAppUser(user).stream().map(houseMapper :: mapToDto).collect(Collectors.toList());
    }

    public void deleteById(Long id){
        Optional<House> house = houseRepository.findById(id);
        if (house.isPresent()) {
            List<Reservation> reservations = reservationRepository.findAllByHouse(house.get());
            for (Reservation reservation : reservations) {
                reservationRepository.deleteById(reservation.getId());
            }
            List<ImageModel> imageModels = imageRepository.findAllByHouse(house.get());
            for (ImageModel imageModel : imageModels) {
                imageRepository.deleteById(imageModel.getId());
            }
            List<Comment> comments = commentRepository.findAllByHouse(house.get());
            for (Comment comment : comments) {
                commentRepository.deleteById(comment.getId());
            }
            houseRepository.deleteById(id);
        }
    }
    @Transactional(readOnly = true)
    public List<HouseResponse> getAllAvailableHouse(SearchRequest searchRequest) {
        List<House> houses = houseRepository
                .findAllBySearchRequest(searchRequest)
                .stream()
                .filter(
                        house -> {
                            if (searchRequest.getStartDate() != null && searchRequest.getEndDate() != null) {
                                return reservationRepository
                                        .getAllConflictingReservations(house.getId(), searchRequest.getStartDate(),
                                                searchRequest.getEndDate())
                                        .size() == 0;
                            }
                            return true;
                        }
                )
                .collect(Collectors.toList());
        return houses.stream().map(houseMapper::mapToDto).collect(Collectors.toList());
    }
}