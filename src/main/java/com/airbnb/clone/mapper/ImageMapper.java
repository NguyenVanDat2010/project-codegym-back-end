package com.airbnb.clone.mapper;

import com.airbnb.clone.dto.ImageDto;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ref", source = "imageDto.ref")
    @Mapping(target = "house", source = "house")
    ImageModel map(ImageDto imageDto, House house);

    @Mapping(target = "houseId", expression = "java(imageModel.getHouse().getId())")
    ImageDto mapToDto(ImageModel imageModel);
}
