package com.bizorder.service;

import com.bizorder.dtos.LoginUserDto;
import com.bizorder.dtos.RegisterUserDto;
import com.bizorder.model.User;

public interface AuthenticationService {
    User signup(RegisterUserDto input);
    User authenticate(LoginUserDto input);
    boolean updatePassword(String email, String newPassword);
    String hashResetToken(String token);
    String generateAndSaveResetToken(String email);
    boolean resetPassword(String email, String token, String newPassword);
}
