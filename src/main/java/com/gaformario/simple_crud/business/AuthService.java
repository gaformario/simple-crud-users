package com.gaformario.simple_crud.business;

import com.gaformario.simple_crud.infrastructure.entitys.User;
import com.gaformario.simple_crud.infrastructure.repository.UserRepository;
import com.gaformario.simple_crud.infrastructure.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public String authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        Date now = new Date();
        Date expiry = new Date(now.getTime() + 3600_000);

        return jwtTokenService.create(
                user.getId().toString(),
                user.getRole().getRole(),
                now,
                expiry
        );
    }
}