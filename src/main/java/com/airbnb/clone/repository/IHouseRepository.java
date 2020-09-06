package com.airbnb.clone.repository;

import com.airbnb.clone.dto.SearchRequest;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.City;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.HouseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface IHouseRepository extends JpaRepository<House, Long> {
    List<House> findAllByHouseCategory (HouseCategory houseCategory);
    List<House> findAllByAppUser(AppUser appUser);
//    @Query("SELECT h FROM House h WHERE (:city is null or h.city = :city) and(:houseCategory is " +
//            "null or h.houseCategory = :houseCategory) and h.name LIKE concat('%',:name,'%')")
//    List<House> findAllByCityAndHouseCategoryAndNameContains(@Param("city") City city,
//                                                              @Param("houseCategory") HouseCategory houseCategory,
//                                                             @Param("name") String name);
//    List<House> findAllByCityAndHouseCategoryAndNameContainsAndBathroomsGreaterThanEqualAndSleepingRoomsGreaterThanEqual(
//            City city,
//            @Nullable HouseCategory houseCategory,
//            @Nullable String name,
//            @Nullable Integer bathRooms,
//            @Nullable Integer sleepingRooms
//    );
    @Query(value = "SELECT h FROM House h " +
            "WHERE (:#{#searchRequest.cityId} is null or h.city.id = :#{#searchRequest.cityId})" +
            "AND (:#{#searchRequest.houseCategoryId} IS NULL OR h.houseCategory.id = " +
            ":#{#searchRequest.houseCategoryId})" +
            "AND (:#{#searchRequest.bathrooms} IS NULL " +
            "OR h.bathrooms >= :#{#searchRequest.bathrooms})" +
             "AND (:#{#searchRequest.sleepingRooms} IS NULL OR h.sleepingRooms >= " +
            ":#{#searchRequest.sleepingRooms})" +
            "AND (:#{#searchRequest.price} IS NULL OR h.price <= :#{#searchRequest.price})" +
            "AND (:#{#searchRequest.name} IS NULL OR h.name LIKE :#{#searchRequest.name})" +
            "AND (:#{#searchRequest.address} IS NULL OR h.address LIKE :#{#searchRequest.address})"
    )
    List<House> findAllBySearchRequest(@Param("searchRequest") SearchRequest searchRequest);
}
