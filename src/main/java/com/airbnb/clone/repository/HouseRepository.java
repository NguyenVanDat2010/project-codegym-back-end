package com.airbnb.clone.repository;

import com.airbnb.clone.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
}
