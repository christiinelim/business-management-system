package com.bizorder.api;

import org.springframework.http.ResponseEntity;

import com.bizorder.dtos.ForgotPasswordRequest;
import com.bizorder.dtos.LoginAccountDto;
import com.bizorder.dtos.ResetPasswordRequest;
import com.bizorder.dtos.VerifyAccountRequest;
import com.bizorder.model.Account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Authentication APIs")
public interface AuthenticationApi {

    // CREATE
    @Operation(
        summary = "User registration",
        description = "Registers a new user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Signup success, check mailbox for verification code"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> register(@Parameter(description = "New account details", required = true) @RequestBody Account account);

    // LOGIN
    @Operation(
        summary = "User login",
        description = "Logs in an existing user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login success"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> login(@Parameter(description = "Login details", required = true) @RequestBody LoginAccountDto loginUserDto);


    // VERIFY
    @Operation(
        summary = "Verify account",
        description = "Verifies the user account using verification token sent via email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verification successful"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> verifyAccount(@Parameter(description = "Verification request details", required = true) @RequestBody VerifyAccountRequest request);


    // FORGOT PASSWORD
    @Operation(
        summary = "Forgot password",
        description = "Sends a reset email to the user's email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reset email sent successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> forgotPassword(@Parameter(description = "Forgot password request details", required = true) @RequestBody ForgotPasswordRequest request);


    // RESET PASSWORD
    @Operation(
        summary = "Reset password",
        description = "Resets the user's password using the reset token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Password reset successful"),
        @ApiResponse(responseCode = "400", description = "Failed to reset password"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Object> resetPassword(@Parameter(description = "Reset password request details", required = true) @RequestBody ResetPasswordRequest request);
}