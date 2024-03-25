package com.bizorder.service;

import com.bizorder.dtos.LoginResponse;
import com.bizorder.dtos.LoginUserDto;
import com.bizorder.dtos.RegisterUserDto;
import com.bizorder.model.Account;

public interface AuthenticationService {
    Account signup(RegisterUserDto input);
    Account authenticate(LoginUserDto input);
    LoginResponse authenticateAndGenerateResponse(LoginUserDto loginUserDto);
    Integer generateAndSaveVerificationToken(String email);
    void verifyAccount(String email, Integer token);
    boolean updatePassword(String email, String newPassword);
    String hashResetToken(String token);
    String generateAndSaveResetToken(String email);
    boolean resetPassword(String email, String token, String newPassword);  
}
