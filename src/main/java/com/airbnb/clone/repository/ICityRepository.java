package com.airbnb.clone.repository;

import com.airbnb.clone.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Long> {
}
