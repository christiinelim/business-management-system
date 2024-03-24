package com.bizorder.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bizorder.dtos.LoginUserDto;
import com.bizorder.dtos.RegisterUserDto;
import com.bizorder.model.User;
import com.bizorder.repository.UserRepository;
import com.bizorder.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signup(RegisterUserDto input) {
        User user = new User()
            .setFullName(input.getFullName())
            .setEmail(input.getEmail())
            .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return userRepository.findByEmail(input.getEmail())
            .orElseThrow();
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String hashResetToken(String token) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(token.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}