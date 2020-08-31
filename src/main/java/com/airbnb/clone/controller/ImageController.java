package com.airbnb.clone.controller;

import com.airbnb.clone.model.ImageModel;
import com.airbnb.clone.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/by-house/{houseId}")
    public ResponseEntity<List<ImageModel>> getImage(@PathVariable("houseId") Long houseId) {
        return new ResponseEntity<>(imageService.getAllImageByHouseId(houseId), HttpStatus.OK);
    }
}
