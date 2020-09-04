package com.airbnb.clone.dto;

import com.airbnb.clone.model.AppUser;

import java.time.Instant;

public class CommentDto {
    private Long id;
    private Long houseId;
    private Instant startDate;
    private String text;
    private String username;
    private Integer votes;

    public CommentDto() {
    }

    public CommentDto(Long id, Long houseId, Instant startDate, String text, String username,Integer votes) {
        this.id = id;
        this.houseId = houseId;
        this.startDate = startDate;
        this.text = text;
        this.username = username;
        this.votes =votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
