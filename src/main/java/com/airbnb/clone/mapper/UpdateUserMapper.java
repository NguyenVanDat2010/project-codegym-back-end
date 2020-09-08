package com.airbnb.clone.mapper;

import com.airbnb.clone.dto.UpdateUserRequest;
import com.airbnb.clone.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring")
public abstract class UpdateUserMapper {
    @Mapping(target = "userId", source ="updateUserRequest.id" )
    @Mapping(target = "firstName", source ="updateUserRequest.firstName" )
    @Mapping(target = "lastName", source ="updateUserRequest.lastName" )
    @Mapping(target = "phoneNumber", source ="updateUserRequest.phoneNumber" )
    @Mapping(target = "email", source ="updateUserRequest.email" )
    @Mapping(target = "username", source ="updateUserRequest.username" )
//    @Mapping(target = "password", source ="updateUserRequest.password" )
    @Mapping(target = "image", source ="updateUserRequest.image")
    public abstract AppUser map(UpdateUserRequest updateUserRequest);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
//    @Mapping(target = "password", source = "password")
    @Mapping(target = "image", source = "image")
    public abstract UpdateUserRequest mapToDo(AppUser user);

}
