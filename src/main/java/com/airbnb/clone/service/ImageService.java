package com.airbnb.clone.service;

import com.airbnb.clone.dto.ImageDto;
import com.airbnb.clone.exception.HouseNotFoundException;
import com.airbnb.clone.mapper.ImageMapper;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.ImageModel;
import com.airbnb.clone.repository.IHouseRepository;
import com.airbnb.clone.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private IHouseRepository houseRepository;
    @Autowired
    private ImageMapper imageMapper;

    public void saveImage(ImageDto imageDto){
        House house =
                houseRepository
                        .findById(imageDto.getHouseId())
                        .orElseThrow(() -> new HouseNotFoundException(imageDto.getHouseId().toString()));
        ImageModel img = imageMapper.map(imageDto,house);
        imageRepository.save(img);
    }
    @Transactional(readOnly = true)
    public List<ImageDto> getAllImageByHouseId(Long houseId) {
        House house =
                houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException(houseId.toString()));
        return imageRepository
                .findAllByHouse(house)
                .stream()
                .map(imageMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
