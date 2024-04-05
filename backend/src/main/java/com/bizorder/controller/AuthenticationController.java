package com.bizorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.api.AuthenticationApi;
import com.bizorder.dtos.ForgotPasswordRequest;
import com.bizorder.dtos.LoginAccountDto;
import com.bizorder.dtos.ResetPasswordRequest;
import com.bizorder.dtos.VerifyAccountRequest;
import com.bizorder.model.Account;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.AuthenticationService;
import com.bizorder.service.EmailService;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    public AuthenticationController(AuthenticationService authenticationService, EmailService emailService) {
        this.authenticationService = authenticationService;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody Account account) {
        try {
            return ResponseHandler.responseBuilder("Signup success, check mailbox for verification code", HttpStatus.OK, authenticationService.signup(account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginAccountDto loginUserDto) {
        try {
            return ResponseHandler.responseBuilder("Login success", HttpStatus.OK, authenticationService.login(loginUserDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyAccount(@RequestBody VerifyAccountRequest request) {
        try {
            authenticationService.verifyAccount(request.getEmail(), request.getVerificationToken());
            return ResponseHandler.responseBuilder("Verification successful", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            String resetToken = authenticationService.generateAndSaveResetToken(request.getEmail());
            System.out.println(resetToken);
            emailService.sendResetEmail(request.getEmail(), resetToken);

            return ResponseHandler.responseBuilder("Reset email sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            boolean resetSuccessful = authenticationService.resetPassword(request.getEmail(), request.getResetToken(), request.getNewPassword());

            if (resetSuccessful) {
                return ResponseHandler.responseBuilder("Password reset successful", HttpStatus.OK);
            } else {
                return ResponseHandler.responseBuilder("Failed to reset password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }

    }
}
