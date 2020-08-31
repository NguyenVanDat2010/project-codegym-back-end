package com.airbnb.clone.model;

import com.airbnb.clone.validate.UniqueEmail;
import com.airbnb.clone.validate.UniquePhoneNumber;
import com.airbnb.clone.validate.UniqueUserName;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Column(nullable = false,unique = true)
    @UniqueUserName
    private String username;
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
    @Email
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email is required")
    @UniqueEmail
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "Email must match, Ex: example@gmail.com")
    private String email;
    @Column
    private String image;
    @Column(name = "phone_number", nullable = false, unique = true)
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "(08|09|01[2|6|8|9])+([0-9]{8})\\b", message = "Phone number must match, Ex: 0989898989")
    @UniquePhoneNumber
    private String phoneNumber;
    private Instant created;
    private boolean enabled;

    public AppUser() {
    }

    public AppUser(String firstName, String lastName, String username, String email, String password, String phoneNumber, Instant created, boolean enabled, String image){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.created = created;
        this.enabled = enabled;
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public @NotEmpty(message = "Phone number is required") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty(message = "Phone number is required") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
