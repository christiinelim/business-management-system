package com.bizorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.dtos.ForgotPasswordRequest;
import com.bizorder.dtos.LoginResponse;
import com.bizorder.dtos.LoginUserDto;
import com.bizorder.dtos.RegisterUserDto;
import com.bizorder.dtos.ResetPasswordRequest;
import com.bizorder.model.Account;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.AuthenticationService;
import com.bizorder.service.EmailService;
import com.bizorder.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, EmailService emailService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            return ResponseHandler.responseBuilder("Signup success", HttpStatus.OK, authenticationService.signup(registerUserDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error signing up: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            Account authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
            return ResponseHandler.responseBuilder("Login success", HttpStatus.OK, loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            // validate or say if email exist...
            // userService.checkIfEmailExists(request.getEmail());
            
            String resetToken = authenticationService.generateAndSaveResetToken(request.getEmail());

            System.out.println(resetToken);

            // TO UNCOMMENT
            emailService.sendResetEmail(request.getEmail(), resetToken);

            return ResponseHandler.responseBuilder("Reset email sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error signing up: " + e.getMessage());
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            System.out.println("resetting path entered");
            boolean resetSuccessful = authenticationService.resetPassword(request.getEmail(), request.getResetToken(), request.getNewPassword());

            if (resetSuccessful) {
                return ResponseHandler.responseBuilder("Password reset successful", HttpStatus.OK);
            } else {
                return ResponseHandler.responseBuilder("Failed to reset password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error signing up: " + e.getMessage());
        }

    }
}
