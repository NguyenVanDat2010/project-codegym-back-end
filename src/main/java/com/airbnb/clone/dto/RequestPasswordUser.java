package com.airbnb.clone.dto;

import com.airbnb.clone.validate.UniqueUserName;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class RequestPasswordUser {
//    private Long userId;
    @NotBlank(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Column(nullable = false,unique = true)
//    @UniqueUserName
    private String username;
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String newPassword;
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
