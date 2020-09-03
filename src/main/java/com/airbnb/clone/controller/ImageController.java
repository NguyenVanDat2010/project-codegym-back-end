package com.airbnb.clone.controller;

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
    public ResponseEntity<ResponseMessage> uploadFiles(
            @RequestPart("file")MultipartFile file,
            @RequestPart("houseId") String houseId) {
        String message = "";
        try {
            imageService.saveImage(file,Long.parseLong(houseId));
            message = "Upload the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(new ResponseMessage(message),HttpStatus.OK);
        } catch (IOException e) {
            message = "Could not upload the file: " + file.getOriginalFilename();
            return new ResponseEntity<>(new ResponseMessage(message),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/by-house/{houseId}")
    public ResponseEntity<List<ImageModel>> getImage(@PathVariable("houseId") Long houseId) {
        return new ResponseEntity<>(imageService.getAllImageByHouseId(houseId), HttpStatus.OK);
    }
}
