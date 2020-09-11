package com.airbnb.clone.service;

import com.airbnb.clone.model.HouseCategory;
import com.airbnb.clone.repository.IHouseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HouseCategoryService {
    @Autowired
    private IHouseCategoryRepository houseCategoryRepository;

    public List<HouseCategory> showAllHouseCategories(){
        return houseCategoryRepository.findAll();
    }
}
