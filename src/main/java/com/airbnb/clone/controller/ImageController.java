package com.airbnb.clone.controller;

import com.airbnb.clone.dto.ImageDto;
import com.airbnb.clone.dto.ResponseMessage;
import com.airbnb.clone.model.ImageModel;
import com.airbnb.clone.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ResponseMessage> uploadImage(@RequestBody ImageDto imageDto) {
        imageService.saveImage(imageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-house/{houseId}")
    public ResponseEntity<List<ImageDto>> getImage(@PathVariable("houseId") Long houseId) {
        return new ResponseEntity<>(imageService.getAllImageByHouseId(houseId), HttpStatus.OK);
    }
}
