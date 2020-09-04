package com.airbnb.clone.service;

import com.airbnb.clone.dto.HouseRequest;
import com.airbnb.clone.dto.HouseResponse;
import com.airbnb.clone.exception.*;
import com.airbnb.clone.mapper.HouseMapper;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.City;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.HouseCategory;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.ICityRepository;
import com.airbnb.clone.repository.IHouseCategoryRepository;
import com.airbnb.clone.repository.IHouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private HouseMapper houseMapper;

    public HouseResponse saveHouse(HouseRequest houseRequest){
        HouseCategory houseCategory =houseCategoryRepository.findByName(houseRequest.getHouseCategory())
                .orElseThrow(() -> new HouseCategoryNotFoundException(houseRequest.getHouseCategory()));
        AppUser currentUser = authService.getCurrentUser();
        City city = cityRepository.findByName(houseRequest.getCityName())
                .orElseThrow(() -> new CityNotFoundException(houseRequest.getCityName()));
        House house = houseRepository.save(houseMapper.map(houseRequest,city,
                houseCategory,
                currentUser));
        return houseMapper.mapToDto(house);
    }

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

    public List<HouseResponse> getAllHousesByHouseCategory(Long houseCategoryId ){
        HouseCategory houseCategory = houseCategoryRepository.findById(houseCategoryId)
                .orElseThrow(() -> new HouseCategoryNotFoundException(houseCategoryId.toString()));
        List<House> houses = houseRepository.findAllByHouseCategory(houseCategory);
        return houses.stream().map(houseMapper :: mapToDto).collect(Collectors.toList());
    }

    public List<HouseResponse> getAllHousesByUsername(String username){
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppUserNotFoundException(username));
        return houseRepository.findAllByAppUser(user).stream().map(houseMapper :: mapToDto).collect(Collectors.toList());
    }
}