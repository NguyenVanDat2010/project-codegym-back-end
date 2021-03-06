package com.airbnb.clone.controller;

import com.airbnb.clone.dto.*;
import com.airbnb.clone.model.AppUser;
import com.airbnb.clone.service.AppUserService;
import com.airbnb.clone.service.AuthService;
import com.airbnb.clone.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> signup(@Valid @RequestBody RegisterRequest registerRequest, BindingResult result) {
      if (registerRequest == null || result.hasErrors()){
         return ResponseEntity.badRequest().body(new MessageResponse("Sign up failed!"));
      }
       authService.signup(registerRequest);
       return new ResponseEntity<>(new MessageResponse("Sign up successfully!"), HttpStatus.OK);
   }

   @GetMapping("/users/{username}")
   public ResponseEntity<UpdateUserRequest> getAllUsers(@PathVariable String username){
      return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
   }

   @PutMapping("/users")
   public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest, BindingResult result){
      if (updateUserRequest == null || result.hasErrors()){
         return ResponseEntity.badRequest().body(new MessageResponse("Update failed!"));
      }
      Boolean isUpdated = userService.updateUser(updateUserRequest);
      if (isUpdated){
         return new ResponseEntity<>(HttpStatus.OK);
      }
      return ResponseEntity.badRequest().body(new MessageResponse("Update failed!"));
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

   @PutMapping("/users/change-pass")
   public ResponseEntity<?> changePass(@Valid @RequestBody RequestPasswordUser requestPasswordUser, BindingResult result){
      if (requestPasswordUser == null || result.hasErrors()){
         return ResponseEntity.badRequest().body(new MessageResponse("Change password failed!"));
      }
      Boolean isChangedPassword = userService.changePassword(requestPasswordUser);
      if (isChangedPassword){
         return new ResponseEntity<>(new MessageResponse("Change password successfully"),HttpStatus.OK);
      }
      return ResponseEntity.badRequest().body(new MessageResponse("Change password failed!"));
   }
}
