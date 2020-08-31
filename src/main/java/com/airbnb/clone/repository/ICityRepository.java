package com.airbnb.clone.repository;

import com.airbnb.clone.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName (String cityName);
}