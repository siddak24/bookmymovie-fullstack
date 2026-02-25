package com.bookmymovie.bookmymovie.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmymovie.bookmymovie.entity.Role;
import com.bookmymovie.bookmymovie.entity.User;
import com.bookmymovie.bookmymovie.repository.UserRepository;
import com.bookmymovie.bookmymovie.security.JwtUtil;
import com.bookmymovie.bookmymovie.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthServiceImpl(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
}

    @Override
    public String register(String email, String password) {

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
public String login(String email, String password) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    return jwtUtil.generateToken(user.getEmail());
}
}