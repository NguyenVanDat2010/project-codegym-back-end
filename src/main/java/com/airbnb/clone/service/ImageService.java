package com.airbnb.clone.service;

import com.airbnb.clone.exception.HouseNotFoundException;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.ImageModel;
import com.airbnb.clone.repository.HouseRepository;
import com.airbnb.clone.repository.ImageRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private HouseRepository houseRepository;

    public ImageModel saveImage(MultipartFile file, String houseId) throws IOException {
        ImageModel img =
                ImageModel.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .picByte(compressBytes(file.getBytes()))
                        .build();
        return imageRepository.save(img);
    }
    public List<ImageModel> getAllImageByHouseId(Long houseId) {
        House house =
                houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException(houseId.toString()));
        List<ImageModel> result = imageRepository.findAllByHouse(house);
        for (ImageModel imageModel:result) {
            imageModel.setPicByte(decompressBytes(imageModel.getPicByte()));
        }
        return result;
    }
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
