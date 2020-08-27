package com.airbnb.clone.service;

import com.airbnb.clone.dto.AuthenticationResponse;
import com.airbnb.clone.dto.LoginRequest;
import com.airbnb.clone.dto.RegisterRequest;
import com.airbnb.clone.exception.AppException;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.model.NotificationEmail;
import com.airbnb.clone.model.VerificationToken;
import com.airbnb.clone.repository.AppUserRepository;
import com.airbnb.clone.repository.VerificationRepository;
import com.airbnb.clone.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private MailService mailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    private static final String VERIFICATION_URL = "http://localhost:8080/api/auth" +
            "/accountVerification/";

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticatingObject =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authenticatingObject);
        String authenticationToken = jwtProvider.generateToken(authenticatingObject);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }
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

        mailService.sendConfirmSignupMail(new NotificationEmail("Please Activate your account",
                appUser.getEmail(), "Thank you for signing up, please click on the below url to " +
                "active your account : " + "http://localhost:8080/api/auth/accountVerification/"+token));
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationRepository.findByToken(token);
        enableUserByToken(verificationToken.orElseThrow(() -> new AppException("Invalid token")));
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
