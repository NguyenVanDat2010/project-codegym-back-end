package com.airbnb.clone.controller;

import com.airbnb.clone.dto.*;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.service.AppUserService;
import com.airbnb.clone.service.AuthService;
import com.airbnb.clone.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   @Autowired
   private AuthService authService;
   @Autowired
   private RefreshTokenService refreshTokenService;

   @Autowired
   private AppUserService userService;

   @Validated
   @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
       authService.signup(registerRequest);
       return new ResponseEntity<>( HttpStatus.OK);
   }

   @GetMapping("/users")
   public ResponseEntity<Iterable<AppUser>> getAllUsers(){
      return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
   }

   @PutMapping("/updateUser/{id}")
   public ResponseEntity<AppUser> updateUser(@RequestBody UpdateUserRequest updateUserRequest){
      authService.updateUser(updateUserRequest);
      return new ResponseEntity<>(HttpStatus.OK);
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
