// package com.bizorder.service;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.bizorder.dtos.LoginUserDto;
// import com.bizorder.dtos.RegisterUserDto;
// import com.bizorder.model.User;
// import com.bizorder.repository.UserRepository;

// @Service
// public class AuthenticationService1 {
//     private final UserRepository userRepository;
    
//     private final PasswordEncoder passwordEncoder;
    
//     private final AuthenticationManager authenticationManager;

//     public AuthenticationService(
//         UserRepository userRepository,
//         AuthenticationManager authenticationManager,
//         PasswordEncoder passwordEncoder
//     ) {
//         this.authenticationManager = authenticationManager;
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public User signup(RegisterUserDto input) {
//         User user = new User()
//                 .setFullName(input.getFullName())
//                 .setEmail(input.getEmail())
//                 .setPassword(passwordEncoder.encode(input.getPassword()));

//         return userRepository.save(user);
//     }

//     public User authenticate(LoginUserDto input) {
//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         input.getEmail(),
//                         input.getPassword()
//                 )
//         );

//         return userRepository.findByEmail(input.getEmail())
//                 .orElseThrow();
//     }
// }