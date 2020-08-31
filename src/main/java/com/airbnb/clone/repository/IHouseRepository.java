package com.airbnb.clone.repository;

import com.airbnb.clone.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHouseRepository extends JpaRepository<House,Long> {
}
