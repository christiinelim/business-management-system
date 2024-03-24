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
import com.bizorder.model.ResetToken;
import com.bizorder.model.User;
import com.bizorder.repository.ResetTokenRepository;
import com.bizorder.service.AuthenticationService;
import com.bizorder.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final ResetTokenRepository resetTokenRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, ResetTokenRepository resetTokenRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.resetTokenRepository = resetTokenRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // validate or say if email exist...
        // userService.checkIfEmailExists(request.getEmail());

        String resetToken = jwtService.generateResetToken(request.getEmail());

        String hashedToken = authenticationService.hashResetToken(resetToken); // Hash the token value

        ResetToken tokenEntity = new ResetToken(hashedToken, request.getEmail());

        System.out.println(hashedToken);

        System.out.println(resetToken);
        System.out.println(request.getEmail());
        resetTokenRepository.save(tokenEntity);

        // emailService.sendResetEmail(request.getEmail(), resetToken);

        return ResponseEntity.ok("Reset email sent successfully");
    }

    // @PostMapping("/reset-password")
    // public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
    //     // Validate the reset token
    //     if (!jwtService.isValidResetToken(request.getResetToken())) {
    //         return ResponseEntity.badRequest().body(new ResetPasswordResponse("Invalid reset token"));
    //     }

    //     // Update the user's password in the database
    //     boolean passwordUpdated = authenticationService.updatePassword(request.getEmail(), request.getNewPassword());

    //     if (passwordUpdated) {
    //         return ResponseEntity.ok(new ResetPasswordResponse("Password updated successfully"));
    //     } else {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResetPasswordResponse("Failed to update password"));
    //     }
    // }
}
