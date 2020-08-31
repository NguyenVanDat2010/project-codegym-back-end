package com.airbnb.clone.service;

import com.airbnb.clone.dto.HouseRequest;
import com.airbnb.clone.exception.HouseRepositoryNotFoundException;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.HouseCategory;
import com.airbnb.clone.repository.ICityRepository;
import com.airbnb.clone.repository.IHouseCategoryRepository;
import com.airbnb.clone.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    @Autowired
    private ICityRepository districtRepository;

    @Autowired
    private IHouseCategoryRepository houseCategoryRepository;

    @Autowired
    private IHouseRepository houseRepository;

    public void saveHouse(HouseRequest houseRequest){
        HouseCategory houseCategory =houseCategoryRepository.findByName(houseRequest.getHouseCategory())
                .orElseThrow(() -> new HouseRepositoryNotFoundException(houseRequest.getHouseCategory()));


    }
}
