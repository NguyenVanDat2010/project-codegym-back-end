package com.airbnb.clone.mapper;

import com.airbnb.clone.dto.CommentDto;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.Comment;
import com.airbnb.clone.model.House;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "house", source = "house")
    @Mapping(target = "createdDate",expression ="java(java.time.Instant.now())")
    @Mapping(target = "appUser",source = "appUser")
    Comment map(CommentDto commentDto, House house, AppUser appUser);

    @Mapping(target = "houseId",expression = "java(comment.getHouse().getId())")
    @Mapping(target = "username", expression = "java(comment.getAppUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
