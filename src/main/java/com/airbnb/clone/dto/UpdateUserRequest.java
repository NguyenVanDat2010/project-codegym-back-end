package com.airbnb.clone.dto;

import com.airbnb.clone.validate.UniqueEmail;
import com.airbnb.clone.validate.UniquePhoneNumber;
import com.airbnb.clone.validate.UniqueUserName;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UpdateUserRequest {
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
//    @UniquePhoneNumber
    private String phoneNumber;
//    @UniqueEmail
    @Email
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email is required")
    private String email;
    @UniqueUserName
    @NotBlank(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Column(nullable = false,unique = true)
    private String username;
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
    private MultipartFile imageFile;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(Long id,String firstName, String lastName, String username, String email, String password, String phoneNumber, MultipartFile imageFile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.imageFile = imageFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
