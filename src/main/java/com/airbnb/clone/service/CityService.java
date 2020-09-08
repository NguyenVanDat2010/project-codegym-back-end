package com.airbnb.clone.service;


import com.airbnb.clone.model.City;
import com.airbnb.clone.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private ICityRepository cityRepository;

    public List<City> showAllCity(){
        return cityRepository.findAll();
    }
}
