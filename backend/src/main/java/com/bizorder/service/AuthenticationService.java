package com.bizorder.service;

import com.bizorder.dtos.LoginResponse;
import com.bizorder.dtos.LoginAccountDto;
import com.bizorder.dtos.RegisterAccountDto;
import com.bizorder.model.Account;

public interface AuthenticationService {
    Account signup(RegisterAccountDto input);
    Account authenticate(LoginAccountDto input);
    LoginResponse authenticateAndGenerateResponse(LoginAccountDto loginUserDto);
    Integer generateAndSaveVerificationToken(String email);
    void verifyAccount(String email, Integer token);
    boolean updatePassword(String email, String newPassword);
    String hashResetToken(String token);
    String generateAndSaveResetToken(String email);
    boolean resetPassword(String email, String token, String newPassword);  
}
