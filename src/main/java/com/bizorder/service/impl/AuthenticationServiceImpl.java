package com.bizorder.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bizorder.dtos.LoginUserDto;
import com.bizorder.dtos.RegisterUserDto;
import com.bizorder.exception.TokenExpiredException;
import com.bizorder.model.ResetToken;
import com.bizorder.model.Seller;
import com.bizorder.model.Account;
import com.bizorder.repository.ResetTokenRepository;
import com.bizorder.repository.SellerRepository;
import com.bizorder.repository.AccountRepository;
import com.bizorder.service.AuthenticationService;
import com.bizorder.service.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ResetTokenRepository resetTokenRepository;
    private final SellerRepository sellerRepository;

    public AuthenticationServiceImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService, ResetTokenRepository resetTokenRepository, SellerRepository sellerRepository) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.resetTokenRepository = resetTokenRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Account signup(RegisterUserDto input) {
        System.out.println(input.getSellerId());
        Seller seller = sellerRepository.findById(input.getSellerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid seller ID"));
                
        Account user = new Account()
            .setFullName(input.getFullName())
            .setEmail(input.getEmail())
            .setPassword(passwordEncoder.encode(input.getPassword()))
            .setSeller(seller);

        return accountRepository.save(user);
    }

    @Override
    public Account authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return accountRepository.findByEmail(input.getEmail())
            .orElseThrow();
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        Optional<Account> optionalUser = accountRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Account user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(user);
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

    @Override
    public String generateAndSaveResetToken(String email) {
        String resetToken = jwtService.generateResetToken(email);

        String hashedToken = hashResetToken(resetToken);

        ResetToken tokenEntity = new ResetToken(hashedToken, email);
        resetTokenRepository.save(tokenEntity);
        
        System.out.println("resetToken");
        System.out.println(resetToken);
        System.out.println("hashedToken");
        System.out.println(hashedToken);

        return resetToken;
    }

    @Override
    public boolean resetPassword(String email, String token, String newPassword) {
        Optional<Account> optionalUser = accountRepository.findByEmail(email);
        Account user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        Optional<ResetToken> optionalResetToken = resetTokenRepository.findByEmail(email);
        if (!optionalResetToken.isPresent()) {
            // token not found
            return false;
        }

        String storedHashedToken = resetTokenRepository.getHashedTokenByEmail(email);
        String hashedToken = hashResetToken(token);

        System.out.println("storedHashedToken");
        System.out.println(storedHashedToken);
        System.out.println("hashedToken");
        System.out.println(hashedToken);



        if (!(storedHashedToken != null && storedHashedToken.equals(hashedToken))) {
            // token do not match
            return false;
        }

        Date expiry = resetTokenRepository.getExpiresAtByEmail(email);
        Date currentDate = new Date(); 

        if (!currentDate.before(expiry)) {
            // token expired
            resetTokenRepository.deleteByHashedToken(storedHashedToken);
            throw new TokenExpiredException("Token has expired. Please generate a new one.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        // Save the updated user
        accountRepository.save(user);

        resetTokenRepository.deleteByHashedToken(storedHashedToken);

        return true;
    }
}