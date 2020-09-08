package com.airbnb.clone.repository;

import com.airbnb.clone.model.HouseCategory;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface IHouseCategoryRepository extends JpaRepository<HouseCategory,Long> {
    Optional<HouseCategory> findByName(String houseCategoryName);
}
