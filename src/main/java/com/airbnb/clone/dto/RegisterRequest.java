package com.airbnb.clone.dto;

import com.airbnb.clone.validate.UniqueEmail;
import com.airbnb.clone.validate.UniquePhoneNumber;
import com.airbnb.clone.validate.UniqueUserName;
import org.springframework.web.multipart.MultipartFile;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    @UniquePhoneNumber
    private String phoneNumber;
    @UniqueEmail
    private String email;
    @UniqueUserName
    private String username;
    private String password;

    private MultipartFile imageFile;

    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String lastName, String phoneNumber, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
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


    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
