package com.airbnb.clone.repository;

import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.HouseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHouseRepository extends JpaRepository<House,Long> {
    List<House> findAllByHouseCategory (HouseCategory houseCategory);

    List<House> findByAppUser(AppUser appUser);
}
