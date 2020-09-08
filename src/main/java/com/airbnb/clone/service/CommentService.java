package com.airbnb.clone.service;

import com.airbnb.clone.dto.CommentDto;
import com.airbnb.clone.exception.HouseNotFoundException;
import com.airbnb.clone.mapper.CommentMapper;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.Comment;
import com.airbnb.clone.model.House;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.ICommentRepository;
import com.airbnb.clone.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private ICommentRepository commentRepository;
    private IHouseRepository houseRepository;
    private AppUserRepository appUserRepository;
    private CommentMapper commentMapper;
    private AuthService authService;


    public List<Comment> showAllComments() {
        return commentRepository.findAll();
    }

    public CommentService(
            ICommentRepository commentRepository,
            IHouseRepository houseRepository, AppUserRepository appUserRepository, CommentMapper commentMapper, AuthService authService) {
        this.commentRepository = commentRepository;
        this.appUserRepository = appUserRepository;
        this.houseRepository = houseRepository;
        this.commentMapper = commentMapper;
        this.authService = authService;
    }


    public void save(CommentDto commentDto) {
        House house = houseRepository.findById(commentDto.getHouseId())
                .orElseThrow(() -> new HouseNotFoundException(commentDto.getHouseId().toString()));
        Comment comment = commentMapper.map(commentDto, house, authService.getCurrentUser());
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsForUser(String userName){
        AppUser appUser =
                appUserRepository.findByUsername(userName)
                        .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.
                findAllByAppUser(appUser)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsForHouse(Long houseId){
        House house =
                houseRepository.findById(houseId)
                        .orElseThrow(() -> new HouseNotFoundException(houseId.toString()));
        return commentRepository.
                findAllByHouse(house)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
