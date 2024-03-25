package com.bizorder.controller;

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
    public ResponseEntity<Account> register(@RequestBody RegisterUserDto registerUserDto) {
        Account registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Account authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // validate or say if email exist...
        // userService.checkIfEmailExists(request.getEmail());

        String resetToken = authenticationService.generateAndSaveResetToken(request.getEmail());

        System.out.println(resetToken);

        // TO UNCOMMENT
        emailService.sendResetEmail(request.getEmail(), resetToken);

        return ResponseEntity.ok("Reset email sent successfully");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        System.out.println("resetting path entered");
        boolean resetSuccessful = authenticationService.resetPassword(request.getEmail(), request.getResetToken(), request.getNewPassword());

        if (resetSuccessful) {
            return ResponseEntity.ok("Password reset successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to reset password");
        }
    }
}
