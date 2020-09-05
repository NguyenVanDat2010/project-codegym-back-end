package com.airbnb.clone.controller;

import com.airbnb.clone.dto.HouseRequest;
import com.airbnb.clone.dto.HouseResponse;
import com.airbnb.clone.dto.SearchRequest;
import com.airbnb.clone.model.City;
import com.airbnb.clone.model.HouseCategory;
import com.airbnb.clone.service.CityService;
import com.airbnb.clone.service.HouseCategoryService;
import com.airbnb.clone.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/houses")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @Autowired
    private CityService cityService;

    @Autowired
    private HouseCategoryService houseCategoryService;


    @PostMapping
    public ResponseEntity<HouseResponse> createHouse(@RequestBody HouseRequest houseRequest){
        return new ResponseEntity<>(houseService.saveHouse(houseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseResponse> getHouse(@PathVariable Long id){
        return new ResponseEntity<>(houseService.getHouse(id), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<HouseResponse>> findHouse(SearchRequest searchRequest) {
        return new ResponseEntity<>(houseService.getAllAvailableHouse(searchRequest), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<HouseResponse>> getAllHouses(){
        return new ResponseEntity<>(houseService.getAllHouses(),HttpStatus.OK);

    }

    @GetMapping("/by-house-category/{id}")
    public ResponseEntity<List<HouseResponse>> getHousesByHouseCategory(@PathVariable Long id) {
        return new ResponseEntity<>(houseService.getAllHousesByHouseCategory(id),HttpStatus.OK);
    }

    @GetMapping("/by-user/{name}")
    public ResponseEntity<List<HouseResponse>> getHousesByAppUser(@PathVariable String name) {
        return new ResponseEntity<>(houseService.getAllHousesByUsername(name),HttpStatus.OK);
    }
    @GetMapping("/show-all-city")
    public ResponseEntity<List<City>> getAllCities(){
        return new ResponseEntity<>(cityService.showAllCity(),HttpStatus.OK);
    }

    @GetMapping("/show-all-house-category")
    public ResponseEntity<List<HouseCategory>> getAllHousesCategory(){
        return new ResponseEntity<>(houseCategoryService.showAllHouseCategories(),HttpStatus.OK);
    }
}