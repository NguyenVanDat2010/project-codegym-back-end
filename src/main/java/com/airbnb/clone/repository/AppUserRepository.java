package com.airbnb.clone.repository;


import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.Reservation;
import com.airbnb.clone.validate.UniqueEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByUsername(String username);

    /**Update user*/
    @Modifying
    @Query(value = "SELECT * FROM app_user WHERE user_id != :userId and (username = :username or email = :email or phone_number = :phoneNumber)", nativeQuery = true)
    List<AppUser> getAllByUsernameAndEmailAndPhoneNumber(@Param("userId") Long id, @Param("username")String username, @Param("email") String email, @Param("phoneNumber") String phoneNumber);

}
