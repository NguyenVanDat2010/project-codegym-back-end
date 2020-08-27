package com.airbnb.clone.controller;

import com.airbnb.clone.dto.AuthenticationResponse;
import com.airbnb.clone.dto.LoginRequest;
import com.airbnb.clone.dto.RefreshTokenRequest;
import com.airbnb.clone.dto.RegisterRequest;
import com.airbnb.clone.service.AuthService;
import com.airbnb.clone.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   @Autowired
   private AuthService authService;
   @Autowired
   private RefreshTokenService refreshTokenService;

   @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
       authService.signup(registerRequest);
       return new ResponseEntity<>("User registration success", HttpStatus.OK);
   }

   @GetMapping("accountVerification/{token}")
   public ResponseEntity<String> verifyAccount(@PathVariable String token) {
      authService.verifyAccount(token);
      return new ResponseEntity<>("Account activated", HttpStatus.OK);
   }

   @PostMapping("/login")
   public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
      return authService.login(loginRequest);
   }
   @PostMapping("/refresh/token")
   public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
      return authService.refreshToken(refreshTokenRequest);
   }

   @PostMapping("/logout")
   public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
      refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
      return ResponseEntity.status(HttpStatus.OK).body("Refresh token delete successfully");
   }
}
