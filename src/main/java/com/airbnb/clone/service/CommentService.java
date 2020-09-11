package com.airbnb.clone.service;

import com.airbnb.clone.dto.CommentDto;
import com.airbnb.clone.exception.HouseNotFoundException;
import com.airbnb.clone.mapper.CommentMapper;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.Comment;
import com.airbnb.clone.model.House;
import com.airbnb.clone.model.Reservation;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.ICommentRepository;
import com.airbnb.clone.repository.IHouseRepository;
import com.airbnb.clone.repository.IReservationRepository;
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
    private IReservationRepository reservationRepository;


    public List<Comment> showAllComments() {
        return commentRepository.findAll();
    }
    public List<Reservation> showAllReservationsByUser(AppUser user){
        return reservationRepository.findAllByUser(user);
    }

    public CommentService(
            ICommentRepository commentRepository,
            IHouseRepository houseRepository, AppUserRepository appUserRepository, CommentMapper commentMapper, AuthService authService, IReservationRepository reservationRepository) {
        this.commentRepository = commentRepository;
        this.appUserRepository = appUserRepository;
        this.houseRepository = houseRepository;
        this.commentMapper = commentMapper;
        this.authService = authService;
        this.reservationRepository = reservationRepository;
    }


    public void save(CommentDto commentDto) {
        AppUser currentUser = authService.getCurrentUser();
        House house = houseRepository.findById(commentDto.getHouseId())
                .orElseThrow(() -> new HouseNotFoundException(commentDto.getHouseId().toString()));
        Comment comment = commentMapper.map(commentDto, house, authService.getCurrentUser());
//        boolean exist = reservationRepository.existsByHouseAndUser(house, currentUser);
        if (reservationRepository.existsByHouseAndUser(house,currentUser)) {
            commentRepository.save(comment);
        }
//        commentRepository.save(comment);
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

    public float getRatingForHouse(House house) {
        List<Comment> commentList = commentRepository.findAllByHouse(house);
        float sum = 0;
        if (commentList.size() == 0){
            return 0;
        }
        else {
            for (int i = 0; i < commentList.size(); i++) {
                sum += commentList.get(i).getVotes();
            }
            return sum / commentList.size();
        }
    }
}