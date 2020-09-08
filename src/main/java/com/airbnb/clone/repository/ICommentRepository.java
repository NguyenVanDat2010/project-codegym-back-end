package com.airbnb.clone.repository;

import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.Comment;
import com.airbnb.clone.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByAppUser(AppUser appUser);
    List<Comment> findAllByHouse(House house);

}
