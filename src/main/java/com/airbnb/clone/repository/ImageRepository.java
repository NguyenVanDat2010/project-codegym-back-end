package com.airbnb.clone.repository;

import com.airbnb.clone.model.House;
import com.airbnb.clone.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    List<ImageModel> findAllByHouse(House house);
}
