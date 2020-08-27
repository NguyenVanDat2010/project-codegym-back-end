package com.airbnb.clone.service;

import com.airbnb.clone.dto.RegisterRequest;
import com.airbnb.clone.exception.AppException;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.VerificationToken;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationRepository verificationRepository;
    @Autowired
    private MailContentBuilder mailContentBuilder;
    private static final String VERIFICATION_URL = "http://localhost:8080/api/auth" +
            "/accountVerification/";
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setEmail(registerRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setCreated(Instant.now());
        appUser.setEnabled(false);

        appUserRepository.save(appUser);
        String token = generateVerificationToken(appUser);
        String message = mailContentBuilder.build("Thank you for signing up, please click on the " +
                "link to active your account: " + VERIFICATION_URL + token);
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new AppException("Invalid token"));
    }
    private void enableUserByToken(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        AppUser appUser =
                appUserRepository.findByUsername(username).orElseThrow(() -> new AppException(
                        "User not found with name - " + username));
        appUser.setEnabled(true);
        appUserRepository.save(appUser);
    }
    private String generateVerificationToken(AppUser appUser) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(appUser);
        verificationRepository.save(verificationToken);
        return token;
    }

}
