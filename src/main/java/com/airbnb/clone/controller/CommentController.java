package com.airbnb.clone.controller;

import com.airbnb.clone.dto.CommentDto;
import com.airbnb.clone.model.House;
import com.airbnb.clone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> CreateComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-house/{houseId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForHouse(@PathVariable Long houseId){
        return new ResponseEntity<>(commentService.getAllCommentsForHouse(houseId),HttpStatus.OK);
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser (@PathVariable String userName){
        return new ResponseEntity<>(commentService.getAllCommentsForUser(userName),HttpStatus.OK);
    }
}
